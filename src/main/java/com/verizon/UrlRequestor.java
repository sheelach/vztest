package com.verizon;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by user on 6/21/16.
 */
public class UrlRequestor implements Runnable {

    private final String name;
    private final int numberOfTimes;
    private final String url;
    private final static Client client = Client.create();

    public UrlRequestor(String name, int numberOfTimes, String url) {
        this.name = name;
        this.numberOfTimes = numberOfTimes;
        this.url = url;
    }

    public void run() {
        for (int i = 0; i < numberOfTimes; i++) {
            long startTime = System.currentTimeMillis();
            WebResource webResource = client.resource(url);
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            } else {
                long estimatedTime = System.currentTimeMillis() - startTime;
                Stats.totalTimeSpent.set(Stats.totalTimeSpent.get()+estimatedTime);
                Stats.totalRequest.set(Stats.totalRequest.incrementAndGet());

                System.out.println(this.name +", Request:" + i+"," + "HTTP 200 , " + estimatedTime+" milliseconds");
            }

        }
    }
}
