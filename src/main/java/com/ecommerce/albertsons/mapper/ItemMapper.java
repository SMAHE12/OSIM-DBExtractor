package com.ecommerce.albertsons.mapper;

import com.ecommerce.albertsons.domian.ItemModel;
import com.ecommerce.albertsons.model.CsvItem;

import org.apache.commons.beanutils.PropertyUtils;

public class ItemMapper {
  public CsvItem transformItemToCsvItem(ItemModel itemModel, CsvItem csvItem,
                                        String[] itemColumns) {
    StringBuilder newString = new StringBuilder("");
    try {
      for (String column : itemColumns) {
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
    csvItem.setCsvItemLineData(newString.toString());
    return csvItem;
  }
}
