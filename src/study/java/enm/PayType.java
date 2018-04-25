package study.java.enm;

public enum PayType {
	ACCOUTN_TRANSFER("계좌이체"),
	REMITTANCE("무통장입금"),
	TOSS("토스"),
	PAYCO("페이코"),
	CARD("신용카드"),
	KAKAO_PAY("카카오페이"),
	COUPON("쿠폰"),
	EMPTY("없음");
	
	private String title;
	PayType(String name){
		this.title = name;
	}
	
	public String getTitle() {
		return this.title;
	}
}
