package com.trollMarket.service.implementation;

import com.trollMarket.dto.Option.OptionDTO;
import com.trollMarket.dto.Shipper.ShipperErrorDTO;
import com.trollMarket.dto.Shipper.ShipperIndexDTO;
import com.trollMarket.dto.Shipper.ShipperUpsertDTO;
import com.trollMarket.entity.Shipper;
import com.trollMarket.helper.CustomFormat;
import com.trollMarket.repository.CartRepository;
import com.trollMarket.repository.OrderRepository;
import com.trollMarket.repository.ShipperRepository;
import com.trollMarket.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.LinkedList;
import java.util.List;

@Service
public class ShipperServiceImplementation implements ShipperService {
    private final ShipperRepository repository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ShipperServiceImplementation(ShipperRepository repository, CartRepository cartRepository, OrderRepository orderRepository) {
        this.repository = repository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<ShipperIndexDTO> get(){
        var listShipper = this.repository.get();
        List<ShipperIndexDTO> dto = new LinkedList<>();
        for(var shipper : listShipper){
            int totalExists = 0;
            totalExists = totalExists + this.cartRepository.countByShipper(shipper.getId());
            totalExists = totalExists + this.orderRepository.countByShipper(shipper.getId());

            var shipperDto = new ShipperIndexDTO(
                    shipper.getId(),
                    shipper.getCompanyName(),
                    CustomFormat.currencyIndoFormat(shipper.getPrice()),
                    shipper.getService() == true ? "Yes":"No",
                    (totalExists != 0) ? "no" : "yes");
            dto.add(shipperDto);
        }
        return dto;
    }

    @Override
    public void save(ShipperUpsertDTO dto){
        var shipper = new Shipper();
        shipper.setId(dto.getShipperId());
        shipper.setCompanyName(dto.getCompanyName());
        shipper.setPrice(dto.getPrice());
        shipper.setService(dto.getService());
        this.repository.save(shipper);
    }

    @Override
    public ShipperUpsertDTO getById(Integer id){
        var shipper = this.repository.getById(id);
        var dto = new ShipperUpsertDTO(
                shipper.getId(),
                shipper.getCompanyName(),
                shipper.getPrice(),
                shipper.getService()
        );
        return dto;
    }

    @Override
    public void delete(Integer id){
        var shipper = this.repository.getById(id);
        this.repository.delete(shipper);
    }

    @Override
    public void stopService(Integer id){
        var shipper = this.repository.getById(id);
        shipper.setService(true);
        this.repository.save(shipper);
    }

    @Override
    public List<OptionDTO> getOptionShipper(){
        var shippers = this.repository.getOptionShipper();
        List<OptionDTO> optionShipper = new LinkedList<>();
        for(var shipper : shippers){
            var opt = new OptionDTO(shipper.getCompanyName(), Integer.toString(shipper.getId()), false);
            optionShipper.add(opt);
        }
        return optionShipper;
    }

    @Override
    public ShipperErrorDTO getError(BindingResult validation){
        var errorDto = new ShipperErrorDTO();

        try {
            var error = validation.getFieldError("companyName").getDefaultMessage();
            errorDto.setCompanyNameError(error);
        }catch (Exception exception){
            errorDto.setCompanyNameError("");
        }

        try {
            var error = validation.getFieldError("price").getDefaultMessage();
            errorDto.setPriceError(error);
        }catch (Exception exception){
            errorDto.setPriceError("");
        }

        return errorDto;
    }
}
