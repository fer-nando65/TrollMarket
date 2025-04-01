package com.trollMarket.service.implementation;

import com.trollMarket.dto.Cart.CartErrorDTO;
import com.trollMarket.dto.Cart.CartIndexDTO;
import com.trollMarket.dto.Cart.CartRawDTO;
import com.trollMarket.entity.Cart;
import com.trollMarket.entity.Order;
import com.trollMarket.entity.OrderDetail;
import com.trollMarket.helper.CustomFormat;
import com.trollMarket.repository.*;
import com.trollMarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class CartServiceImplementation implements CartService {
    private final CartRepository repository;
    private final MerchandiseRepository merchandiseRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public CartServiceImplementation(CartRepository repository, MerchandiseRepository merchandiseRepository, BuyerRepository buyerRepository, SellerRepository sellerRepository, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.repository = repository;
        this.merchandiseRepository = merchandiseRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<CartIndexDTO> get(String username){
        var cartList = this.repository.get(username);
        List<CartIndexDTO> dto = new LinkedList<>();
        for(var cart : cartList){
            var cartDto = new CartIndexDTO();
            cartDto.setCartId(cart.getId());
            cartDto.setMerchandiseName(cart.getMerchandise().getName());
            cartDto.setQuantity(cart.getQuantity());
            cartDto.setShipperName(cart.getShipper().getCompanyName());
            cartDto.setSellerName(cart.getMerchandise().getSeller().getProfile().getName());
            var total = cart.getMerchandise().getPrice().multiply(new BigDecimal(cart.getQuantity()));
            cartDto.setTotalPrice(CustomFormat.currencyIndoFormat(total));
            dto.add(cartDto);
        }
        return dto;
    }

    @Override
    public void save(CartRawDTO dto){
        var cart = new Cart();
        cart.setUsername(dto.getUsername());
        cart.setMerchandiseId(dto.getMerchandiseId());
        cart.setShipperId(dto.getShipperId());
        cart.setQuantity(dto.getAmount());
        cart.setUnitPrice(merchandiseRepository.getById(dto.getMerchandiseId()).getPrice());

        this.repository.save(cart);
    }

    @Override
    public void remove(Integer id){
        var cart = this.repository.getById(id);
        this.repository.delete(cart);
    }

    @Override
    public Boolean purchaseAll(String username){
        var buyer = this.buyerRepository.getById(username);
        var carts = this.repository.get(username);
        int totalPrice = 0;
        for(var cart : carts){
            totalPrice = totalPrice + (cart.getMerchandise().getPrice().intValue() * cart.getQuantity());
        }

        int balance = buyer.getBalance().intValue();
        if(balance > totalPrice || balance == totalPrice){
            balance = balance - totalPrice;
            buyer.setBalance(new BigDecimal(balance));
            for(var cart : carts){
                var seller = this.sellerRepository.getById(cart.getMerchandise().getUsernameSeller());
                var income = cart.getMerchandise().getPrice().multiply(new BigDecimal(cart.getQuantity()));
                var balanceSeller = seller.getBalance();
                seller.setBalance(balanceSeller.add(income));

                var order = new Order();
                order.setUsername(cart.getUsername());
                order.setOrderDate(LocalDate.now());
                order.setShipperId(cart.getShipperId());
                order.setDeliveryCost(cart.getShipper().getPrice());
                this.orderRepository.save(order);

                var latestOrderId = this.orderRepository.latestOrderId();

                var orderDetail = new OrderDetail();
                orderDetail.setOrderId(latestOrderId);
                orderDetail.setMerchandiseId(cart.getMerchandiseId());
                orderDetail.setUnitPrice(cart.getUnitPrice());
                orderDetail.setQuantity(cart.getQuantity());

                this.orderDetailRepository.save(orderDetail);

                this.repository.delete(cart);
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String getStatusPurchase(List<CartIndexDTO> dto){
        if(dto.size() == 0){
            return "No";
        }else {
            return "Yes";
        }
    }

    @Override
    public CartErrorDTO getError(BindingResult validation){
        var errorDto = new CartErrorDTO();

        try {
            var errorAmount = validation.getFieldError("amount").getDefaultMessage();
            errorDto.setAmountError(errorAmount);
        }catch (Exception exception){
            errorDto.setAmountError("");
        }

        try {
            var errorShipper = validation.getFieldError("shipperId").getDefaultMessage();
            errorDto.setShipperIdError(errorShipper);
        }catch (Exception exception){
            errorDto.setShipperIdError("");
        }

        return errorDto;
    }
}
