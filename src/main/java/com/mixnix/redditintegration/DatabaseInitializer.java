package com.mixnix.redditintegration;

import com.mixnix.redditintegration.domain.downloadingTask.DownloadingTask;
import com.mixnix.redditintegration.domain.downloadingTask.DownloadingTaskRepository;
import com.mixnix.redditintegration.domain.downloadingTask.TaskType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final DownloadingTaskRepository downloadingTaskRepository;

    @Transactional
    @Override
    public void run(String... strings){
        downloadingTaskRepository.save(new DownloadingTask(LocalDateTime.now().toEpochSecond(ZoneOffset.MIN),
                TaskType.GOING_BACKWARD));
        downloadingTaskRepository.save(new DownloadingTask(LocalDateTime.now().toEpochSecond(ZoneOffset.MIN),
                TaskType.GOING_FORWARD));
    }
}
