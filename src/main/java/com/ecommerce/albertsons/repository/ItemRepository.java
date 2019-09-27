package com.ecommerce.albertsons.repository;

import com.ecommerce.albertsons.domian.ItemModel;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/*
 * @author Nisum
 */

@Repository
public interface ItemRepository extends MongoRepository<ItemModel, String> {
  
  @Query("{'upcId':{ '$in':?0}}")
  List<ItemModel> findByUpcIds(List<String> upcIds);
  
}
