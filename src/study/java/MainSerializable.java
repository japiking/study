package study.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
/**
 * �ڹ� ����ȭ ������ ������ ��ȯ�� �ڹ� �ý��� ���� ������ ��ȯ�� ���ؼ� �����Ѵ�.
 * �ڹ� ����ȭ�� �ڹ� �ý��ۿ��� ���߿� ����ȭ�Ǿ� �ֽ��ϴ�. 
 * ������ ������ ������ Ŭ������ ��ü�� ����ȭ �⺻ ���Ǹ� ��Ű�� ū �۾� ���� �ٷ� ����ȭ�� �����մϴ�. ���� ������ȭ�� ���������Դϴ�.
 * 
 * ����ȭ ��� Ŭ������ ����Ǵ��� serialVersionUID�� �����Ǿ� ���� ���� ����� ��쿡�� Ŭ���� �����ϴ��� ��밡��
 * ������ ���� Ÿ���� ����� ��쿡�� ������ �߻��մϴ�.
 * @author S-Jin
 *
 */
public class MainSerializable {

	public static void main(String[] args) throws IOException {
		try {
			Member member = new Member("�̻���", "japicel@gmail.com", 36);
			String data = makeData(member);
			
			System.out.println("����ȭ ������ : "+data);

			System.out.println("========================================================================");
			
			Member member2 = getMember(data);
			System.out.println("����ȭ ������2 Name  : "+member2.getName());
			System.out.println("����ȭ ������2 Email : "+member2.getEmail());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ����ȭ�� �����͸� �о�ɴϴ�.
	 * @param data
	 * @return
	 */
	private static Member getMember(String data) throws Exception{
		byte[] serializableMember = Base64.getDecoder().decode(data);
		try(ByteArrayInputStream bais = new ByteArrayInputStream(serializableMember)){
			try(ObjectInputStream ois = new ObjectInputStream(bais)){
				
				//�� ����ȭ�� Member�� �о�´�.
				Object objectMeber = ois.readObject();
				Member member = (Member)objectMeber;
				
				return member;
			}
		}
	}
	
	/**
	 * ����ȭ �����͸� �����մϴ�.
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
		
		//System byte �迭�� ������ ����ȭ �����͸� base64�� ���ڵ�
		String data = Base64.getEncoder().encodeToString(serializableMember);
		
		return data;
	}
}
