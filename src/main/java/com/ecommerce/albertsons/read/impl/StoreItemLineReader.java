package com.ecommerce.albertsons.read.impl;

import com.ecommerce.albertsons.model.CsvStoreItem;
import com.ecommerce.albertsons.util.StoreItemFileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;

public class StoreItemLineReader implements ItemReader<CsvStoreItem>, StepExecutionListener {
  
  private final Logger logger = LoggerFactory.getLogger(StoreItemLineReader.class);
  private StoreItemFileUtils fu;
  @Value("${cicIdsFile}") String cicIdsFile;
  @Value("${storeItemColumns}") String[] storeItemColumns;
  
  @Override
  public void beforeStep(StepExecution stepExecution) {
    fu = new StoreItemFileUtils(cicIdsFile,storeItemColumns );
    logger.info("Line Reader initialized.");
  }
  
  @Override
  public CsvStoreItem read() throws Exception {
    logger.debug("-R-");
    CsvStoreItem line = fu.readLine();
    if (line != null) logger.debug("Read line: " + line.toString());
    return line;
  }
  
  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    fu.closeReader();
    logger.info("Line Reader ended.");
    return ExitStatus.COMPLETED;
  }
}