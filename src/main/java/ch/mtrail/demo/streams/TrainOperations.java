package ch.mtrail.demo.streams;

public class TrainOperations {

	private TrainId trainId;
	private int versionNumber;
	private String zugnummer;
	private long zugnummerasLong;
	private Boolean fakZugfahrt;
	private Boolean nationalTrain;
	private Boolean passengerTrain;
	private Boolean merged = Boolean.FALSE;

	public TrainId getTrainId() {
		return trainId;
	}

	public void setTrainId(TrainId trainId) {
		this.trainId = trainId;
	}

	public int getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getZugnummer() {
		return zugnummer;
	}

	public void setZugnummer(String zugnummer) {
		this.zugnummer = zugnummer;
	}

	public long getZugnummerasLong() {
		return zugnummerasLong;
	}

	public void setZugnummerasLong(long zugnummerasLong) {
		this.zugnummerasLong = zugnummerasLong;
	}

	public Boolean getFakZugfahrt() {
		return fakZugfahrt;
	}

	public void setFakZugfahrt(Boolean fakZugfahrt) {
		this.fakZugfahrt = fakZugfahrt;
	}

	public Boolean getNationalTrain() {
		return nationalTrain;
	}

	public void setNationalTrain(Boolean nationalTrain) {
		this.nationalTrain = nationalTrain;
	}

	public Boolean getPassengerTrain() {
		return passengerTrain;
	}

	public void setPassengerTrain(Boolean passengerTrain) {
		this.passengerTrain = passengerTrain;
	}

	public Boolean getMerged() {
		return merged;
	}

	public void setMerged(Boolean merged) {
		this.merged = merged;
	}

}
