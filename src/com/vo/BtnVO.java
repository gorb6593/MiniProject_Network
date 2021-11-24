package com.vo;

public class BtnVO {
	public int btn;	
	public int SEQ;
	
	public BtnVO() {
		
	}

	public BtnVO(int seq, int btn) {
		super();
		this.SEQ = seq;
		this.btn = btn;
	}
	public int getBtn() {
		return btn;
	}

	public void setBtn(int btn) {
		this.btn = btn;
	}

	@Override
	public String toString() {
		return "BtnVO [btn=" + btn + "]";
	}

	public BtnVO(int btn) {
		super();
		this.btn = btn;
	}
	
}
