package com.ecommerce.albertsons.repository;

import com.ecommerce.albertsons.domian.StoreItemModel;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StoreItemRepository extends MongoRepository<StoreItemModel, String> {

  @Query(value = "{'upcId':{ '$in':?0} , 'storeId': '1574'}")
  List<StoreItemModel> findByCorporateItemCd(List<String> cicList);
}
