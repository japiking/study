package study.java.enm;

public enum Tablestatus {
	Y("1", true),
	N("0", false);
	
	private String tbl1;
	private boolean tbl2;
	
	Tablestatus(String tbl1, boolean tbl2){
		this.tbl1 = tbl1;
		this.tbl2 = tbl2;
	}

	public String getTbl1() {
		return tbl1;
	}


	public boolean isTbl2() {
		return tbl2;
	}
}
