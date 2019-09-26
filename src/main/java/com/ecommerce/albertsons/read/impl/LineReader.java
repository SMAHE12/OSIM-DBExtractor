package com.ecommerce.albertsons.read.impl;

import com.ecommerce.albertsons.model.CsvItem;
import com.ecommerce.albertsons.util.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;

public class LineReader implements ItemReader<CsvItem>, StepExecutionListener {
  
  private final Logger logger = LoggerFactory.getLogger(LineReader.class);
  private FileUtils fu;
  
  @Override
  public void beforeStep(StepExecution stepExecution) {
    fu = new FileUtils("Sample_Input-300000.txt");
    logger.debug("Line Reader initialized.");
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
    logger.debug("Line Reader ended.");
    return ExitStatus.COMPLETED;
  }
}