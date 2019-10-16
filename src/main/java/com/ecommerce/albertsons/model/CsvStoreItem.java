package com.ecommerce.albertsons.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvStoreItem implements Serializable {
  public String Cic;
  public String upcId;
  public String storeId;
  public String itemDescription;
  public boolean isOrderable;
  public String stopBy;
  public String itemType;
  public String csvStoreItemLineData;
  
  public CsvStoreItem(){
  
  }
  
  public CsvStoreItem(String Cic,
                      String upcId, String storeId,
                      String itemDescription, boolean isOrderable,
                      String stopBy, String itemType) {
    this.Cic = Cic;
    this.upcId = upcId;
    this.storeId = storeId;
    this.itemDescription = itemDescription;
    this.isOrderable = isOrderable;
    this.stopBy = stopBy;
    this.itemType = itemType;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append(this.Cic);
    sb.append(",");
    sb.append(this.upcId);
    sb.append(",");
    sb.append(this.storeId);
    sb.append(",");
    sb.append(this.itemDescription);
    sb.append(",");
    sb.append(this.isOrderable);
    sb.append(",");
    sb.append(this.stopBy);
    sb.append(",");
    sb.append(this.itemType);
    sb.append("]");
    return sb.toString();
  }
}
