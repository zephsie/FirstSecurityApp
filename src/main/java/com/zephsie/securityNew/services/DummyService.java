package com.zephsie.securityNew.services;

import com.zephsie.securityNew.util.Loggable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class DummyService {
    @Async
    @Loggable
    public CompletableFuture<String> doSomething() {
        log.info("Doing something");
        return CompletableFuture.completedFuture("done");
    }
}
