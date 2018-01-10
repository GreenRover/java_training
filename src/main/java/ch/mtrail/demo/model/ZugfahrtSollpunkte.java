package ch.mtrail.demo.model;

import java.sql.Time;

public class ZugfahrtSollpunkte {
	private Integer bpId;
	private Zugfahrt train;
	private Integer position;
	private String bp;
	private Time zeitAn;
	private Time zeitAb;

	public Integer getBpId() {
		return bpId;
	}

	public void setBpId(Integer bpId) {
		this.bpId = bpId;
	}

	public Zugfahrt getTrain() {
		return train;
	}

	public void setTrain(Zugfahrt train) {
		this.train = train;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public Time getZeitAn() {
		return zeitAn;
	}

	public void setZeitAn(Time zeitAn) {
		this.zeitAn = zeitAn;
	}

	public Time getZeitAb() {
		return zeitAb;
	}

	public void setZeitAb(Time zeitAb) {
		this.zeitAb = zeitAb;
	}

	@Override
	public String toString() {
		return getBp() + "\t" + getZeitAn() + " - " + getZeitAb();
	}

}
