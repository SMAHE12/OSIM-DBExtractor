package com.ecommerce.albertsons.service.impl;


import com.ecommerce.albertsons.domian.StoreItemModel;
import com.ecommerce.albertsons.repository.StoreItemRepository;
import com.ecommerce.albertsons.service.StoreItemService;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nisum on 30-04-2019.
 */
@Service
@Slf4j
public class StoreItemServiceImpl implements StoreItemService {

  @Autowired
  private StoreItemRepository storeItemRepository;

  /**
   * This method will retrieve StoreItem by using bpn.
   *
   * @param bpn bpn number to be search by
   * @return Mono.
   */

  @Override
  public List<StoreItemModel> findByCorporateItemCd(List<String> bpn) {
    return storeItemRepository.findByCorporateItemCd(bpn);
  }
}
