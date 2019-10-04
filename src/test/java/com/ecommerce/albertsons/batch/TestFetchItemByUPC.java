package com.ecommerce.albertsons.batch;

import com.ecommerce.albertsons.DbExtractApplication;
import com.ecommerce.albertsons.config.ItemChunksConfig;
import com.ecommerce.albertsons.config.StoreItemChunksConfig;
import com.ecommerce.albertsons.service.ItemService;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ItemChunksConfig.class)
@SpringBootTest(classes = DbExtractApplication.class)
public class TestFetchItemByUPC {
  
  @Autowired
  private JobLauncherTestUtils jobLauncherTestUtils;
  @Autowired
  private ItemService itemService;
  
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
  
  @Bean
  public JobLauncher jobLauncher(ThreadPoolTaskExecutor taskExecutor) throws Exception {
    SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
    jobLauncher.setTaskExecutor(taskExecutor);
    jobLauncher.setJobRepository(jobRepository());
    return jobLauncher;
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
  
  @Test
  @Ignore
  public void givenChunksJob_WhenJobEnds_ThenStatusCompleted() throws Exception {
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    Assert.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
  }
}