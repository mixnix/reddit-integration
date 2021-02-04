package com.mixnix.redditintegration.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class DownloadingMemesScheduler {

    //todo: remember to populate database with initial DownloadingTask
    //executes every minute
    @Scheduled(cron = "0 * * * * *")
    public void downloadPastMemes() {


    }

    @Scheduled(cron = "0 * * * * *")
    public void downloadNewMemes() {

    }
}
