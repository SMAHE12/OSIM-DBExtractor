package com.ecommerce.albertsons.model;

import java.io.Serializable;

public class CsvItem implements Serializable {
  String Cic;
  String upcId;
  String internetDesc;
  String productGroupCd;
  String productGroupNm;
  String productCategoryCd;
  String productCategoryNm;
  String productClassCd;
  String productClassNm;
  String retailSectionCd;
  String productSubClassCdLevel1;
  String productSubClassNmLevel1;
  String productSubClassCdLevel2;
  String productSubClassNmLevel2;
  
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
  
  public String getInternetDesc() {
    return internetDesc;
  }
  
  public void setInternetDesc(String internetDesc) {
    this.internetDesc = internetDesc;
  }
  
  public String getProductGroupCd() {
    return productGroupCd;
  }
  
  public void setProductGroupCd(String productGroupCd) {
    this.productGroupCd = productGroupCd;
  }
  
  public String getProductGroupNm() {
    return productGroupNm;
  }
  
  public void setProductGroupNm(String productGroupNm) {
    this.productGroupNm = productGroupNm;
  }
  
  public String getProductCategoryCd() {
    return productCategoryCd;
  }
  
  public void setProductCategoryCd(String productCategoryCd) {
    this.productCategoryCd = productCategoryCd;
  }
  
  public String getProductCategoryNm() {
    return productCategoryNm;
  }
  
  public void setProductCategoryNm(String productCategoryNm) {
    this.productCategoryNm = productCategoryNm;
  }
  
  public String getProductClassCd() {
    return productClassCd;
  }
  
  public void setProductClassCd(String productClassCd) {
    this.productClassCd = productClassCd;
  }
  
  public String getProductClassNm() {
    return productClassNm;
  }
  
  public void setProductClassNm(String productClassNm) {
    this.productClassNm = productClassNm;
  }
  
  public String getRetailSectionCd() {
    return retailSectionCd;
  }
  
  public void setRetailSectionCd(String retailSectionCd) {
    this.retailSectionCd = retailSectionCd;
  }
  
  public String getProductSubClassCdLevel1() {
    return productSubClassCdLevel1;
  }
  
  public void setProductSubClassCdLevel1(String productSubClassCdLevel1) {
    this.productSubClassCdLevel1 = productSubClassCdLevel1;
  }
  
  public String getProductSubClassNmLevel1() {
    return productSubClassNmLevel1;
  }
  
  public void setProductSubClassNmLevel1(String productSubClassNmLevel1) {
    this.productSubClassNmLevel1 = productSubClassNmLevel1;
  }
  
  public String getProductSubClassCdLevel2() {
    return productSubClassCdLevel2;
  }
  
  public void setProductSubClassCdLevel2(String productSubClassCdLevel2) {
    this.productSubClassCdLevel2 = productSubClassCdLevel2;
  }
  
  public String getProductSubClassNmLevel2() {
    return productSubClassNmLevel2;
  }
  
  public void setProductSubClassNmLevel2(String productSubClassNmLevel2) {
    this.productSubClassNmLevel2 = productSubClassNmLevel2;
  }
  
  public CsvItem(String Cic,
                 String upcId,
                 String internetDesc,
                 String productGroupCd,
                 String productGroupNm,
                 String productCategoryCd,
                 String productCategoryNm,
                 String productClassCd,
                 String productClassNm,
                 String retailSectionCd,
                 String productSubClassCdLevel1,
                 String productSubClassNmLevel1,
                 String productSubClassCdLevel2,
                 String productSubClassNmLevel2) {
    this.Cic = Cic;
    this.upcId = upcId;
    this.internetDesc = internetDesc;
    this.productGroupCd = productGroupCd;
    this.productGroupNm = productGroupNm;
    this.productCategoryCd = productCategoryCd;
    this.productCategoryNm = productCategoryNm;
    this.productClassCd = productClassCd;
    this.productClassNm = productClassNm;
    this.retailSectionCd = retailSectionCd;
    this.productSubClassCdLevel1 = productSubClassCdLevel1;
    this.productSubClassNmLevel1 = productSubClassNmLevel1;
    this.productSubClassCdLevel2 = productSubClassCdLevel2;
    this.productSubClassNmLevel2 = productSubClassNmLevel2;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append(this.Cic);
    sb.append(",");
    sb.append(this.upcId);
    sb.append(",");
    sb.append(this.internetDesc);
    sb.append(",");
    sb.append(this.productGroupCd);
    sb.append(",");
    sb.append(this.productGroupNm);
    sb.append(",");
    sb.append(this.productCategoryCd);
    sb.append(",");
    sb.append(this.productCategoryNm);
    sb.append(",");
    sb.append(this.productClassCd);
    sb.append(",");
    sb.append(this.productClassNm);
    sb.append(",");
    sb.append(this.retailSectionCd);
    sb.append(",");
    sb.append(this.productSubClassCdLevel1);
    sb.append(",");
    sb.append(this.productSubClassNmLevel1);
    sb.append(",");
    sb.append(this.productSubClassCdLevel2);
    sb.append(",");
    sb.append(this.productSubClassNmLevel2);
    sb.append("]");
    return sb.toString();
  }
}
