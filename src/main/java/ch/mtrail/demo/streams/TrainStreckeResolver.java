package ch.mtrail.demo.streams;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrainStreckeResolver {
	/**
	 * Filtert die gegebenen TrainOperations.
	 * <p>
	 * Falls der gleiche Zug gemäss EVU und ZugNummer mehrmals vorhanden ist, und
	 * einer davon den Betriebstag "Heute" oder den gegebenen Betriebstag hat, wird
	 * ausschliesslich dieser zurückgegeben. Ergibt ein gegebener Betriebstag kein
	 * Resultat, so wird nach dem heutigen Tag gefiltert.
	 * </p>
	 *
	 * @param trains
	 *            darf nicht {@code null} sein
	 * @param betriebstag
	 *            ein für das Auflösen relevanter Betriebstag, {@code null} oder
	 *            {@link ADateTime#UNDEFINED} wird als "heute" interpretiert
	 * @return die gefilterten TrainOperations, nie {@code null}
	 */
	public Collection<TrainOperations> filterDuplicatedTrains(final Collection<TrainOperations> trains,
			final ADateTime betriebstag) {
		ADateTime zukuenftigerVerkehrstag = (betriebstag == null || ADateTime.UNDEFINED.equals(betriebstag))
				? ADateTime.UNDEFINED
				: betriebstag;

		final Map<String, List<TrainOperations>> trainsGrouped = trains.stream().collect(Collectors.groupingBy( //
				trainOp -> trainOp.getTrainId().getEvu() + "-" + trainOp.getTrainId().getZugNummer(), //
				Collectors.toList() //
		));

		final List<TrainOperations> zuegeGemaessVerkehrstagMitFallbackAufHeuteOderAlle = trainsGrouped.values().stream()
				.flatMap(trainGroup -> {
					// if we get more than one train in the group and at least one train operating
					// today, use only trains that operate today.
					if (trainGroup.size() > 1) {

						if (!ADateTime.UNDEFINED.equals(zukuenftigerVerkehrstag)) {
							final List<TrainOperations> trainsOfZukuenftigerVerkehrstag = trainGroup.stream() //
									.filter(train -> train.getTrainId().getBetriebsTag() == zukuenftigerVerkehrstag
											.truncateTime().getMillis()) //
									.collect(Collectors.toList());

							if (trainsOfZukuenftigerVerkehrstag.size() > 0) {
								return trainsOfZukuenftigerVerkehrstag.stream();
							}
						}

						final long today = new ADateTime().truncateTime().getMillis();
						final List<TrainOperations> trainsOfToday = trainGroup.stream() //
								.filter(train -> train.getTrainId().getBetriebsTag() == today) //
								.collect(Collectors.toList());

						if (trainsOfToday.size() > 0) {
							return trainsOfToday.stream();
						}
					}

					// Use all trains of group if condition does not match.
					return trainGroup.stream();
				}).collect(Collectors.toList());

		if (zuegeGemaessVerkehrstagMitFallbackAufHeuteOderAlle.size() == 1) {
			return zuegeGemaessVerkehrstagMitFallbackAufHeuteOderAlle;
		}

		// falls ein bestimmter Verkehrstag verlangt wurde und Zugläufe gefunden, nur
		// die dem Verkehrstag entsprechenden Zugläufe zurückgeben:
		if (!ADateTime.UNDEFINED.equals(zukuenftigerVerkehrstag)) {
			final List<TrainOperations> trainsOfZukuenftigerVerkehrstag = zuegeGemaessVerkehrstagMitFallbackAufHeuteOderAlle
					.stream() //
					.filter(train -> train.getTrainId().getBetriebsTag() == zukuenftigerVerkehrstag.truncateTime()
							.getMillis()) //
					.collect(Collectors.toList());

			if (trainsOfZukuenftigerVerkehrstag.size() > 0) {
				return trainsOfZukuenftigerVerkehrstag;
			}
		}

		// Fallback (heutige Zugläufe oder einfach die jenige, die gefunden wurden):
		return zuegeGemaessVerkehrstagMitFallbackAufHeuteOderAlle;
	}
}
