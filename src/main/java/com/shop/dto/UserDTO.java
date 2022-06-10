package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String firstname;

    private String lastname;

    private String username;

    private String phoneNumber;

    private String password;

    private String status;

    private Long tgUserId;

    private CashbackDTO cashback;

    private RoleDTO role;

}
