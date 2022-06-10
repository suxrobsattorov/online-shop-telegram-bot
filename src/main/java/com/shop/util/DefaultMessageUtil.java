package com.shop.util;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class DefaultMessageUtil {

    public static SendMessage notFoundMessage(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Mavjud emas");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }
}
