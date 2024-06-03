package com.batch.example.SpringBatchExample.config;

import com.batch.example.SpringBatchExample.listener.JobCompletionNotificationImpl;
import com.batch.example.SpringBatchExample.model.Sales;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

//    private final JobCompletionNotificationImpl jobCompletionNotification;
    @Bean
    public Job jobBean(
            JobRepository jobRepository,
            JobCompletionNotificationImpl jobCompletionNotification,
            Step steps
            ){

        return new JobBuilder("job",jobRepository)
                .listener(jobCompletionNotification)
                .start(steps)
                .build();
    }

    @Bean
    public Step steps(JobRepository jobRepository,
                      DataSourceTransactionManager transactionManager,
                      ItemReader<Sales> itemReader){

        return new StepBuilder("jobStep",jobRepository)
                .chunk(5,transactionManager)
                .reader(itemReader)
                .processor()
                .writer()
                .build();
    }

    @Bean
    public FlatFileItemReader<Sales> reader(){

        List<String> fileHeaderValueList = Arrays.asList("Transaction ID", "Date", "Product Category", "Product Name", "Units Sold", "Unit Price", "Total Revenue", "Region", "Payment Method");
        return new FlatFileItemReaderBuilder<Sales>()
                .name("item reader")
                .resource(new ClassPathResource("Online Sales Data.csv"))
                .delimited()
                .names(String.valueOf(fileHeaderValueList))
                .targetType(Sales.class)
                .build();
    }
}
