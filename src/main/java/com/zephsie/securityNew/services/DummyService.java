package com.zephsie.securityNew.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DummyService {
    @Async
    public CompletableFuture<String> doSomething() {
        return CompletableFuture.completedFuture("done");
    }
}
