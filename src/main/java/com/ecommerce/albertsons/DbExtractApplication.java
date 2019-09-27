package com.ecommerce.albertsons;

import com.ecommerce.albertsons.model.CsvStoreItem;
import com.ecommerce.albertsons.repository.ItemRepository;
import com.ecommerce.albertsons.repository.StoreItemRepository;
import com.ecommerce.albertsons.service.ItemService;
import com.ecommerce.albertsons.service.StoreItemService;

import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/*
 * @author Nisum.
 */
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {StoreItemRepository.class})
@Slf4j
public class DbExtractApplication {
  
  @Autowired
  private StoreItemService storeItemService;
  
  @Autowired
  private ItemService itemService;
  
  public static void main(String[] args) {
    SpringApplication.run(DbExtractApplication.class, args);
  }
  
  private Function<String, CsvStoreItem> mapToItem = (line) -> {
    CsvStoreItem item = new CsvStoreItem(line, null, null,
        null,
        false, null, null);
    return item;
  };
}
