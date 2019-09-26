package com.ecommerce.albertsons.write.impl;

import com.ecommerce.albertsons.model.CsvItem;
import com.ecommerce.albertsons.util.FileUtils;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;



public class LinesWriter implements ItemWriter<CsvItem>, StepExecutionListener {
  
  private final Logger logger = LoggerFactory.getLogger(LinesWriter.class);
  private FileUtils fu;
  
  @Override
  public void beforeStep(StepExecution stepExecution) {
    fu = new FileUtils("E:\\SafeWay\\STATEMACHINE_FINAL_REPO\\OSIMDBExtractor\\output.csv");
    logger.debug("Line Writer initialized.");
  }
  
  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    fu.closeWriter();
    logger.debug("Line Writer ended.");
    return ExitStatus.COMPLETED;
  }
  
  @Override
  public void write(List<? extends CsvItem> lines) throws Exception {
    
    for (CsvItem line : lines) {
      //logger.info("-W-" + line.getCic());
      fu.writeLine(line);
      logger.debug("Wrote line " + line.toString());
    }
  //  fu.closeWriter();
  }
}
