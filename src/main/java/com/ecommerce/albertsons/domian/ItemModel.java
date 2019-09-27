package com.ecommerce.albertsons.domian;

import com.albertsons.ecommerce.osim.entity.model.core.Item;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Document(collection = "item")
@CompoundIndexes({
    @CompoundIndex(name = "rogCd_corporateItemCd_upcId_vendorId",
        def = "{'itemKey': 1, 'rogCd' : 1, 'corporateItemCd': 1,"
        + "'upcId' : 1,'vendorId' :1}")
})
public class ItemModel extends Item implements Serializable {

  private static final long serialVersionUID = -7510130810443241757L;
  @Id
  private String id;

}
