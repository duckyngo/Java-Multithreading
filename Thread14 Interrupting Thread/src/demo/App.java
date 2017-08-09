package demo;

import java.util.Random;

public class App {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting...");
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Random random = new Random();
				
			
				for(int i = 0; i < 1E8; i++){
					/*
					if(Thread.currentThread().isInterrupted()){
						System.out.println("Interrupted!");
						break;
					}*/
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Interrupted!");
					}
					Math.sin(random.nextDouble());
				}
				
			}
		});
		
		t1.start();
		
		Thread.sleep(500);
		
		t1.interrupt();
		
		t1.join();
		
		System.out.println("Finish.");
	}

}
