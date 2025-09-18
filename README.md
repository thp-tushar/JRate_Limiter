# Java Rate Limiter Project

This project implements a **Token Bucket Rate Limiter** in Java.
It controls how many requests a client can make per second using **multithreading** and **ScheduledExecutorService**.

## Features
- Token Bucket Algorithm
- Concurrent client simulation
- Scheduled token refill
- Thread-safe implementation

## How to Run
1. Compile the project:
   ```bash
   javac src/limiter/*.java src/app/*.java
   ```

2. Run the demo:
   ```bash
   java -cp src app.RateLimiterDemo
   ```
