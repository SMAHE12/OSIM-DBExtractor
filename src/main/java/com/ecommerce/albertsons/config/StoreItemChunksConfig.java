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
  public JobLauncherTestUtils jobLauncherTestUtils() {
    return new JobLauncherTestUtils();
  }
  
  /** jobRepository job repository.
   *
   **/
  @Bean
  public JobRepository jobRepository() throws Exception {
    MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
    factory.setTransactionManager(transactionManager());
    return (JobRepository) factory.getObject();
  }
  
  @Bean
  public PlatformTransactionManager transactionManager() {
    return new ResourcelessTransactionManager();
  }
  
  @Bean
  public JobLauncher jobLauncher() throws Exception {
    SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
    jobLauncher.setJobRepository(jobRepository());
    return jobLauncher;
  }
  
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
  protected Step processLines(ItemReader<CsvStoreItem> reader,
                              ItemProcessor<CsvStoreItem, CsvStoreItem> processor,
                              ItemWriter<CsvStoreItem> writer) {
    return steps.get("processLines").<CsvStoreItem, CsvStoreItem>chunk(100)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build();
  }
  
  @Bean
  public Job job() {
    return jobs
        .get("Fetch_STORE_ITEMS_Job")
        .start(processLines(itemReader(), itemProcessor(), itemWriter()))
        .build();
  }
  
}