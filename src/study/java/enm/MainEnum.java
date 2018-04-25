package study.java.enm;

import java.io.IOException;

public class MainEnum {
	
	public static void main(String[] args) throws IOException {
		Tablestatus stat = Tablestatus.Y;
		
		System.out.println("LSJ--->>"+stat.getTbl1());
		System.out.println("LSJ--->>"+stat.getTbl2());
		
		System.out.println("=============================");
		
		Tablestatus stat2 = Tablestatus.N;
		System.out.println("LSJ--->>"+stat2.getTbl1());
		System.out.println("LSJ--->>"+stat2.getTbl2());
	}
}
