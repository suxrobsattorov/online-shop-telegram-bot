package com.shop.service;

import com.shop.dto.ProductDTO;
import com.shop.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.toDTO.ToDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> getAll() {
        List<ProductDTO> dtoList = new LinkedList<>();
        for (Product product : productRepository.findAll()) {
            dtoList.add(ToDTO.productToDTO(product));
        }
        return dtoList;
    }

    public ProductDTO getById(Long id) {
        return ToDTO.productToDTO(productRepository.findById(id).get());
    }

}
