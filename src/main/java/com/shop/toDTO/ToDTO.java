package com.shop.toDTO;

import com.shop.dto.*;
import com.shop.entity.*;

import java.util.Collection;
import java.util.HashSet;

public class ToDTO {

    public static AnnouncementDTO announcementToDTO(Announcement entity) {
        AnnouncementDTO dto = new AnnouncementDTO();
        dto.setId(entity.getId());
        dto.setCaption(entity.getCaption());
        dto.setCreated_at(entity.getCreated_at());
        dto.setStatus(entity.getStatus());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public static AnnouncementPhotoDTO announcementPhotoToDTO(AnnouncementPhoto entity) {
        AnnouncementPhotoDTO dto = new AnnouncementPhotoDTO();
        dto.setId(entity.getId());
        dto.setAnnouncement(entity.getAnnouncement());
        dto.setCreated_at(entity.getCreated_at());
        dto.setExtension(entity.getExtension());
        dto.setKey(entity.getKey());
        dto.setOriginName(entity.getOriginName());
        dto.setPath(entity.getPath());
        dto.setSize(entity.getSize());
        return dto;
    }

    public static CardDTO cardToDTO(Card entity) {
        CardDTO dto = new CardDTO();
        dto.setId(entity.getId());
        dto.setBalance(entity.getBalance());
        dto.setName(entity.getName());
        dto.setNumber(entity.getNumber());
        dto.setCreated_at(entity.getCreated_at());
        dto.setExpired_data(entity.getExpired_data());
        dto.setStatus(entity.getStatus());
        dto.setUser(userToDTO(entity.getUser()));
        return dto;
    }

    public static CashbackDTO cashbackToDTO(Cashback entity) {
        CashbackDTO dto = new CashbackDTO();
        dto.setId(entity.getId());
        dto.setBalance(entity.getBalance());
        dto.setName(entity.getName());
        dto.setNumber(entity.getNumber());
        dto.setCreated_at(entity.getCreated_at());
        return dto;
    }

    public static CategoryDTO categoryToDTO(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreated_at(entity.getCreated_at());
//        dto.setParent(entity.getParent().getId());
        return dto;
    }

    public static OrderDetailsDTO orderDetailsToDTO(OrderDetails entity) {
        OrderDetailsDTO dto = new OrderDetailsDTO();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setUser(userToDTO(entity.getUser()));
        dto.setDeleted(entity.isDeleted());
        dto.setProducts(productToDTO(entity.getProducts()));
        dto.setQuantity(entity.getQuantity());
        return dto;
    }

    public static PaymentDTO paymentToDTO(Payment entity) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(entity.getId());
        dto.setPaymentType(entity.getPaymentType());
        dto.setStatus(entity.getStatus());
        dto.setOrder(orderDetailsToDTO(entity.getOrder()));
        dto.setTotalPrice(entity.getTotalPrice());
        return dto;
    }

    public static ProductDTO productToDTO(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setStatus(entity.getStatus());
        dto.setCategory(categoryToDTO(entity.getCategory()));
        dto.setDescription(entity.getDescription());
        dto.setGaraty(entity.getGaraty());
        return dto;
    }

    public static ProductPhotoDTO productPhotoToDTO(ProductPhoto entity) {
        ProductPhotoDTO dto = new ProductPhotoDTO();
        dto.setId(entity.getId());
        dto.setExtension(entity.getExtension());
        dto.setProduct(productToDTO(entity.getProduct()));
        dto.setKey(entity.getKey());
        dto.setPath(entity.getPath());
        dto.setOriginName(entity.getOriginName());
        dto.setSize(entity.getSize());
        return dto;
    }

    public static RoleDTO roleToDTO(Role entity) {
        RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static ShopDTO shopToDTO(Shop entity) {
        ShopDTO dto = new ShopDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCategory(categoryToDTO(entity.getCategory()));
        dto.setStatus(entity.getStatus());
//        dto.setUser(userToDTO(entity.getUser()));
        dto.setFloor(entity.getFloor());
        return dto;
    }

    public static UserDTO userToDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setCashback(cashbackToDTO(entity.getCashback()));
        dto.setFirstname(entity.getFirstname());
        dto.setStatus(entity.getStatus());
        dto.setLastname(entity.getLastname());
        dto.setPassword(entity.getPassword());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setTgUserId(entity.getTgUserId());
        dto.setUsername(entity.getUsername());
        dto.setRole(roleToDTO(entity.getRole()));
        return dto;
    }
}
