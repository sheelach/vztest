package com.verizon;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by user on 6/21/16.
 */
public  class Stats {
    static AtomicLong totalRequest = new AtomicLong();

    static AtomicLong totalTimeSpent= new AtomicLong();
    static AtomicLong numberOfResponsesPerSec= new AtomicLong();



    public static String print() {
        return "Summary:" +
                "total number of request=" + totalRequest +
                ", total time spent =" + totalTimeSpent +" milli seconds"+
                ", number of reponses per sec =" + numberOfResponsesPerSec;
    }
}
