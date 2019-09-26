package com.ecommerce.albertsons.service;


import com.ecommerce.albertsons.domian.StoreItemModel;

import java.util.List;

/**
 * Created by Nisum on 30-04-2019.
 */
public interface StoreItemService {
  
  List<StoreItemModel> findByCorporateItemCd(List<String> cicList);
}
