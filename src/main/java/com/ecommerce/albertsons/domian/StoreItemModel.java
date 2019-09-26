package com.ecommerce.albertsons.domian;

import com.albertsons.ecommerce.osim.entity.model.core.StoreItem;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by Nisum on 04-04-2019.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "storeitem")
@CompoundIndexes({
    @CompoundIndex(name = "upcNbr_corporateItemCd_bpn_rogCD",
    def = "{'storeId': 1, 'upcNbr' : 1, 'corporateItemCd': 1,"
      + "'bpn' : 1,'rogCD' :1}")
})
public class StoreItemModel extends StoreItem {
  @NotNull
  @Id
  private String id;
}
