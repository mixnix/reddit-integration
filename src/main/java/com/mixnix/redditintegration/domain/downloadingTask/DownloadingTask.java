package com.mixnix.redditintegration.domain.downloadingTask;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class DownloadingTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long recentlyDownloadedMemeUtc;

    private TaskType taskType;

    public DownloadingTask(Long recentlyDownloadedMemeUtc, TaskType taskType) {
        this.recentlyDownloadedMemeUtc = recentlyDownloadedMemeUtc;
        this.taskType = taskType;
    }
}
