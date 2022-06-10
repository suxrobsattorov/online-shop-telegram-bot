package com.shop.core;

import com.shop.controller.WelcomeController;
import com.shop.util.CodeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//@Configuration
//@ComponentScan(basePackages = "")
//@EnableAutoConfiguration
@Component
@RequiredArgsConstructor
public class CoreBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String username;

    @Value("${telegram.bot.token}")
    private String token;

    private final WelcomeController welcomeController;

    @Override
    public void onUpdateReceived(Update update) {
        if (update != null) {
            if (update.hasMessage()) {
                sendMsg(welcomeController.message(update.getMessage()));
            }
            if (update.hasCallbackQuery()) {
                sendMsg(welcomeController.callbackQuery(update.getCallbackQuery()));
            }
        }
    }

    public void sendMsg(CodeMessage codeMessage) {
        try {
            switch (codeMessage.getType()) {
                case MESSAGE:
                    execute(codeMessage.getSendMessage());
                    break;
                case EDIT:
                    execute(codeMessage.getEditMessageText());
                    break;
                case EDITANDSEND:
                    execute(codeMessage.getEditMessageText());
                    execute(codeMessage.getSendMessage());
                    break;
                default:
                    break;
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendphoto(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
