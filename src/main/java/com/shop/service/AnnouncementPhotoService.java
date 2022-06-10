package com.shop.service;


import com.shop.dto.AnnouncementPhotoDTO;
import com.shop.entity.AnnouncementPhoto;
import com.shop.repository.AnnouncementPhotoRepository;
import com.shop.toDTO.ToDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementPhotoService {

    private final AnnouncementPhotoRepository announcementPhotoRepository;

    public List<AnnouncementPhotoDTO> getAll() {
        List<AnnouncementPhotoDTO> dtoList = new LinkedList<>();
        for (AnnouncementPhoto announcementPhoto : announcementPhotoRepository.findAll()) {
            dtoList.add(ToDTO.announcementPhotoToDTO(announcementPhoto));
        }
        return dtoList;
    }

    public AnnouncementPhotoDTO getById(Long id) {
        return ToDTO.announcementPhotoToDTO(announcementPhotoRepository.findById(id).get());
    }

}
