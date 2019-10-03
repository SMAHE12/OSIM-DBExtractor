package com.ecommerce.albertsons;

import com.ecommerce.albertsons.config.ItemChunksConfig;
import com.ecommerce.albertsons.config.StoreItemChunksConfig;
import com.ecommerce.albertsons.model.CsvStoreItem;
import com.ecommerce.albertsons.repository.StoreItemRepository;
import com.ecommerce.albertsons.service.ItemService;
import com.ecommerce.albertsons.service.StoreItemService;

import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

/*
 * @author Nisum.
 */
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {StoreItemRepository.class})
@Slf4j
@EnableBatchProcessing
public class DbExtractApplication {
  
  @Autowired
  private JobBuilderFactory jobs;
  
  @Autowired
  private StepBuilderFactory steps;
  
  @Autowired
  private StoreItemChunksConfig storeItemChunksConfig;
  
  @Autowired
  private ItemChunksConfig itemChunksConfig;
  
  @Autowired
  @Qualifier("jobStoreItems")
  private Job jobStoreItems;
  
  @Autowired
  @Qualifier("jobItems")
  private Job jobItems;
  
  public static void main(String[] args) {
    SpringApplication.run(DbExtractApplication.class, args);
    
  }
  
  @Bean
  public ThreadPoolTaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(15);
    taskExecutor.setMaxPoolSize(20);
    taskExecutor.setQueueCapacity(30);
    return taskExecutor;
  }
  
  /**
   * jobRepository job repository.
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
  public JobLauncher jobLauncher(ThreadPoolTaskExecutor taskExecutor) throws Exception {
    SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
    jobLauncher.setTaskExecutor(taskExecutor);
    jobLauncher.setJobRepository(jobRepository());
    return jobLauncher;
  }
  
  @Bean
  public Job jobStoreItems() {
    return jobs
        .get("Fetch_STORE_ITEMS_Job")
        .start(storeItemChunksConfig.processStoreItemLines(storeItemChunksConfig.itemReader(),
            storeItemChunksConfig.itemProcessor(), storeItemChunksConfig.itemWriter()))
        .build();
  }
  @Bean
  public Job jobItems() {
    return jobs
        .get("Fetch_ITEMS_Job")
        .start(itemChunksConfig.processItemLines(
            itemChunksConfig.itemLineReader(),
            itemChunksConfig.itemLineProcessor(),
            itemChunksConfig.itemLineWriter()))
        .build();
  }
  
  
}
