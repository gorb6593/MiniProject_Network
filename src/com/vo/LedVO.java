package com.vo;

public class LedVO {
	public int SEQ;
	public String led;
	
	//기본생성자
	public LedVO() {
		
	}
	
	public LedVO(int sEQ, String led) {
		super();
		this.SEQ = sEQ;
		this.led = led;
	}

	//using field
	public LedVO(String led) {
		super();
		this.led = led;
	}
	//get,set메소드
	public String getLed() {
		return led;
	}

	public void setLed(String led) {
		this.led = led;
	}
	//tostring
	@Override
	public String toString() {
		return "LedVO [led=" + led + "]";
	}
	
}
