package com.trollMarket.service;

import com.trollMarket.dto.Option.OptionDTO;
import com.trollMarket.dto.Shipper.ShipperErrorDTO;
import com.trollMarket.dto.Shipper.ShipperIndexDTO;
import com.trollMarket.dto.Shipper.ShipperUpsertDTO;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ShipperService {
    List<ShipperIndexDTO> get();
    void save(ShipperUpsertDTO dto);
    ShipperUpsertDTO getById(Integer id);
    void delete(Integer id);
    void stopService(Integer id);
    List<OptionDTO> getOptionShipper();
    ShipperErrorDTO getError(BindingResult validation);
}
