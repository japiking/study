package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class TestMain {

	static int index = 0;

	public static void main(String[] args) {

		// 새로운 쓰레드 추가시 출력 문구를 위한 ThreadFactory
		ThreadFactory tf = new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				System.out.println("Runnable"+"["+ index++ +"]"+" is created.");
				return thread;
			}
		};

		// 쓰레드를 5개까지만 만드는 쓰레드풀 생성
		ExecutorService p = Executors.newFixedThreadPool(5, tf);

		// TEST CODE
		try {

			// 아이템 100개를 쓰레드풀에 삽입 및 실행
			for(int i = 0 ; i < 100; i++){
				Runnable r = new TestRunnable(i);
				p.execute(r);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		p.shutdown();
	}
}

// 테스트할 용도의 Runnable 클래스
class TestRunnable implements Runnable{
	int index;
	public TestRunnable(int i) {
		index = i;
	}
	@Override
	public void run() {
		System.out.println(index);
	}
}
