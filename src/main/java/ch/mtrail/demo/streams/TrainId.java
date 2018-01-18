package ch.mtrail.demo.streams;

public class TrainId {

	private final String formattedDate;
	private final String trassenId;
	private final long betriebsTag;
	private final String zugId;

	private final String evu;
	private final String zugNummer;
	private final String laufNummer;

	public TrainId(final String formattedDate, final String trassenId, final String zugId, final long betriebsTag) {
		this.formattedDate = formattedDate;
		this.trassenId = trassenId;
		this.betriebsTag = betriebsTag;
		this.zugId = zugId;

		final String[] splittedId = splitZugId(zugId);
		this.evu = splittedId != null ? splittedId[0] : null;
		this.zugNummer = splittedId != null ? splittedId[1] : null;
		this.laufNummer = splittedId != null ? splittedId[2] : null;
	}

	private String[] splitZugId(final String zugId) {
		String[] splittedId = zugId != null ? zugId.split("-") : null;
		if (splittedId != null && splittedId.length != 3) {
			splittedId = new String[] { "n/a", "n/a", "n/a" };
		}
		return splittedId;
	}

	public TrainId() {
		this(null, null, null, 0L);
	}

	public long getBetriebsTag() {
		return betriebsTag;
	}

	public String getTrassenId() {
		return trassenId;
	}

	public String getZugId() {
		return zugId;
	}

	@Override
	public String toString() {
		return "ID [" + zugId + " am " + formattedDate + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (betriebsTag ^ (betriebsTag >>> 32));
		result = prime * result + ((trassenId == null) ? 0 : trassenId.hashCode());
		result = prime * result + ((zugId == null) ? 0 : zugId.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TrainId other = (TrainId) obj;
		if (betriebsTag != other.betriebsTag) {
			return false;
		}
		if (trassenId == null) {
			if (other.trassenId != null) {
				return false;
			}
		} else if (!trassenId.equals(other.trassenId)) {
			return false;
		}
		if (zugId == null) {
			if (other.zugId != null) {
				return false;
			}
		} else if (!zugId.equals(other.zugId)) {
			return false;
		}
		return true;
	}

	public String getEvu() {
		return evu;
	}

	public String getZugNummer() {
		return zugNummer;
	}

	public String getLaufNummer() {
		return laufNummer;
	}

}
