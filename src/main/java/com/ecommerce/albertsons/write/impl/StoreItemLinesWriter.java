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


public class StoreItemLinesWriter implements ItemWriter<List<CsvStoreItem>>, StepExecutionListener {
  
  private final Logger logger = LoggerFactory.getLogger(StoreItemLinesWriter.class);
  private StoreItemFileUtils fu;
  @Value("${storeItemsOutputFile}") String storeItemsOutputFile;
  @Value("${storeItemColumns}") String[] storeItemColumns;
  
  @Override
  public void beforeStep(StepExecution stepExecution) {
    //fu = new StoreItemFileUtils("E:\\SafeWay\\OSIM-DB\\OSIM-DBExtractor\\output.csv");
    fu = new StoreItemFileUtils(storeItemsOutputFile,storeItemColumns);
    logger.info("Line Writer initialized.");
  }
  
  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    fu.closeWriter();
    logger.info("Line Writer ended.");
    return ExitStatus.COMPLETED;
  }
  
  @Override
  public void write(List<? extends List<CsvStoreItem>> lines) throws Exception {
    
    for(List<CsvStoreItem> multiLines : lines){
      for (CsvStoreItem line : multiLines) {
        //logger.info("-W-" + line.getCic());
        fu.writeLine(line);
        logger.info("Wrote line For UPC  : " + line.toString());
      }
    }
  }
}
