package study.Lamda;

import java.util.ArrayList;

public class RunnableTest {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		
		System.out.println("Start Thread!!");
		
		Runnable r = new foo();
		ArrayList<Thread> ar = new ArrayList<Thread>();
		
		for(int i=0; i<10; i++){
			Thread test = new Thread(r);
			test.start();
			ar.add(test);
		}
		
		for(Thread t : ar){
			t.join();
		}
		
		
		System.out.println("End Thread!!");
	}

}
