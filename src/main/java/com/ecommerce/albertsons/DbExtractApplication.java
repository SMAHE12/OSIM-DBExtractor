package com.ecommerce.albertsons;

import com.ecommerce.albertsons.domian.StoreItemModel;
import com.ecommerce.albertsons.model.CsvItem;
import com.ecommerce.albertsons.read.impl.TextReader;
import com.ecommerce.albertsons.repository.StoreItemRepository;
import com.ecommerce.albertsons.service.StoreItemService;
import com.ecommerce.albertsons.write.impl.TextWriter;

import java.util.List;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/*
 * @author Nisum.
 */
@SpringBootApplication
//@EnableReactiveMongoRepositories(basePackageClasses = StoreItemRepository.class)
@EnableMongoRepositories(basePackageClasses = StoreItemRepository.class)
@Slf4j
public class DbExtractApplication implements CommandLineRunner {
  
  @Autowired
  private StoreItemService storeItemService;
  
  public static void main(String[] args) {
    SpringApplication.run(DbExtractApplication.class, args);
  }
  
  @Override
  public void run(String... args) throws Exception {
    /* long startTime = System.currentTimeMillis();
    TextReader reader = new TextReader();
    TextWriter writer = new TextWriter();
    
   List<String> cicList = reader
        .readTextToItems(
            "E:\\SafeWay\\STATEMACHINE_FINAL_REPO\\OSIMDBExtractor\\Sample_Input-300000.txt");
    System.out.println("-------------");
    System.out.println(
        "Step 1 Read CIC Execution Time : " + (System.currentTimeMillis() - startTime) +
            " Mill secs");
    System.out.println("-------------");
    
    startTime = System.currentTimeMillis();
    List<StoreItemModel> storeItemList = storeItemService.findByCorporateItemCd(cicList);
    System.out.println("-------------");
    System.out.println("Step 2 Load Matching Inventory Time & Write to CSV  : " +
        (System.currentTimeMillis() - startTime) + " Mill secs");
    System.out.println("-------------");*/
    
    
  }
  
  private Function<String, CsvItem> mapToItem = (line) -> {
    CsvItem item = new CsvItem(line,null,null,
        null,
        false,null,null);
    return item;
  };
}
