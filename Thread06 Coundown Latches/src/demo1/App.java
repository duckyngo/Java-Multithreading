package demo1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{
	private CountDownLatch latch;
	
	public Processor(CountDownLatch latch){
		this.latch = latch;
	}
	
	public void run(){
		System.out.println("Started.");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finish...");
		latch.countDown();
	}
	
}

public class App {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(12);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for(int i = 0; i< 12; i++){
			new Thread(new Processor(latch)).start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Finish waiting.");
	}
}
