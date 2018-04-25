package study.java.enm;

import java.util.function.Function;

public enum Calculater {
	CALC_A(val -> val),
	CALC_B(val -> val*10),
	CALC_C(val -> val*3),
	CALC_D(val -> 0L);
	
	private Function<Long, Long> expresstion;
	
	Calculater(Function<Long, Long> expresstion) {
		this.expresstion = expresstion;
	}
	
	public long calculater(long val) {
		return expresstion.apply(val);
	}
}
