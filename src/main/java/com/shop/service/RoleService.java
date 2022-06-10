package com.shop.service;

import com.shop.dto.RoleDTO;
import com.shop.entity.Role;
import com.shop.repository.RoleRepository;
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
public class RoleService {

    private final RoleRepository roleRepository;

    public Role createRole(RoleDTO dto) {
        return roleRepository.save(ToEntity.roleTo(dto));
    }

    public List<RoleDTO> getAll() {
        List<RoleDTO> dtoList = new LinkedList<>();
        for (Role role : roleRepository.findAll()) {
            dtoList.add(ToDTO.roleToDTO(role));
        }
        return dtoList;
    }

    public RoleDTO getById(Long id) {
        return ToDTO.roleToDTO(roleRepository.findById(id).get());
    }

}
