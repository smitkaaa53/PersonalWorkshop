package com.journaldev.java8.future;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class MyOwnApplication {

	public static Boolean getValue()
	{
		int i=0;
		while(i<5)
		{
			if(Thread.currentThread().isInterrupted())
			{
				System.out.println("Exiting as Thread is isInterrupted.");
				return Boolean.FALSE; 
			}
			
			System.out.println("I am called: " + i);
			i++;
			// Simulating a long network call of 1 second in the worst case
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return Boolean.TRUE;
	}

	public static void main(String[] args) 
	{
		boolean result = Boolean.FALSE;
		ScheduledExecutorService schedulerService = Executors.newScheduledThreadPool(5);

		ExecutorService executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
				// This is an unbounded Queue. This should never be used
				// in real life. That is the first step to failure.
				new LinkedBlockingQueue<Runnable>());

		// Instead of using CompletableFuture.supplyAsync, directly create a future from
		// the executor
		Future<Boolean> future = executor.submit(() -> getValue());
		//schedulerService.schedule(() -> future.cancel(true), 100, TimeUnit.MILLISECONDS);
		
		try 
		{
			//result = future.get();
			result = future.get(100,TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | CancellationException | TimeoutException e) {
			System.out.println("TimeOut:" + e.getMessage());
			future.cancel(true);
		}
		finally {
			executor.shutdownNow();
		}
		
		
		// Finally wait for all futures to join
		System.out.println("RESULT: "+ result + "\n\n"+executor.toString());
		
	}
}