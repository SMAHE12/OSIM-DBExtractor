package com.ecommerce.albertsons.mapper;

import com.ecommerce.albertsons.domian.ItemModel;
import com.ecommerce.albertsons.model.CsvItem;

import org.apache.commons.beanutils.PropertyUtils;

public class ItemMapper {
  public CsvItem transformItemToCsvItem(ItemModel itemModel, CsvItem csvItem,
                                        String[] itemColumns) {
    StringBuilder newString = new StringBuilder("");
    
    for (String column : itemColumns) {
      newString.append(getProperty(itemModel, column) + ",");
    }
    csvItem.setCsvItemLineData(newString.toString());
    return csvItem;
  }
  
  private String getProperty(ItemModel itemModel, String column) {
    try {
      if (column.contains(".")) {
        return PropertyUtils.getNestedProperty(itemModel, column).toString();
      } else {
        return PropertyUtils.getProperty(itemModel, column).toString();
      }
    } catch (Exception e) {
      System.out.println(e);
      return "";
    }
  }
}
