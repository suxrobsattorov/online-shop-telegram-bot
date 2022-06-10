package com.shop.service;

import com.shop.dto.ProductPhotoDTO;
import com.shop.entity.ProductPhoto;
import com.shop.repository.ProductPhotoRepository;
import com.shop.toDTO.ToDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductPhotoService {

    private final ProductPhotoRepository productPhotoRepository;

    public List<ProductPhotoDTO> getAll() {
        List<ProductPhotoDTO> dtoList = new LinkedList<>();
        for (ProductPhoto productPhoto : productPhotoRepository.findAll()) {
            dtoList.add(ToDTO.productPhotoToDTO(productPhoto));
        }
        return dtoList;
    }

    public ProductPhotoDTO getById(Long id) {
        return ToDTO.productPhotoToDTO(productPhotoRepository.findById(id).get());
    }
}
