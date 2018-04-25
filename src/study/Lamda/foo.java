package study.Lamda;

import java.util.Random;

public class foo implements Runnable{
	private int index = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random r = new Random(System.currentTimeMillis());
		
		long s = r.nextInt(3000);
		
		try{
			Thread.sleep(s);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		addIndex();
	}
	
	
	synchronized void addIndex(){
		index++;
		System.out.println("LSJ--->>>"+index);
	}
}
