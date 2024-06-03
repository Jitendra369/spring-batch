package com.batch.example.SpringBatchExample.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobCompletionNotificationImpl implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("job started at {} and start time {}", jobExecution.getStatus(), jobExecution.getStartTime());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
//        JobExecutionListener.super.afterJob(jobExecution);
        if (jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.info("job is completed at {} ", jobExecution.getStartTime());
        }
    }
}
