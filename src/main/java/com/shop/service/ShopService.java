package com.shop.service;

import com.shop.dto.ShopDTO;
import com.shop.entity.Shop;
import com.shop.repository.ShopRepository;
import com.shop.toDTO.ToDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService {

    private final ShopRepository shopRepository;

    public List<ShopDTO> getAll() {
        List<ShopDTO> dtoList = new LinkedList<>();
        for (Shop shop : shopRepository.findAll()) {
            dtoList.add(ToDTO.shopToDTO(shop));
        }
        return dtoList;
    }

    public ShopDTO getById(Long id) {
        return ToDTO.shopToDTO(shopRepository.findById(id).get());
    }
}
