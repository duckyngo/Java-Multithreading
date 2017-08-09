package demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
	
	private int id;
	
	public Processor(int id){
		this.id = id;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Starting: " + id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completing: " + id);
		
	}
	
	
	
}

public class App {
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		 for(int i = 0; i < 10; i++){
			 executorService.submit(new Processor(i));
		 }
		 
		 executorService.shutdown();
		 System.out.println("All tasks summitted.");
		 try {
			executorService.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		 System.out.println("All talks completed.");
		 
	}
	 
}
