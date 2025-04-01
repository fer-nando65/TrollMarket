package com.trollMarket.service;

import com.trollMarket.dto.Merchandise.MerchandiseIndexDTO;
import com.trollMarket.dto.Merchandise.MerchandiseInfoDTO;
import com.trollMarket.dto.Merchandise.MerchandiseUpsertDTO;

import java.util.List;

public interface MerchandiseService {
    List<MerchandiseIndexDTO> get(String username);
    MerchandiseUpsertDTO getMerchandise(Integer id, String username);
    void save(MerchandiseUpsertDTO dto);
    void setDiscontinue(Integer id);
    void delete(Integer id);
    MerchandiseInfoDTO getInfo(Integer id);
}
