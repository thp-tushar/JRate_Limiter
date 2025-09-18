package app;

import limiter.*;

public class RateLimiterDemo {
    public static void main(String[] args) {
        RateLimiter limiter = new TokenBucketRateLimiter(5, 2);

        Thread client1 = new Thread(new ClientTask("A", limiter));
        Thread client2 = new Thread(new ClientTask("B", limiter));

        client1.start();
        client2.start();
    }
}
