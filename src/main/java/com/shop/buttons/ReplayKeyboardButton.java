package com.shop.buttons;

import com.shop.util.ReplayKeyboardUtil;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class ReplayKeyboardButton {

    public static ReplyKeyboardMarkup mainMenu() {

        List<KeyboardRow> keyboardRowList = ReplayKeyboardUtil.rowlist(
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("🏠 MENU")),
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("🛒 Buyurtmalarim"),
                        ReplayKeyboardUtil.button("🛍️ Aksiyalar")),
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("💳 Kartalar"))
        );
        ReplyKeyboardMarkup markup = ReplayKeyboardUtil.keyboard(keyboardRowList);
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        markup.setSelective(true);
        return markup;
    }

    public static ReplyKeyboardMarkup locationButton() {
        KeyboardButton button = ReplayKeyboardUtil.button("📍 Geolokatsiyani jo'natish");
        button.setRequestLocation(true);

        List<KeyboardRow> keyboardRowList = ReplayKeyboardUtil.rowlist(
                ReplayKeyboardUtil.row(button),
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("⬅ Orqaga"))
        );
        ReplyKeyboardMarkup markup = ReplayKeyboardUtil.keyboard(keyboardRowList);
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        markup.setSelective(true);
        return markup;
    }

    public static ReplyKeyboardMarkup againLocationButton() {
        KeyboardButton button = ReplayKeyboardUtil.button("📍 Joylashuvni qayta yuborish");
        button.setRequestLocation(true);

        List<KeyboardRow> keyboardRowList = ReplayKeyboardUtil.rowlist(
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("✔ Tasdiqlash")),
                ReplayKeyboardUtil.row(button),
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("⬅ Orqaga"))
        );
        ReplyKeyboardMarkup markup = ReplayKeyboardUtil.keyboard(keyboardRowList);
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        markup.setSelective(true);
        return markup;
    }

    public static ReplyKeyboardMarkup phoneNumberButton() {
        KeyboardButton button = ReplayKeyboardUtil.button("📞 Telefon nomer jo'natish");
        button.setRequestContact(true);

        List<KeyboardRow> keyboardRowList = ReplayKeyboardUtil.rowlist(
                ReplayKeyboardUtil.row(button),
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("⬅ Orqaga"))
        );
        ReplyKeyboardMarkup markup = ReplayKeyboardUtil.keyboard(keyboardRowList);
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        markup.setSelective(true);
        return markup;
    }
}
