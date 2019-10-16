package com.ecommerce.albertsons.config;

import com.ecommerce.albertsons.model.CsvStoreItem;
import com.ecommerce.albertsons.process.StoreItemLineProcessor;
import com.ecommerce.albertsons.read.impl.StoreItemLineReader;
import com.ecommerce.albertsons.write.impl.StoreItemLinesWriter;

import java.util.List;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreItemChunksConfig {
  
  @Autowired
  private JobBuilderFactory jobs;
  
  @Autowired
  private StepBuilderFactory steps;
  
  @Bean
  public ItemReader<CsvStoreItem> itemReader() {
    return new StoreItemLineReader();
  }
  
  @Bean
  public ItemProcessor<CsvStoreItem, List<CsvStoreItem>> itemProcessor() {
    return new StoreItemLineProcessor();
  }
  
  @Bean
  public ItemWriter<List<CsvStoreItem>> itemWriter() {
    return new StoreItemLinesWriter();
  }
  
  @Bean
  public Step processStoreItemLines(ItemReader<CsvStoreItem> reader,
                                    ItemProcessor<CsvStoreItem, List<CsvStoreItem>> processor,
                                    ItemWriter<List<CsvStoreItem>> writer) {
    return steps.get("processStoreItemLines").<CsvStoreItem, List<CsvStoreItem>>chunk(100)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build();
  }
  
  
}