package demo1;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class App {
	
	private volatile int count = 0;
	
	public void increase(){
		count++;
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.doWork();
	}
	
	public void doWork(){
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				for(int i = 0; i < 10000; i++){
					increase();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				for(int i = 0; i < 10000; i++){
					increase();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Count is: " + count);
	}
}
