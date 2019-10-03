package com.ecommerce.albertsons.write.impl;

import com.ecommerce.albertsons.model.CsvItem;
import com.ecommerce.albertsons.util.ItemFileUtils;
import com.ecommerce.albertsons.util.StoreItemFileUtils;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;


public class ItemLinesWriter<C> implements ItemWriter<CsvItem>, StepExecutionListener {
  
  private final Logger logger = LoggerFactory.getLogger(ItemLinesWriter.class);
  private ItemFileUtils itemFileUtil;
  @Value("${itemsOutputFile}") String itemsOutputFile;
  
  @Override
  public void beforeStep(StepExecution stepExecution) {
    //itemFileUtil = new ItemFileUtils(
     //   "E:\\SafeWay\\OSIM-DB\\OSIM-DBExtractor\\item-output.csv");
    itemFileUtil = new ItemFileUtils(itemsOutputFile);
    logger.info("Line Writer initialized.");
  }
  
  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    itemFileUtil.closeWriter();
    logger.debug("Line Writer ended.");
    return ExitStatus.COMPLETED;
  }
  
  @Override
  public void write(List<? extends CsvItem> lines) throws Exception {
    //logger.info(" Item Writer :::: ");
    for (CsvItem line : lines) {
      //logger.info("-W-" + line.getCic());
      itemFileUtil.writeLine(line);
      logger.info("Wrote line " + line.toString());
    }
    //itemFileUtil.closeWriter();
  }
}
