package demo1;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	
	public static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
	
	public static void producer() throws InterruptedException{
		Random random = new Random();
		while(true){
			Integer value = random.nextInt(100);
			queue.put(value);
			System.out.println("Producer: " + value);
		}
	}
	
	public static void consumer() throws InterruptedException{
		Random random = new Random();
		while(true){
			Thread.sleep(100);
			
			if(random.nextInt(10) == 0){
				Integer value = queue.take();
				
				System.out.println("Take value: " + value + " Queue size: " + queue.size());
			}
		}
	}

}
