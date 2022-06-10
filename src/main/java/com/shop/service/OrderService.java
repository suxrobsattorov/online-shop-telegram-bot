package com.shop.service;

import com.shop.buttons.InlineKeyboardButtonClass;
import com.shop.dto.OrderDetailsDTO;
import com.shop.dto.ProductDTO;
import com.shop.entity.OrderDetails;
import com.shop.list.RepositoryList;
import com.shop.repository.OrderDetailsRepository;
import com.shop.toDTO.ToEntity;
import com.shop.util.DefaultMessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final UserService userService;

    public void orderAccount(CallbackQuery callbackQuery, EditMessageText editMessageText, ProductDTO dto) {
        String data = callbackQuery.getData();
        User user = callbackQuery.getFrom();
        editMessageText.setChatId(user.getId().toString());
        String[] text = data.split("/");

        Integer count = RepositoryList.userSelectedProductCount.get(String.valueOf(user.getId()));
        if (count == null) {
            count = 0;
        }

        switch (text[2]) {
            case "1":
                count = count * 10 + 1;
                break;
            case "2":
                count = count * 10 + 2;
                break;
            case "3":
                count = count * 10 + 3;
                break;
            case "4":
                count = count * 10 + 4;
                break;
            case "5":
                count = count * 10 + 5;
                break;
            case "6":
                count = count * 10 + 6;
                break;
            case "7":
                count = count * 10 + 7;
                break;
            case "8":
                count = count * 10 + 8;
                break;
            case "9":
                count = count * 10 + 9;
                break;
            case "0":
                count = count * 10;
                break;
            case "ochirish":
                count = count / 10;
                break;
            case "savatchagajoylash":
                hisob(callbackQuery, editMessageText, dto);
                break;
            case "orqaga":
//                BackButtonUtil.orderBack(callbackQuery, editMessageText);
                break;
            default:
                DefaultMessageUtil.notFoundMessage(user.getId());
                break;
        }

        RepositoryList.userSelectedProductCount.put(String.valueOf(user.getId()), count);
        editMessageText.setReplyMarkup(InlineKeyboardButtonClass.orderMenu(user.getId()));
    }

    public OrderDetails hisob(CallbackQuery callbackQuery, EditMessageText editMessageText, ProductDTO dto) {
        User user = callbackQuery.getFrom();

        Integer count = RepositoryList.userSelectedProductCount.get(String.valueOf(user.getId()));
        Double sum = count * dto.getPrice();

        OrderDetailsDTO detailsDTO = new OrderDetailsDTO();
        detailsDTO.setQuantity(count);
        detailsDTO.setPrice(sum);
        detailsDTO.setProducts(dto);
        detailsDTO.setUser(userService.getByTgUserId(callbackQuery.getMessage().getChatId()));

        if (RepositoryList.userSelectedProductCount.get(String.valueOf(user.getId())) != null) {
            RepositoryList.userSelectedProductCount.put(user.getId().toString(), 0);
        }
        editMessageText.setText("Successfully");
        return ToEntity.orderDetailsTo(detailsDTO);
    }

}
