package com.ecommerce.albertsons.model;

import java.io.Serializable;

public class CsvItem implements Serializable {
  String Cic;
  String upcId;
  String storeId;
  String itemDescription;
  boolean isOrderable;
  String stopBy;
  String itemType;
  
  public CsvItem(String Cic,
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
  
  public String getCic() {
    return Cic;
  }
  
  public void setCic(String cic) {
    Cic = cic;
  }
  
  public String getUpcId() {
    return upcId;
  }
  
  public void setUpcId(String upcId) {
    this.upcId = upcId;
  }
  
  public String getStoreId() {
    return storeId;
  }
  
  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }
  
  public String getItemDescription() {
    return itemDescription;
  }
  
  public void setItemDescription(String itemDescription) {
    this.itemDescription = itemDescription;
  }
  
  public boolean isOrderable() {
    return isOrderable;
  }
  
  public void setOrderable(boolean orderable) {
    isOrderable = orderable;
  }
  
  public String getStopBy() {
    return stopBy;
  }
  
  public void setStopBy(String stopBy) {
    this.stopBy = stopBy;
  }
  
  public String getItemType() {
    return itemType;
  }
  
  public void setItemType(String itemType) {
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
