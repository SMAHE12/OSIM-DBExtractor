package com.ecommerce.albertsons.write.impl;

import com.ecommerce.albertsons.model.CsvItem;
import com.ecommerce.albertsons.util.ItemFileUtils;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;


public class ItemLinesWriter<C> implements ItemWriter<List<CsvItem>>, StepExecutionListener {
  
  private final Logger logger = LoggerFactory.getLogger(ItemLinesWriter.class);
  private ItemFileUtils itemFileUtil;
  @Value("${itemsOutputFile}") String itemsOutputFile;
  @Value("${itemColumns}") String[] itemColumns;
  
  @Override
  public void beforeStep(StepExecution stepExecution) {
    //itemFileUtil = new ItemFileUtils(
     //   "E:\\SafeWay\\OSIM-DB\\OSIM-DBExtractor\\item-output.csv");
    itemFileUtil = new ItemFileUtils(itemsOutputFile,itemColumns);
    logger.info("Line Writer initialized.");
  }
  
  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    itemFileUtil.closeWriter();
    logger.debug("Line Writer ended.");
    return ExitStatus.COMPLETED;
  }
  
  @Override
  public void write(List<? extends List<CsvItem>> lines) throws Exception {
    //logger.info(" Item Writer :::: ");
    for (List<CsvItem> multiLine : lines) {
      for(CsvItem line : multiLine){
        itemFileUtil.writeLine(line);
        logger.info("Item data for id: "+line.get_id()+" written ");
      }
      //logger.info("-W-" + line.getCic());
    }
    //itemFileUtil.closeWriter();
  }
}
