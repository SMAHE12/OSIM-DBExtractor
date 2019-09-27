package com.ecommerce.albertsons.batch;

import com.ecommerce.albertsons.DbExtractApplication;
import com.ecommerce.albertsons.config.ItemChunksConfig;
import com.ecommerce.albertsons.service.ItemService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ItemChunksConfig.class)
@SpringBootTest(classes = DbExtractApplication.class)
public class TestFetchItemByUPC {
  
  @Autowired
  private JobLauncherTestUtils jobLauncherTestUtils;
  @Autowired
  private ItemService itemService;
  
  @Test
  public void givenChunksJob_WhenJobEnds_ThenStatusCompleted() throws Exception {
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    Assert.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
  }
}