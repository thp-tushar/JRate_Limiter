package limiter;

public interface RateLimiter {
    boolean allowRequest(String clientId);
}
