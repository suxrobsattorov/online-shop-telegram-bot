package com.shop.service;

import com.shop.dto.AnnouncementDTO;
import com.shop.entity.Announcement;
import com.shop.repository.AnnouncementRepository;
import com.shop.toDTO.ToDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public List<AnnouncementDTO> getAll() {
        List<AnnouncementDTO> dtoList = new LinkedList<>();
        for (Announcement announcement : announcementRepository.findAll()) {
            dtoList.add(ToDTO.announcementToDTO(announcement));
        }
        return dtoList;
    }

    public AnnouncementDTO getById(Long id) {
        return ToDTO.announcementToDTO(announcementRepository.findById(id).get());
    }

}
