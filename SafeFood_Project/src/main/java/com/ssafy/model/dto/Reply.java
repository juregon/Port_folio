package com.ssafy.model.dto;

public class Reply {
	private int rNo;
	private int qNo;
	private String rText;
	private String replyer;
	private String rDate;
	
	public Reply() {
	}

	public Reply(int rNo, int qNo, String rText, String replyer, String rDate) {
		this.rNo = rNo;
		this.qNo = qNo;
		this.rText = rText;
		this.replyer = replyer;
		this.rDate = rDate;
	}

	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public int getqNo() {
		return qNo;
	}

	public void setqNo(int qNo) {
		this.qNo = qNo;
	}

	public String getrText() {
		return rText;
	}

	public void setrText(String rText) {
		this.rText = rText;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public String getrDate() {
		return rDate;
	}

	public void setrDate(String rDate) {
		this.rDate = rDate;
	}

	@Override
	public String toString() {
		return "Reply [rNo=" + rNo + ", qNo=" + qNo + ", rText=" + rText + ", replyer=" + replyer + ", rDate=" + rDate
				+ "]";
	}
}