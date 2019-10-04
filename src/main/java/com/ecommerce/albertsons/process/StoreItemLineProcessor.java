package com.ecommerce.albertsons.process;

import com.ecommerce.albertsons.domian.StoreItemModel;
import com.ecommerce.albertsons.mapper.StoreItemMapper;
import com.ecommerce.albertsons.model.CsvStoreItem;
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
import org.springframework.beans.factory.annotation.Value;


public class StoreItemLineProcessor
    implements ItemProcessor<CsvStoreItem, CsvStoreItem>, StepExecutionListener {
  
  @Autowired
  private StoreItemService storeItemService;
  
  @Value("${storeItemColumns}")
  String[] storeItemColumns;
  
  private final Logger logger = LoggerFactory.getLogger(StoreItemLineProcessor.class);
  
  @Override
  public void beforeStep(StepExecution stepExecution) {
    logger.info("Line Processor initialized.");
  }
  
  @Override
  public CsvStoreItem process(CsvStoreItem line) throws Exception {
    // do mapping from db
    List<String> cicList = new ArrayList<String>();
    cicList.add(line.getCic());
    List<StoreItemModel> storeItemList = storeItemService.findByCorporateItemCd(cicList);
    if(storeItemList.size()==0){
      return null;
    }
    for (StoreItemModel model : storeItemList) {
      StoreItemMapper mapper = new StoreItemMapper();
      line = mapper.transformStoreItemToCsvStoreItem(model,line,storeItemColumns);
      /*line.setCic(model.getCorporateItemCd());
      line.setItemDescription(model.getItemName());
      line.setItemType(model.getItemType());
      line.setOrderable(model.isOrderable());
      line.setStopBy(model.getStopBuy());
      line.setStoreId(model.getStoreId());
      line.setUpcId(model.getUpcId());*/
    }
    return line;
  }
  
  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    logger.info("Line Processor ended.");
    return ExitStatus.COMPLETED;
  }
}