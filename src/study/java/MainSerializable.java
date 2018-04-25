package study.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
/**
 * 자바 직렬화 형태의 데이터 교환은 자바 시스템 간의 데이터 교환을 위해서 존재한다.
 * 자바 직렬화는 자바 시스템에서 개발에 최적화되어 있습니다. 
 * 복잡한 데이터 구조의 클래스의 객체라도 직렬화 기본 조건만 지키면 큰 작업 없이 바로 직렬화를 가능합니다. 물론 역직렬화도 마찬가지입니다.
 * 
 * 직렬화 대상 클래스가 변경되더라도 serialVersionUID가 고정되어 같은 값을 사용할 경우에는 클래스 수정하더라도 사용가능
 * 하지만 변수 타입이 변경될 경우에는 에러가 발생합니다.
 * @author S-Jin
 *
 */
public class MainSerializable {

	public static void main(String[] args) throws IOException {
		try {
			Member member = new Member("이상진", "japicel@gmail.com", 36);
			String data = makeData(member);
			
			System.out.println("직렬화 데이터 : "+data);

			System.out.println("========================================================================");
			
			Member member2 = getMember(data);
			System.out.println("직렬화 데이터2 Name  : "+member2.getName());
			System.out.println("직렬화 데이터2 Email : "+member2.getEmail());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 직렬화된 데이터를 읽어옵니다.
	 * @param data
	 * @return
	 */
	private static Member getMember(String data) throws Exception{
		byte[] serializableMember = Base64.getDecoder().decode(data);
		try(ByteArrayInputStream bais = new ByteArrayInputStream(serializableMember)){
			try(ObjectInputStream ois = new ObjectInputStream(bais)){
				
				//역 직렬화된 Member를 읽어온다.
				Object objectMeber = ois.readObject();
				Member member = (Member)objectMeber;
				
				return member;
			}
		}
	}
	
	/**
	 * 직렬화 데이터를 생성합니다.
	 * @param member
	 * @return
	 */
	private static String makeData(Member member){
		// TODO Auto-generated method stub
		byte[] serializableMember = null;
		
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
				oos.writeObject(member);
				serializableMember = baos.toByteArray();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System byte 배열로 생성된 직렬화 데이터를 base64로 인코딩
		String data = Base64.getEncoder().encodeToString(serializableMember);
		
		return data;
	}
}
