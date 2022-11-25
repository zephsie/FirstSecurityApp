package com.zephsie.securityNew.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WorkSchedule {
    @Scheduled(initialDelay = 1000, fixedDelayString = "${workSchedule.fixedDelay}")
    public void runFirst() {
        log.info("First task");
    }

    @Scheduled(initialDelay = 1000, fixedRateString = "${workSchedule.fixedRate}")
    public void runSecond() {
        log.info("Second task");
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void runThird() {
        log.info("Third task");
    }
}