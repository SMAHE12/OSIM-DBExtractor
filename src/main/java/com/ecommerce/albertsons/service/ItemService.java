package com.ecommerce.albertsons.service;

import com.ecommerce.albertsons.domian.ItemModel;

import java.util.List;

/*
 * @author Nisum.
 */

public interface ItemService {
  
  List<ItemModel> findByUpcIds(List upcIds);
  
  
}
