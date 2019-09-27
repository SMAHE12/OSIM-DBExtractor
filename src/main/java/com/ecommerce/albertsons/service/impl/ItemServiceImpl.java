package com.ecommerce.albertsons.service.impl;

import com.ecommerce.albertsons.domian.ItemModel;
import com.ecommerce.albertsons.repository.ItemRepository;
import com.ecommerce.albertsons.service.ItemService;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/*
 * @author Nisum.
 */

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {
  
  @Autowired
  private ItemRepository itemRepository;
  
  @Override
  public List<ItemModel> findByUpcIds(List upcIds) {
    return itemRepository.findByUpcIds(upcIds);
  }
  
}
