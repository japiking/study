package study.java.enm;

import java.io.IOException;
import java.util.List;

public class MainEnum {
	
	public static void main(String[] args) throws IOException {
		System.out.println("=============================");

		Tablestatus stat = Tablestatus.Y;
		
		System.out.println("LSJ---1>>"+stat.getTbl1());
		System.out.println("LSJ---1>>"+stat.getTbl2());
		
		System.out.println("=============================");
		
		Tablestatus stat2 = Tablestatus.N;
		System.out.println("LSJ---2>>"+stat2.getTbl1());
		System.out.println("LSJ---2>>"+stat2.getTbl2());
		
		System.out.println("=============================");
		
		PayGroup payGp = PayGroup.CARD;
		PayGroup payGp2 = PayGroup.findByPayCode("CARD");
		
		String title = payGp.getTitle();
		
		System.out.println("LSJ---결제수단>>"+title);
		List<String> payList = payGp.getPayList();
		payList.forEach(
				j->{
				System.out.println("결제종류 : "+j);
				});
		
		System.out.println("=============================");
	}
}
