package com.verizon;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created by user on 6/21/16.
 */
public class FetchUrl {
    private static String URL;
    private static int NUMBER_OF_TIMES;
    private static int NUMBER_OF_THREADS;

    public static void main(String args[]) {
        System.out.println(args.length);
        System.out.println(Arrays.toString(args));
        Preconditions.checkArgument(args.length == 3, "Please check the params to the script. It shld be fetch_url.sh URL Number Threads");
        URL = args[0];
        NUMBER_OF_TIMES = Integer.valueOf(args[1]);
        NUMBER_OF_THREADS = Integer.valueOf(args[2]);


        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            UrlRequestor urlRequestor = new UrlRequestor("thread " + i, NUMBER_OF_TIMES, URL);
            executor.execute(urlRequestor);
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Maximum threads inside pool " + Stats.totalRequest.get());
        System.out.println("Maximum threads inside pool " + Stats.totalTimeSpent.get());
        System.out.println("Maximum threads inside pool " + Stats.totalRequest.get()*1000 / Stats.totalTimeSpent.get());
        Stats.numberOfResponsesPerSec.set((Stats.totalRequest.get()*1000 / Stats.totalTimeSpent.get()));
        System.out.println("Maximum threads inside pool " + Stats.print());
    }
}
