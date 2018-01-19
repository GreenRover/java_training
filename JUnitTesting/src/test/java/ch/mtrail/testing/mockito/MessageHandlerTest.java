package ch.mtrail.testing.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MessageHandlerTest {

	private MessageHandler messageHandler;
	@Mock
	private ZeitService zeitService;
	@Mock
	private WorkerPool workerPool;
	@Mock
	private Worker worker;
	@Captor
	private ArgumentCaptor<Message> workerMessageCaptor;
	@Mock
	private SlaService slaService;
	private Duration maxDuration = Duration.ofSeconds(1);
	private Duration worktime = Duration.ofMillis(5);

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		messageHandler = new MessageHandler(zeitService, workerPool, slaService, maxDuration);
		Mockito.when(workerPool.getWorker()).thenReturn(worker);
		final Instant firstTs = Instant.now();

		Mockito.when(zeitService.getTime()).thenAnswer(new Answer<Date>() {
			private Instant ts = null;

			@Override
			public Date answer(InvocationOnMock invocation) throws Throwable {
				if (ts == null) {
					ts = firstTs;
				} else {
					ts = ts.plus(worktime);
				}
				return Date.from(ts);
			}
		});

	}

	@Test
	public void onMessage_shouldInvoteTimeService_whenCalled() {
		// given
		Message message = new Message();

		// when
		messageHandler.onMessage(message);

		// then
		Mockito.verify(zeitService, Mockito.times(2)).getTime();
	}

	@Test
	public void onMessage_shouldSetTimeReceived_whenCalled() {
		// given
		Message message = new Message();
		Date expected = new Date();
		Mockito.when(zeitService.getTime()).thenReturn(expected);

		// when
		message = messageHandler.onMessage(message);

		// then
		assertEquals(expected, message.getTimeReceived());
	}

	@Test
	public void onMessage_shouldSetTimeReceived_whenCalledUsingAnswer() {
		// given
		Message message = new Message();
		final Date expected = new Date();

		// when
		message = messageHandler.onMessage(message);

		// then
		assertEquals(expected, message.getTimeReceived());
	}

	@Test
	public void onMessage_shouldInvoteWorkerPool_whenCalled() {
		// given
		Message message = new Message();

		// when
		messageHandler.onMessage(message);

		// then
		Mockito.verify(workerPool).getWorker();
	}

	@Test
	public void onMessage_shouldInvokeWorker_whenCalled() {
		// given
		Message message = new Message();

		// when
		messageHandler.onMessage(message);

		// then
		Mockito.verify(worker).process(Mockito.same(message), Mockito.eq(1));
		Mockito.verifyNoMoreInteractions(worker);
	}

	@Test
	public void onMessage_shouldSetTimeProcessed_whenCalled() {
		// given
		Message message = new Message();
		Date expected = new Date();
		Mockito.when(zeitService.getTime()).thenReturn(new Date(), expected);

		// when
		message = messageHandler.onMessage(message);

		// then
		assertEquals(expected, message.getTimeProcessed());
	}

	@Test
	public void onMessage_shouldInvokeWorker_whenUsingArgumentsCaptors() {
		// given
		Message message = new Message();

		// when
		messageHandler.onMessage(message);

		// then
		Mockito.verify(worker, Mockito.times(1)).process(workerMessageCaptor.capture(), Mockito.anyInt());
		assertSame(message, workerMessageCaptor.getValue());
		assertEquals(1, workerMessageCaptor.getAllValues().size());
	}

	@Test
	public void onMessage_shouldInvokeNothing_whenMessageNull() {
		// given
		Message message = null;

		// when
		messageHandler.onMessage(message);

		// then
		Mockito.verifyZeroInteractions(worker);
	}

	@Test
	public void onMessage_shouldInvokeSlaService_whenDelayIsTooLong() {
		// given
		Message message = new Message();
		worktime = maxDuration;

		// when
		message = messageHandler.onMessage(message);

		// then
		Mockito.verify(slaService).reportViolation(message);
	}

	@Test
	public void onMessage_shouldNotInvokeSlaService_whenDelayIsShort() {
		// given
		Message message = new Message();
		worktime = maxDuration.minus(10, ChronoUnit.MILLIS);

		// when
		message = messageHandler.onMessage(message);

		// then
		Mockito.verifyZeroInteractions(slaService);
	}

}
