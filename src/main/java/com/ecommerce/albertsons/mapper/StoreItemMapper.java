package com.ecommerce.albertsons.mapper;

import com.ecommerce.albertsons.domian.StoreItemModel;
import com.ecommerce.albertsons.model.CsvItem;
import com.ecommerce.albertsons.model.CsvStoreItem;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Value;

public class StoreItemMapper {
  
  public CsvStoreItem transformStoreItemToCsvStoreItem(StoreItemModel itemModel, CsvStoreItem csvItem,
                                                  String[] storeItemColumns) {
    StringBuilder newString = new StringBuilder("");
    try {
      for (String column : storeItemColumns) {
        if (column.contains(".")) {
          newString.append(PropertyUtils.getNestedProperty(itemModel, column));
          newString.append(",");
        } else {
          newString.append(PropertyUtils.getProperty(itemModel, column));
          newString.append(",");
        }
      }
      
    } catch (Exception e) {
      System.out.println(e);
    }
    csvItem.setCsvStoreItemLineData(newString.toString());
    return csvItem;
  }
}
