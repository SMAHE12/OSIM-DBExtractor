package com.ecommerce.albertsons.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvItem implements Serializable {
  public String Cic;
  public String upcId;
  public String internetDesc;
  public String productGroupCd;
  public String productGroupNm;
  public String productCategoryCd;
  public String productCategoryNm;
  public String productClassCd;
  public String productClassNm;
  public String retailSectionCd;
  public String productSubClassCdLevel1;
  public String productSubClassNmLevel1;
  public String productSubClassCdLevel2;
  public String productSubClassNmLevel2;
  
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
