package com.ecommerce.albertsons.process;

import com.ecommerce.albertsons.domian.StoreItemModel;
import com.ecommerce.albertsons.model.CsvItem;
import com.ecommerce.albertsons.service.StoreItemService;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;


public class LineProcessor implements ItemProcessor<CsvItem, CsvItem>, StepExecutionListener {
  
  @Autowired
  private StoreItemService storeItemService;
  
  private final Logger logger = LoggerFactory.getLogger(LineProcessor.class);
  
  @Override
  public void beforeStep(StepExecution stepExecution) {
    logger.debug("Line Processor initialized.");
  }
  
  @Override
  public CsvItem process(CsvItem line) throws Exception {
    // do mapping from db
    List<String> cicList = new ArrayList<String>();
    cicList.add(line.getCic());
    List<StoreItemModel> storeItemList = storeItemService.findByCorporateItemCd(cicList);
    if(storeItemList.size()==0){
      return null;
    }
    for (StoreItemModel model : storeItemList) {
      line.setCic(model.getCorporateItemCd());
      line.setItemDescription("None");
      line.setItemType(model.getItemType());
      line.setOrderable(model.isOrderable());
      line.setStopBy(model.getStopBuy());
      line.setStoreId(model.getStoreId());
      line.setUpcId(model.getUpcId());
    }
    return line;
  }
  
  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    logger.debug("Line Processor ended.");
    return ExitStatus.COMPLETED;
  }
}