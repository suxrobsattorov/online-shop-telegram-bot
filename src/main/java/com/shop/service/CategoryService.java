package com.shop.service;

import com.shop.dto.CategoryDTO;
import com.shop.entity.Category;
import com.shop.repository.CategoryRepository;
import com.shop.toDTO.ToDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAll() {
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (Category category : categoryRepository.findAll()) {
            dtoList.add(ToDTO.categoryToDTO(category));
        }
        return dtoList;
    }

    public CategoryDTO getById(Long id) {
        return ToDTO.categoryToDTO(categoryRepository.findById(id).get());
    }
}
