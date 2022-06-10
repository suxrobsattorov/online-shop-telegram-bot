package com.shop.toDTO;

import com.shop.dto.*;
import com.shop.entity.*;

public class ToEntity {

    public static Announcement announcementTo(AnnouncementDTO dto) {
        Announcement ent = new Announcement();
        ent.setId(dto.getId());
        ent.setCaption(dto.getCaption());
        ent.setCreated_at(dto.getCreated_at());
        ent.setStatus(dto.getStatus());
        ent.setTitle(dto.getTitle());
        return ent;
    }

    public static AnnouncementPhoto announcementPhotoTo(AnnouncementPhotoDTO dto) {
        AnnouncementPhoto ent = new AnnouncementPhoto();
        ent.setId(dto.getId());
        ent.setAnnouncement(dto.getAnnouncement());
        ent.setCreated_at(dto.getCreated_at());
        ent.setExtension(dto.getExtension());
        ent.setKey(dto.getKey());
        ent.setOriginName(dto.getOriginName());
        ent.setPath(dto.getPath());
        ent.setSize(dto.getSize());
        return ent;
    }

    public static Card cardTo(CardDTO dto) {
        Card ent = new Card();
        ent.setId(dto.getId());
        ent.setBalance(dto.getBalance());
        ent.setName(dto.getName());
        ent.setNumber(dto.getNumber());
        ent.setCreated_at(dto.getCreated_at());
        ent.setExpired_data(dto.getExpired_data());
        ent.setStatus(dto.getStatus());
        ent.setUser(userTo(dto.getUser()));
        return ent;
    }

    public static Cashback cashbackTo(CashbackDTO dto) {
        Cashback ent = new Cashback();
        ent.setId(dto.getId());
        ent.setBalance(dto.getBalance());
        ent.setName(dto.getName());
        ent.setNumber(dto.getNumber());
        ent.setCreated_at(dto.getCreated_at());
        return ent;
    }

    public static Category categoryTo(CategoryDTO dto) {
        Category ent = new Category();
        ent.setId(dto.getId());
        ent.setName(dto.getName());
        ent.setCreated_at(dto.getCreated_at());
        return ent;
    }

    public static OrderDetails orderDetailsTo(OrderDetailsDTO dto) {
        OrderDetails ent = new OrderDetails();
        ent.setId(dto.getId());
        ent.setPrice(dto.getPrice());
        ent.setUser(userTo(dto.getUser()));
        ent.setDeleted(dto.isDeleted());
        ent.setProducts(productTo(dto.getProducts()));
        ent.setQuantity(dto.getQuantity());
        return ent;
    }

    public static Payment paymentTo(PaymentDTO dto) {
        Payment ent = new Payment();
        ent.setId(dto.getId());
        ent.setPaymentType(dto.getPaymentType());
        ent.setStatus(dto.getStatus());
        ent.setOrder(orderDetailsTo(dto.getOrder()));
        ent.setTotalPrice(dto.getTotalPrice());
        return ent;
    }

    public static Product productTo(ProductDTO dto) {
        Product ent = new Product();
        ent.setId(dto.getId());
        ent.setName(dto.getName());
        ent.setPrice(dto.getPrice());
        ent.setStatus(dto.getStatus());
        ent.setCategory(categoryTo(dto.getCategory()));
        ent.setDescription(dto.getDescription());
        ent.setGaraty(dto.getGaraty());
        return ent;
    }

    public static ProductPhoto productPhotoTo(ProductPhotoDTO dto) {
        ProductPhoto ent = new ProductPhoto();
        ent.setId(dto.getId());
        ent.setExtension(dto.getExtension());
        ent.setProduct(productTo(dto.getProduct()));
        ent.setKey(dto.getKey());
        ent.setPath(dto.getPath());
        ent.setOriginName(dto.getOriginName());
        ent.setSize(dto.getSize());
        return ent;
    }

    public static Role roleTo(RoleDTO dto) {
        Role ent = new Role();
        ent.setId(dto.getId());
        ent.setName(dto.getName());
        return ent;
    }

    public static Shop shopTo(ShopDTO dto) {
        Shop ent = new Shop();
        ent.setId(dto.getId());
        ent.setName(dto.getName());
        ent.setCategory(categoryTo(dto.getCategory()));
        ent.setStatus(dto.getStatus());
        ent.setUser(userTo(dto.getUser()));
        ent.setFloor(dto.getFloor());
        return ent;
    }

    public static User userTo(UserDTO dto) {
        User ent = new User();
        ent.setId(dto.getId());
        if (dto.getCashback() != null) {
            ent.setCashback(cashbackTo(dto.getCashback()));
        }
        ent.setFirstname(dto.getFirstname());
        ent.setStatus(dto.getStatus());
        ent.setLastname(dto.getLastname());
        ent.setPassword(dto.getPassword());
        ent.setPhoneNumber(dto.getPhoneNumber());
        ent.setTgUserId(dto.getTgUserId());
        ent.setUsername(dto.getUsername());
        if (dto.getRole() != null) {
            ent.setRole(roleTo(dto.getRole()));
        }
        return ent;
    }
}
