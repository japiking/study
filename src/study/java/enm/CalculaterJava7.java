package study.java.enm;

public enum CalculaterJava7 {
	CALC_A{
		long calculater(long val) {
			return val;
		}
	},
	CALC_B{
		long calculater(long val) {
			return val-10;
		}
	},
	CALC_C{
		long calculater(long val) {
			return val*10;
		}
	};
	
	abstract long calculater(long val);
}
