package demo;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	Lock lock1 = new ReentrantLock();
	Lock lock2 = new ReentrantLock();
	
	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException{
		while(true){
			// Acquire locks
			
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			
			try{
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
				
			}finally{
				if(gotFirstLock && gotSecondLock){
					return;
				}
				
				if(gotFirstLock){
					firstLock.unlock();
				}
				
				if(gotSecondLock){
					secondLock.unlock();
				}
			}
			
			// Lock not acquired
			Thread.sleep(1);
		}
	}
	

	public void firstThread() throws InterruptedException{
		Random ran = new Random();
		for(int i = 0; i< 10000; i++){
//			lock1.lock();
//			lock2.lock();
			acquireLocks(lock1, lock2);
			
			try{
				Account.transfer(acc1, acc2, ran.nextInt(100));
			}finally{
				lock1.unlock();
				lock2.unlock();
			}
		}

	}
	
	public void secondThread() throws InterruptedException{
		Random ran = new Random();
		for(int i = 0; i< 10000; i++){
//			Dead lock happen when we doing a lock in different order and if 
//			Subprogram just waiting and it's not doing anything.
//			lock2.lock();
//			lock1.lock();

//			lock1.lock();
//			lock2.lock();
			
			acquireLocks(lock1, lock2);
			try{
				Account.transfer(acc2, acc1, ran.nextInt(100));
			}finally{
				lock1.unlock();
				lock2.unlock();
			}
		}
		
	}
	
	public void finished(){
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
}
