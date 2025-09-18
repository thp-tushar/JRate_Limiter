package app;

import limiter.RateLimiter;

public class ClientTask implements Runnable {
    private final String clientId;
    private final RateLimiter limiter;

    public ClientTask(String clientId, RateLimiter limiter) {
        this.clientId = clientId;
        this.limiter = limiter;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            boolean allowed = limiter.allowRequest(clientId);
            System.out.println("Client " + clientId + " request " + i + " -> " +
                    (allowed ? "Allowed ✅" : "Blocked ❌"));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
