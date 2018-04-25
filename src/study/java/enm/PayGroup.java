package study.java.enm;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public enum PayGroup {
	CASH("현금",  Arrays.asList("ACCOUNT_TRANSFER", "REMITTANCE", "ON_SITE_PAYMENT", "TOSS")),
	CARD("카드",  Arrays.asList("PAYCO", "KAKAO_PAY", "CHECK_PAY")),
	ETC("기타",   Arrays.asList("POINT", "COUPON")),
	EMPTY("없음", Collections.EMPTY_LIST);
	
	private String title;
	private List<String> payList;
	
	PayGroup(String title, List<String> payList){
		this.title = title;
		this.payList = payList;
	}
	
	/**
	 * Java8 stream, Lamda
	 * @param code
	 * @return
	 */
	public static PayGroup findByPayCode(String code) {
		return Arrays.stream(PayGroup.values())
		.filter(payGroup -> payGroup.hasPayCode(code))
		.findAny()
		.orElse(EMPTY);
	}
	
	/**
	 * Java8 stream
	 * @param code
	 * @return
	 */
	public boolean hasPayCode(String code){
	    return payList.stream().anyMatch(pay -> pay.equals(code));
	}
	public String getTitle() {
		return this.title;
	}
	public List<String> getPayList(){
		return this.payList;
	}
}
