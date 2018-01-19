package ch.mtrail.demo.streams;

import java.util.Date;

/**
 * Wrapper class used in ALEA to add specific functionality. Subclassing is not
 * possible because joda DateTime is final. Add undefined to the unterlying
 * DateTime data type.
 */
public class ADateTime implements Comparable<ADateTime> {

	public static final String DATE_FORMAT = "dd.MM.yyyy"; //$NON-NLS-1$
	public static final String DATE_TIME_FORMAT_SECONDS = "dd.MM.yyyy HH:mm:ss"; //$NON-NLS-1$
	public static final int ONE_DAY_IN_MILIS = 86400000;

	private final Date dateTime;

	/**
	 * static ADateTime instance used to indicate that an ADateTime instance is
	 * undefined
	 */
	public static final ADateTime UNDEFINED = new ADateTime(0);

	public ADateTime() {
		dateTime = new Date();
	}

	public ADateTime(final long milis) {
		this.dateTime = new Date(milis);
	}
	
	public ADateTime(final Date dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Returns whether the underlying DateTime contains a defined date
	 *
	 * @return is internal DateTime undefined or not
	 */
	public boolean isUndefined() {
		return this.equals(UNDEFINED);
	}

	/**
	 * Returns the whether the underlying DateTime contains a defined date
	 *
	 * @return internal DateTime defined or not
	 */
	public boolean isDefined() {
		return !this.isUndefined();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
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
		final ADateTime other = (ADateTime) obj;
		return dateTime.getTime() == other.dateTime.getTime();
	}

	/**
	 * Test if time is same by using 1sec tolerance.
	 *
	 * @param other
	 *            The time to test
	 * @return TRUE if times are the same.
	 */
	public boolean sameTime(final ADateTime other) {
		if (other == null) {
			return false;
		}

		return (Math.abs(this.getMillis() - other.getMillis()) / 1000) < 1;
	}

	public long getMillis() {
		return dateTime.getTime();
	}

	@Override
	public int compareTo(ADateTime other) {
		return (int) (getMillis() - other.getMillis());
	}

	/**
	 * Truncates the time portion of an ADateTime
	 *
	 * @return ADateTime without time information
	 */
	public ADateTime truncateTime() {
		return new ADateTime(new Date(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay()));
	}

}
