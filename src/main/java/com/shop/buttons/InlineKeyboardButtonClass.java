package com.shop.buttons;

import com.shop.dto.*;
import com.shop.list.RepositoryList;
import com.shop.util.InlineKeyboardUtil;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardButtonClass {

    public static InlineKeyboardMarkup shopButtonsList(List<ShopDTO> dtoList) {

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (ShopDTO dto : dtoList) {
            InlineKeyboardButton button =
                    InlineKeyboardUtil.button(dto.getName(), "/shop/" + dto.getName());
            rowList.add(InlineKeyboardUtil.row(button));
        }
        return InlineKeyboardUtil.keyboard(rowList);
    }

    public static InlineKeyboardMarkup categoryButtonsList(List<CategoryDTO> dtoList) {

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (CategoryDTO dto : dtoList) {
            InlineKeyboardButton button =
                    InlineKeyboardUtil.button(dto.getName(), "/category/" + dto.getName());
            rowList.add(InlineKeyboardUtil.row(button));
        }
        InlineKeyboardButton button =
                InlineKeyboardUtil.button("⬅️Orqaga", "/category/back");
        rowList.add(InlineKeyboardUtil.row(button));
        return InlineKeyboardUtil.keyboard(rowList);
    }

    public static InlineKeyboardMarkup productButtonsList(List<ProductDTO> dtoList) {

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (ProductDTO dto : dtoList) {
            InlineKeyboardButton button =
                    InlineKeyboardUtil.button(dto.getName(), "/product/" + dto.getName());
            rowList.add(InlineKeyboardUtil.row(button));
        }
        InlineKeyboardButton button =
                InlineKeyboardUtil.button("⬅️Orqaga", "/product/back");
        rowList.add(InlineKeyboardUtil.row(button));
        return InlineKeyboardUtil.keyboard(rowList);
    }

    public static InlineKeyboardMarkup orderMenu(Long userId) {
        Integer count = RepositoryList.userSelectedProductCount.get(String.valueOf(userId));
        if (count == null) {
            count = 0;
        }
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Tanlangan : " + count, "/order/tanlangan")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("1", "/order/1"),
                        InlineKeyboardUtil.button("2", "/order/2"),
                        InlineKeyboardUtil.button("3", "/order/3")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("4", "/order/4"),
                        InlineKeyboardUtil.button("5", "/order/5"),
                        InlineKeyboardUtil.button("6", "/order/6")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("7", "/order/7"),
                        InlineKeyboardUtil.button("8", "/order/8"),
                        InlineKeyboardUtil.button("9", "/order/9")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("0", "/order/0"),
                        InlineKeyboardUtil.button("🗑 O'chirish", "/order/ochirish")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🛒 Savatchaga joylash", "/order/savatchagajoylash")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("⬅️Orqaga", "/order/back")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }

    public static InlineKeyboardMarkup bucketMenu() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Buyurtmani tasdiqlash", "/bucket/ordernitasdiqlash")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Yana zakaz berish", "/bucket/orqaga")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }

    public static InlineKeyboardMarkup cardButtonsList(List<CardDTO> dtoList, CashbackDTO cashbackDto) {

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (CardDTO dto : dtoList) {
            InlineKeyboardButton button =
                    InlineKeyboardUtil.button("💳 ️ " + dto.getNumber(),
                            "/card/" + dto.getNumber());
            rowList.add(InlineKeyboardUtil.row(button));
        }
        if (cashbackDto != null) {
            InlineKeyboardButton button =
                    InlineKeyboardUtil.button("💳 ️ " + cashbackDto.getName(),
                            "/card/" + cashbackDto.getId());
            rowList.add(InlineKeyboardUtil.row(button));
        }
        InlineKeyboardButton button =
                InlineKeyboardUtil.button("💳 Karta qo'shish", "/card/createCard");
        rowList.add(InlineKeyboardUtil.row(button));
        return InlineKeyboardUtil.keyboard(rowList);
    }

    public static InlineKeyboardMarkup card(String cardNumber) {

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        InlineKeyboardButton button =
                InlineKeyboardUtil.button("🗑️ Kartani o'chirish", "/card/deleteCard/" + cardNumber);
        rowList.add(InlineKeyboardUtil.row(button));
        InlineKeyboardButton button2 =
                InlineKeyboardUtil.button("⬅️Orqaga", "/card/back");
        rowList.add(InlineKeyboardUtil.row(button2));
        return InlineKeyboardUtil.keyboard(rowList);
    }

    /*public static InlineKeyboardMarkup getProductCommitMenu(Customer customer){
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
        for (Basket basket : Database.basketList) {
            if (basket.getCustomerId().equals(customer.getId())){
                InlineKeyboardButton button1 = getButton("➕", "1/" + basket.getProductId());
                InlineKeyboardButton button2 = getButton(ProductService.getProductId(basket.getProductId()).getName(), "mahsulot");
                InlineKeyboardButton button3 = getButton("➖", "-1/" + basket.getProductId());
                InlineKeyboardButton button4 = getButton("❌", "0/" + basket.getProductId());
                List<InlineKeyboardButton> row = getRow(button1, button2, button3, button4);
                rowList.add(row);
            }
        }

        InlineKeyboardButton button5 = getButton(customer.getLanguageId() == 1 ? "✅ Buyurtmani tasdiqlash" : customer.getLanguageId() == 2 ? "✅ Подтверждение заказа" : "✅ Confirmation of order", ComponentContainer.CONFIRMATION_ORDER);
        InlineKeyboardButton button6 = getButton(customer.getLanguageId() == 1 ? "Yana buyurtma qo'shish" : customer.getLanguageId() == 2 ? "Добавить заказ еще раз" : "Add order again", ComponentContainer.ADD_AGAIN_ORDER);
        List<InlineKeyboardButton> row = getRow(button5);
        List<InlineKeyboardButton> row1 = getRow(button6);
        rowList.add(row);
        rowList.add(row1);
        InlineKeyboardMarkup markup = getMarkup(rowList);
        return markup;
    }*/

}
