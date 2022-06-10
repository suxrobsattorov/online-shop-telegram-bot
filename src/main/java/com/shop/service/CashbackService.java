package com.shop.service;

import com.shop.dto.CashbackDTO;
import com.shop.entity.Cashback;
import com.shop.repository.CashbackRepository;
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
public class CashbackService {

    private final CashbackRepository cashbackRepository;

    public Cashback createCashback(CashbackDTO dto) {
        return cashbackRepository.save(ToEntity.cashbackTo(dto));
    }

    public List<CashbackDTO> getAll() {
        List<CashbackDTO> dtoList = new LinkedList<>();
        for (Cashback cashback : cashbackRepository.findAll()) {
            dtoList.add(ToDTO.cashbackToDTO(cashback));
        }
        return dtoList;
    }

    public CashbackDTO getById(Long id) {
        return ToDTO.cashbackToDTO(cashbackRepository.findById(id).get());
    }

}
