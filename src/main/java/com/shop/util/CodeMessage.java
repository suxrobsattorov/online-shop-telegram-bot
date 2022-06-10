package com.shop.util;

import com.shop.enums.CodeMessageType;
import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Data
public class CodeMessage {
    private CodeMessageType type=CodeMessageType.MESSAGE;
    private SendMessage sendMessage;
    private EditMessageText editMessageText;
}
