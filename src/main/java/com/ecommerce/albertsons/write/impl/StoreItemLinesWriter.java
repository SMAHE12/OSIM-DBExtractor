package com.ecommerce.albertsons.write.impl;

import com.ecommerce.albertsons.model.CsvStoreItem;
import com.ecommerce.albertsons.util.StoreItemFileUtils;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;


public class StoreItemLinesWriter implements ItemWriter<CsvStoreItem>, StepExecutionListener {
  
  private final Logger logger = LoggerFactory.getLogger(StoreItemLinesWriter.class);
  private StoreItemFileUtils fu;
  @Value("${storeItemsOutputFile}") String storeItemsOutputFile;
  
  @Override
  public void beforeStep(StepExecution stepExecution) {
    //fu = new StoreItemFileUtils("E:\\SafeWay\\OSIM-DB\\OSIM-DBExtractor\\output.csv");
    fu = new StoreItemFileUtils(storeItemsOutputFile);
    logger.debug("Line Writer initialized.");
  }
  
  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    fu.closeWriter();
    logger.debug("Line Writer ended.");
    return ExitStatus.COMPLETED;
  }
  
  @Override
  public void write(List<? extends CsvStoreItem> lines) throws Exception {
    
    for (CsvStoreItem line : lines) {
      //logger.info("-W-" + line.getCic());
      fu.writeLine(line);
      logger.debug("Wrote line " + line.toString());
    }
  //  fu.closeWriter();
  }
}
