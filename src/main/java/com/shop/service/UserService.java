package com.shop.service;

import com.shop.dto.CashbackDTO;
import com.shop.dto.RoleDTO;
import com.shop.dto.UserDTO;
import com.shop.entity.Cashback;
import com.shop.entity.Role;
import com.shop.entity.User;
import com.shop.repository.UserRepository;
import com.shop.toDTO.ToDTO;
import com.shop.toDTO.ToEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final CashbackService cashbackService;
    private final RoleService roleService;

    public void createUser(Message message) {
        org.telegram.telegrambots.meta.api.objects.User user = message.getFrom();

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("USER");
        Role role = roleService.createRole(roleDTO);

        CashbackDTO cashbackDTO = new CashbackDTO();
        cashbackDTO.setBalance(0d);
        cashbackDTO.setName("Cashback");
        Cashback cashback = cashbackService.createCashback(cashbackDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setTgUserId(user.getId());
        userDTO.setFirstname(user.getFirstName());
        userDTO.setUsername(user.getUserName());
        userDTO.setLastname(user.getLastName());
        userDTO.setPassword(user.getUserName() + user.getId());
        userDTO.setRole(ToDTO.roleToDTO(role));
        userDTO.setCashback(ToDTO.cashbackToDTO(cashback));
        User user1 = ToEntity.userTo(userDTO);
        userRepository.save(user1);
    }

    public List<UserDTO> getAll() {
        List<UserDTO> dtoList = new LinkedList<>();
        for (User user : userRepository.findAll()) {
            dtoList.add(ToDTO.userToDTO(user));
        }
        return dtoList;
    }

    public UserDTO getById(Long id) {
        return ToDTO.userToDTO(userRepository.findById(id).get());
    }

    public UserDTO getByTgUserId(Long id) {
        return ToDTO.userToDTO(userRepository.findByTgUserId(id).get());
    }
}
