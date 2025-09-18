package limiter;

import java.util.concurrent.*;
import java.util.*;

public class TokenBucketRateLimiter implements RateLimiter {
    private final int maxTokens;
    private final int refillRatePerSecond;
    private final Map<String, Integer> tokenBucket = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public TokenBucketRateLimiter(int maxTokens, int refillRatePerSecond) {
        this.maxTokens = maxTokens;
        this.refillRatePerSecond = refillRatePerSecond;

        // Refill tokens every second
        scheduler.scheduleAtFixedRate(this::refillTokens, 0, 1, TimeUnit.SECONDS);
    }

    private void refillTokens() {
        for (String client : tokenBucket.keySet()) {
            tokenBucket.compute(client, (k, v) -> Math.min(maxTokens, v + refillRatePerSecond));
        }
    }

    @Override
    public synchronized boolean allowRequest(String clientId) {
        tokenBucket.putIfAbsent(clientId, maxTokens);
        int tokens = tokenBucket.get(clientId);
        if (tokens > 0) {
            tokenBucket.put(clientId, tokens - 1);
            return true;
        }
        return false;
    }
}
