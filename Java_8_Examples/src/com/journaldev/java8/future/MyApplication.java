package com.journaldev.java8.future;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class MyApplication {

	public static Integer getValue() {
		System.out.println("I am called");

		// Simulating a long network call of 1 second in the worst case
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 10;
	}

	public static void main(String[] args) 
	{
		ScheduledExecutorService schedulerService = Executors.newScheduledThreadPool(5);

		ExecutorService executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
				// This is an unbounded Queue. This should never be used
				// in real life. That is the first step to failure.
				new LinkedBlockingQueue<Runnable>());

		// We want to call the dummy service 10 times
		@SuppressWarnings("rawtypes")
		CompletableFuture[] allFutures = new CompletableFuture[10];

		for (int i = 0; i < 10; ++i)
		{
			allFutures[i] = CompletableFuture.supplyAsync(() -> {
				// Instead of using CompletableFuture.supplyAsync, directly create a future from
				// the executor
				Future<Integer> future = executor.submit(() -> getValue());
				schedulerService.schedule(() -> future.cancel(true), 100, TimeUnit.MILLISECONDS);

				try 
				{
					return future.get();
				} catch (InterruptedException | ExecutionException | CancellationException e) {
					// pass
				}
				// You can choose to return a dummy value here
				return null;
			});
		}

		// Finally wait for all futures to join
		CompletableFuture.allOf(allFutures).join();
		System.out.println("All futures completed");
		System.out.println(executor.toString());
	}
}