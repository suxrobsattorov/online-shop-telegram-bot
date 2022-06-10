package com.shop.service;

import com.shop.dto.OrderDetailsDTO;
import com.shop.entity.OrderDetails;
import com.shop.repository.OrderDetailsRepository;
import com.shop.toDTO.ToDTO;
import com.shop.toDTO.ToEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    public void createOrder(OrderDetailsDTO dto) {
        OrderDetails orderDetails = ToEntity.orderDetailsTo(dto);
        orderDetailsRepository.save(orderDetails);
    }

    public List<OrderDetailsDTO> getAll() {
        List<OrderDetailsDTO> dtoList = new LinkedList<>();
        for (OrderDetails orderDetails : orderDetailsRepository.findAll()) {
            dtoList.add(ToDTO.orderDetailsToDTO(orderDetails));
        }
        return dtoList;
    }

    public OrderDetailsDTO getById(Long id) {
        return ToDTO.orderDetailsToDTO(orderDetailsRepository.findById(id).get());
    }

}
