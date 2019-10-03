package com.ecommerce.albertsons.config;

import com.ecommerce.albertsons.model.CsvItem;
import com.ecommerce.albertsons.process.ItemLineProcessor;
import com.ecommerce.albertsons.read.impl.ItemLineReader;
import com.ecommerce.albertsons.write.impl.ItemLinesWriter;

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
public class ItemChunksConfig {
  
  @Autowired
  private JobBuilderFactory jobs;
  
  @Autowired
  private StepBuilderFactory steps;
  
  @Bean
  public ItemLineReader<CsvItem> itemLineReader() {
    return new ItemLineReader();
  }
  
  @Bean
  public ItemLineProcessor<CsvItem, CsvItem> itemLineProcessor() {
    return new ItemLineProcessor();
  }
  
  @Bean
  public ItemLinesWriter<CsvItem> itemLineWriter() {
    return new ItemLinesWriter();
  }
  
  @Bean
  public Step processItemLines(ItemReader<CsvItem> reader,
                               ItemProcessor<CsvItem, CsvItem> processor,
                               ItemWriter<CsvItem> writer) {
    return steps.get("processItemLines").<CsvItem, CsvItem>chunk(100)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build();
  }
  
  
}