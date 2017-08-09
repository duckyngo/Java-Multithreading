package demo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class App {
	public static void main(String[] args) {
		Executor exec = Executors.newSingleThreadExecutor();
		
		exec.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Hello from executor");
			}
		});
	}
}
