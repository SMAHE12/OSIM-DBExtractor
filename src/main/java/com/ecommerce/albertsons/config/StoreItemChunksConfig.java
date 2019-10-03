package com.ecommerce.albertsons.config;

import com.ecommerce.albertsons.model.CsvStoreItem;
import com.ecommerce.albertsons.process.StoreItemLineProcessor;
import com.ecommerce.albertsons.read.impl.StoreItemLineReader;
import com.ecommerce.albertsons.write.impl.StoreItemLinesWriter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StoreItemChunksConfig {
  
  @Autowired
  private JobBuilderFactory jobs;
  
  @Autowired
  private StepBuilderFactory steps;
  
  @Bean
  public ItemReader<CsvStoreItem> itemReader() {
    return new StoreItemLineReader();
  }
  
  @Bean
  public ItemProcessor<CsvStoreItem, CsvStoreItem> itemProcessor() {
    return new StoreItemLineProcessor();
  }
  
  @Bean
  public ItemWriter<CsvStoreItem> itemWriter() {
    return new StoreItemLinesWriter();
  }
  
  @Bean
  public Step processStoreItemLines(ItemReader<CsvStoreItem> reader,
                              ItemProcessor<CsvStoreItem, CsvStoreItem> processor,
                              ItemWriter<CsvStoreItem> writer) {
    return steps.get("processStoreItemLines").<CsvStoreItem, CsvStoreItem>chunk(100)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build();
  }
  
  
}