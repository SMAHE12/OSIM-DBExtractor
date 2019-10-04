package com.ecommerce.albertsons.process;

import com.ecommerce.albertsons.domian.ItemModel;
import com.ecommerce.albertsons.mapper.ItemMapper;
import com.ecommerce.albertsons.model.CsvItem;
import com.ecommerce.albertsons.service.ItemService;

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


public class ItemLineProcessor<C, C1> implements ItemProcessor<CsvItem, CsvItem>, StepExecutionListener {
  
  @Autowired
  private ItemService itemService;
  @Value("${itemColumns}")
  String[] itemColumns;
  
  private final Logger logger = LoggerFactory.getLogger(ItemLineProcessor.class);
  
  @Override
  public void beforeStep(StepExecution stepExecution) {
    logger.info("Item Line Processor initialized.");
  }
  
  @Override
  public CsvItem process(CsvItem line) throws Exception {
    // do mapping from db
    List<String> upcList = new ArrayList<String>();
    upcList.add(line.getUpcId());
    List<ItemModel> itemList = itemService.findByUpcIds(upcList);
    if (itemList.size() == 0) {
      return null;
    }
    
    CsvItem itemLine = null;
    ItemMapper mapper = new ItemMapper();
    for (ItemModel model : itemList) {
      /*itemLine = new CsvItem(model.getCorporateItemCd(),
          model.getUpcId(),
          model.getItemDescription().getInternetItemDsc(),
          model.getProductHierarchy().getProductGroup().getProductGroupCd(),
          model.getProductHierarchy().getProductGroup().getProductGroupNm(),
          model.getProductHierarchy().getProductCategory().getProductCategoryCd(),
          model.getProductHierarchy().getProductCategory().getProductCategoryNm(),
          model.getProductHierarchy().getProductClass().getProductClassCd(),
          model.getProductHierarchy().getProductClass().getProductClassNm(),
          model.getProductHierarchy().getProductClass().getRetailSectionCd(),
          model.getProductHierarchy().getProductSubClassLevel1().getProductSubClassCd(),
          model.getProductHierarchy().getProductSubClassLevel1().getProductSubClassNm(),
          model.getProductHierarchy().getProductSubClassLevel2().getProductSubClassCd(),
          model.getProductHierarchy().getProductSubClassLevel2().getProductSubClassNm());*/
      itemLine = mapper.transformItemToCsvItem(model,line,itemColumns);
    }
    return itemLine;
  }
  
  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    logger.info("Item Line Processor ended.");
    return ExitStatus.COMPLETED;
  }
}