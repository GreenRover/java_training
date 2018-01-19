package ch.mtrail.testing.mockito;

import java.time.Duration;
import java.time.Instant;

public class MessageHandler {

	private ZeitService zeitService;
	private WorkerPool workerPool;
	private SlaService slaService;
	private Duration maximalDuration;

	public MessageHandler(ZeitService zeitService, WorkerPool workerPool, SlaService slaService,
			Duration maximalDuration) {
		this.zeitService = zeitService;
		this.workerPool = workerPool;
		this.slaService = slaService;
		this.maximalDuration = maximalDuration;
	}

	public Message onMessage(Message message) {
		if (message != null) {
			message.setTimeReceived(zeitService.getTime());
			Instant startTs = message.getTimeReceived().toInstant();

			Worker worker = workerPool.getWorker();
			worker.process(message, 1);

			message.setTimeProcessed(zeitService.getTime());
			Instant processTs = message.getTimeProcessed().toInstant();

			Duration timeSpent = Duration.between(startTs, processTs);
			if (timeSpent.compareTo(maximalDuration) >= 0)
				slaService.reportViolation(message);
		}

		return message;
	}

}
