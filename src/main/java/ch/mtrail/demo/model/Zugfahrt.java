package ch.mtrail.demo.model;

import java.sql.Date;
import java.util.Set;

public class Zugfahrt {
	private Integer trainId;
	private String evu;
	private String zugId;
	private Date betriebsTag;
	private Integer versionNumber;
	private byte isFakZugfahrt;
	private byte isNationalTrain;
	private byte isPassengerTrain;

	private Set<ZugfahrtSollpunkte> sollPunkte;

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public String getEvu() {
		return evu;
	}

	public void setEvu(String evu) {
		this.evu = evu;
	}

	public String getZugId() {
		return zugId;
	}

	public void setZugId(String zugId) {
		this.zugId = zugId;
	}

	public Date getBetriebsTag() {
		return betriebsTag;
	}

	public void setBetriebsTag(Date betriebsTag) {
		this.betriebsTag = betriebsTag;
	}

	public Integer getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}

	public byte getIsFakZugfahrt() {
		return isFakZugfahrt;
	}

	public void setIsFakZugfahrt(byte isFakZugfahrt) {
		this.isFakZugfahrt = isFakZugfahrt;
	}

	public byte getIsNationalTrain() {
		return isNationalTrain;
	}

	public void setIsNationalTrain(byte isNationalTrain) {
		this.isNationalTrain = isNationalTrain;
	}

	public byte getIsPassengerTrain() {
		return isPassengerTrain;
	}

	public void setIsPassengerTrain(byte isPassengerTrain) {
		this.isPassengerTrain = isPassengerTrain;
	}

	public Set<ZugfahrtSollpunkte> getSollPunkte() {
		return sollPunkte;
	}

	public void setSollPunkte(Set<ZugfahrtSollpunkte> sollPunkte) {
		this.sollPunkte = sollPunkte;
	}

}
