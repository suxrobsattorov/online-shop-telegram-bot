package com.shop.service;

import com.shop.dto.PaymentDTO;
import com.shop.entity.Payment;
import com.shop.repository.PaymentRepository;
import com.shop.toDTO.ToDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<PaymentDTO> getAll() {
        List<PaymentDTO> dtoList = new LinkedList<>();
        for (Payment payment : paymentRepository.findAll()) {
            dtoList.add(ToDTO.paymentToDTO(payment));
        }
        return dtoList;
    }

    public PaymentDTO getById(Long id) {
        return ToDTO.paymentToDTO(paymentRepository.findById(id).get());
    }
}
