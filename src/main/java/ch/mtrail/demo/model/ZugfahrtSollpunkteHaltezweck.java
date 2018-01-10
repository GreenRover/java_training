package ch.mtrail.demo.model;

import java.io.Serializable;

public class ZugfahrtSollpunkteHaltezweck implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4678349120307835463L;
	
	
	private Integer bpId;
	private Integer halteZweck;

	public ZugfahrtSollpunkteHaltezweck(Integer bpId, Integer halteZweck) {
		this.bpId = bpId;
		this.halteZweck = halteZweck;
	}

	public Integer getBpId() {
		return bpId;
	}

	public void setBpId(Integer bpId) {
		this.bpId = bpId;
	}

	public Integer getHalteZweck() {
		return halteZweck;
	}

	public void setHalteZweck(Integer halteZweck) {
		this.halteZweck = halteZweck;
	}
}
