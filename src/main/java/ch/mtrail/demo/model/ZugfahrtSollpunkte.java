package ch.mtrail.demo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ZugfahrtSollpunkte {

	private Integer bpId;
	private Zugfahrt zugfahrt;
	private byte position;
	private String bp;
	private Date zeitAn;
	private Date zeitAb;
	private Set<Long> haltezwecke = new HashSet<>();

	public ZugfahrtSollpunkte() {
	}

	public ZugfahrtSollpunkte(Zugfahrt zugfahrt, byte position, String bp, Date zeitAn, Date zeitAb) {
		this.zugfahrt = zugfahrt;
		this.position = position;
		this.bp = bp;
		this.zeitAn = zeitAn;
		this.zeitAb = zeitAb;
	}

	public ZugfahrtSollpunkte(Zugfahrt zugfahrt, byte position, String bp, Date zeitAn, Date zeitAb,
			Set<Long> haltezwecke) {
		this.zugfahrt = zugfahrt;
		this.position = position;
		this.bp = bp;
		this.zeitAn = zeitAn;
		this.zeitAb = zeitAb;
		this.haltezwecke = haltezwecke;
	}

	public Integer getBpId() {
		return this.bpId;
	}

	public void setBpId(Integer bpId) {
		this.bpId = bpId;
	}

	public Zugfahrt getZugfahrt() {
		return this.zugfahrt;
	}

	public void setZugfahrt(Zugfahrt zugfahrt) {
		this.zugfahrt = zugfahrt;
	}

	public byte getPosition() {
		return this.position;
	}

	public void setPosition(byte position) {
		this.position = position;
	}

	public String getBp() {
		return this.bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public Date getZeitAn() {
		return this.zeitAn;
	}

	public void setZeitAn(Date zeitAn) {
		this.zeitAn = zeitAn;
	}

	public Date getZeitAb() {
		return this.zeitAb;
	}

	public void setZeitAb(Date zeitAb) {
		this.zeitAb = zeitAb;
	}

	public Set<Long> getHaltezwecke() {
		return haltezwecke;
	}

	public void setHaltezwecke(Set<Long> haltezwecke) {
		this.haltezwecke = haltezwecke;
	}

}
