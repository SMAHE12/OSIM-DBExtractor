package com.ecommerce.albertsons.mapper;

import com.ecommerce.albertsons.domian.ItemModel;
import com.ecommerce.albertsons.domian.StoreItemModel;
import com.ecommerce.albertsons.model.CsvStoreItem;

import org.apache.commons.beanutils.PropertyUtils;

public class StoreItemMapper {
  
  public CsvStoreItem transformStoreItemToCsvStoreItem(StoreItemModel storeItemModel,
                                                       CsvStoreItem csvItem,
                                                       String[] storeItemColumns) {
    StringBuilder newString = new StringBuilder("");
    for (String column : storeItemColumns) {
      newString.append(getProperty(storeItemModel, column) + "-,-");
      csvItem.setCsvStoreItemLineData(newString.toString());
    }
    return csvItem;
  }
  
  private String getProperty(StoreItemModel storeItemModel, String column) {
    try {
      if (column.contains(".")) {
        return PropertyUtils.getNestedProperty(storeItemModel, column).toString();
      } else {
        return PropertyUtils.getProperty(storeItemModel, column).toString();
      }
    } catch (Exception e) {
      System.out.println(e);
      return "";
    }
  }
}
