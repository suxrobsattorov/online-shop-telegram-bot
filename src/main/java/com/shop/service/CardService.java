package com.shop.service;

import com.shop.dto.CardDTO;
import com.shop.entity.Card;
import com.shop.repository.CardRepository;
import com.shop.toDTO.ToDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CardService {

    private final CardRepository cardRepository;

    public List<CardDTO> getAll() {
        List<CardDTO> dtoList = new LinkedList<>();
        for (Card card : cardRepository.findAll()) {
            dtoList.add(ToDTO.cardToDTO(card));
        }
        return dtoList;
    }

    public CardDTO getById(Long id) {
        return ToDTO.cardToDTO(cardRepository.findById(id).get());
    }

    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }

}
