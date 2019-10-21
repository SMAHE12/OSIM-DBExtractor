package com.ecommerce.albertsons.config;

import com.ecommerce.albertsons.service.ItemService;
import com.ecommerce.albertsons.service.StoreItemService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Batch config .
 */
@Configuration
@Slf4j
public class BatchConfig extends DefaultBatchConfigurer {
  
  @Value("${scheduler.order.batchSize:5000}")
  private Integer fetchSize;
  
  @Autowired
  private StoreItemService storeItemService;
  
  @Autowired
  private ItemService itemService;
}
