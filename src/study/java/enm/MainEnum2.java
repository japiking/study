package study.java.enm;

public class MainEnum2 {
	public static void main(String[]args) {
		
		Calculater calc = Calculater.CALC_B;
		long tmp = calc.calculater(10);
		
		System.out.println("calc--->>"+tmp);
		
		System.out.println("=================================");
		
		CalculaterJava7 calcJava7 = CalculaterJava7.CALC_B;
		long tmp2 = calcJava7.calculater(50);
		System.out.println("calc--->>"+tmp2);
		
	}
}
