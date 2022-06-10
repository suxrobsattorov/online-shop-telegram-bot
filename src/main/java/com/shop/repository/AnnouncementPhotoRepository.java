package com.shop.repository;

import com.shop.entity.AnnouncementPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementPhotoRepository extends JpaRepository<AnnouncementPhoto, Long> {
}
