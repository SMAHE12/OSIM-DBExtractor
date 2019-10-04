package com.ecommerce.albertsons.read.impl;

import com.ecommerce.albertsons.model.CsvItem;
import com.ecommerce.albertsons.util.ItemFileUtils;
import com.ecommerce.albertsons.util.StoreItemFileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Reads CIC From file Used for Reading CIC or UPC from file.
 */
public class ItemLineReader<C> implements ItemReader<CsvItem>, StepExecutionListener {
  
  private final Logger logger = LoggerFactory.getLogger(ItemLineReader.class);
  private ItemFileUtils fu;
  @Value("${upcIdsFile}") String upcIdsFile;
  @Value("${itemColumns}") String[] itemColumns;
  
  @Override
  public void beforeStep(StepExecution stepExecution) {
    //fu = new ItemFileUtils("Upc-Ids.txt");
    fu = new ItemFileUtils(upcIdsFile,itemColumns);
    logger.info("Line Reader initialized.");
  }
  
  @Override
  public CsvItem read() throws Exception {
    logger.debug("-R-");
    CsvItem line = fu.readLine();
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