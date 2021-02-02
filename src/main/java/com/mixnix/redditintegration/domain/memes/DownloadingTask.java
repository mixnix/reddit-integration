package com.mixnix.redditintegration.domain.memes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DownloadingTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long recentlyDownloadedMemeUtc;

    private TaskType taskType;
}
