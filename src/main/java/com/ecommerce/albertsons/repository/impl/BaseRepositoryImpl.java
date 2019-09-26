//package com.ecommerce.albertsons.repository.impl;
//
//import com.ecommerce.albertsons.repository.BaseRepository;
//
//import java.util.List;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.BulkOperations;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
//import org.springframework.data.mongodb.core.query.Query;
//
//@Slf4j
//public class BaseRepositoryImpl<T> implements BaseRepository<T> {
//  @Autowired
//  private ReactiveMongoTemplate mongoTemplate;
//
//  @Override
//  public List<T> findEntities(Query query, Class<T> domainObject) {
//    return mongoTemplate.find(query, domainObject);
//  }
//
//  @Override
//  public void bulkInsert(List<T> entities, Class<T> domainObject) {
//    BulkOperations bulkOperations = mongoTemplate
//        .bulkOps(BulkOperations.BulkMode.UNORDERED, domainObject);
//    for (T entity : entities) {
//      bulkOperations.insert(entity);
//    }
//    bulkOperations.execute();
//  }
//}
