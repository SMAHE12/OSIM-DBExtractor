package com.ecommerce.albertsons.repository;

import java.util.List;
import org.springframework.data.mongodb.core.query.Query;

public interface BaseRepository<T> {
  List<T> findEntities(Query query, Class<T> domainObject);

  void bulkInsert(List<T> entities, Class<T> domainObject);
}
