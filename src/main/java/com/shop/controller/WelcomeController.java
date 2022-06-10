package com.shop.controller;

import com.shop.buttons.InlineKeyboardButtonClass;
import com.shop.buttons.ReplayKeyboardButton;
import com.shop.dto.*;
import com.shop.entity.*;
import com.shop.enums.CodeMessageType;
import com.shop.list.RepositoryList;
import com.shop.service.*;
import com.shop.toDTO.ToDTO;
import com.shop.util.CodeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Controller
@RequiredArgsConstructor
@Transactional
public class WelcomeController {
    private final ShopService shopService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final CardService cardService;
    private final CashbackService cashbackService;
    private final UserService userService;
    private final OrderDetailsService orderDetailsService;
    private final OrderService orderService;
    private ProductDTO productDto = null;
    private OrderDetailsDTO orderDto = null;
    private List<CategoryDTO> categoryDTOList;
    private List<ProductDTO> productDTOList;

    public CodeMessage callbackQuery(CallbackQuery callbackQuery) {

        CodeMessage codeMessage = null;

        String data = callbackQuery.getData();
        String[] text = data.split("/");

        if (text[1].equals("category") || text[1].equals("shop")) {
            codeMessage = categoryList(callbackQuery);
        } else if (text[1].equals("card")) {
            codeMessage = card(callbackQuery);
        } else if (text[1].equals("product")) {
            codeMessage = productList(callbackQuery);
        } else if (text[1].equals("order")) {
            codeMessage = orderList(callbackQuery);
        } else if (text[1].equals("bucket")) {
            if (text[2].equals("ordernitasdiqlash")) {
                codeMessage = phoneNumber(callbackQuery);
            } else if (text[2].equals("orqaga")) {
                codeMessage = backBucket(callbackQuery);
            }
        }
        return codeMessage;
    }

    public CodeMessage message(Message message) {

        CodeMessage codeMessage = null;
        String text = message.getText();

        if (text != null) {
            if (text.equals("/start")) {
                codeMessage = start(message);
            } else if (text.equals("/settings")) {

            } else if (text.equals("/help")) {

            } else if (text.equals("🏠 MENU")) {
                codeMessage = shopList(message);
            } else if (text.equals("💳 Kartalar")) {
                codeMessage = cardList(message);
            } else if (text.equals("🛒 Buyurtmalarim")) {
                codeMessage = buscet(message);
            } else if (text.equals("🛍️ Aksiyalar")) {

            } else if (text.equals("⬅ Orqaga")) {
                if (RepositoryList.backMessageButton.containsKey(message.getChatId())) {
                    String s = RepositoryList.backMessageButton.get(message.getChatId());
                    if (s.equals("sendPhone")) {
                        RepositoryList.backMessageButton.remove(message.getChatId());
                        codeMessage = buscet(message);
                    } else if (s.equals("sendloc")) {
                        RepositoryList.backMessageButton.remove(message.getChatId());
                        codeMessage = phoneNumber(message);
                    } else if (s.equals("againSendloc")) {
                        RepositoryList.backMessageButton.remove(message.getChatId());
                        codeMessage = location(message);
                    }
                }
            }
        } else if (message.hasContact()) {
            codeMessage = location(message);
        } else if (message.hasLocation()) {
            codeMessage = againLocationButton(message);
        }
        return codeMessage;
    }

    public CodeMessage createCard(CallbackQuery callbackQuery, Message message) {

        CodeMessage codeMessage = new CodeMessage();
        SendMessage sendMessage = new SendMessage();
        String data = callbackQuery.getData();
        String[] text = data.split("/");

        if (text[2].equals("createCard")) {
            Scanner scanner = new Scanner(System.in);
            sendMessage.setText("Karta nomerini kiriting : ");
            scanner.next();
        }

        return codeMessage;
    }

    public CodeMessage start(Message message) {
        boolean isUser = false;
        for (UserDTO dto : userService.getAll()) {
            if (dto.getTgUserId().equals(message.getFrom().getId())) {
                isUser = true;
            }
        }
        if (!isUser) {
            userService.createUser(message);
        }
        SendMessage sendMessage = new SendMessage();
        CodeMessage codeMessage = new CodeMessage();
        String responseText = "Assalomu alaykum online do'konimizga xush kelibsiz!";
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyMarkup(ReplayKeyboardButton.mainMenu());
        sendMessage.setText(responseText);
        codeMessage.setSendMessage(sendMessage);
        codeMessage.setType(CodeMessageType.MESSAGE);
        return codeMessage;
    }

    public CodeMessage shopList(Message message) {
        SendMessage sendMessage = new SendMessage();
        CodeMessage codeMessage = new CodeMessage();
        String responseText = "Do'konlar ro'yxati";
        sendMessage.setReplyMarkup(InlineKeyboardButtonClass.shopButtonsList(shopService.getAll()));
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(responseText);
        codeMessage.setSendMessage(sendMessage);
        codeMessage.setType(CodeMessageType.MESSAGE);
        return codeMessage;
    }

    public CodeMessage cardList(Message message) {
        SendMessage sendMessage = new SendMessage();
        CodeMessage codeMessage = new CodeMessage();
        String responseText = "Kartalar ro'yxati";
        CashbackDTO cashbackDTO = null;
        for (UserDTO userDTO : userService.getAll()) {
            for (CashbackDTO cashbackDTO1 : cashbackService.getAll()) {
                if (userDTO.getCashback().getId().equals(cashbackDTO1.getId())) {
                    cashbackDTO = cashbackDTO1;
                    break;
                }
            }
        }
        sendMessage.setReplyMarkup(InlineKeyboardButtonClass.cardButtonsList(cardService.getAll(), cashbackDTO));
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(responseText);
        codeMessage.setSendMessage(sendMessage);
        codeMessage.setType(CodeMessageType.MESSAGE);
        return codeMessage;
    }

    public CodeMessage card(CallbackQuery callbackQuery) {

        EditMessageText editMessageText = new EditMessageText();
        SendMessage sendMessage = new SendMessage();
        CodeMessage codeMessage = new CodeMessage();

        sendMessage.setChatId(callbackQuery.getFrom().getId().toString());
        editMessageText.setChatId(callbackQuery.getFrom().getId().toString());
        editMessageText.setMessageId(callbackQuery.getMessage().getMessageId());

        String data = callbackQuery.getData();
        String[] text = data.split("/");

        selectionCard(text, editMessageText, cardService.getAll(), cashbackService.getAll());

        codeMessage.setEditMessageText(editMessageText);
        codeMessage.setType(CodeMessageType.EDIT);
        return codeMessage;
    }

    public CodeMessage categoryList(CallbackQuery callbackQuery) {

        EditMessageText editMessageText = new EditMessageText();
        SendMessage sendMessage = new SendMessage();
        CodeMessage codeMessage = new CodeMessage();

        sendMessage.setChatId(callbackQuery.getFrom().getId().toString());
        editMessageText.setChatId(callbackQuery.getFrom().getId().toString());
        editMessageText.setMessageId(callbackQuery.getMessage().getMessageId());

        String data = callbackQuery.getData();
        String[] text = data.split("/");

        selection(text, editMessageText, callbackQuery);

        codeMessage.setEditMessageText(editMessageText);
        codeMessage.setType(CodeMessageType.EDIT);
        return codeMessage;
    }

    public CodeMessage productList(CallbackQuery callbackQuery) {

        EditMessageText editMessageText = new EditMessageText();
        SendMessage sendMessage = new SendMessage();
        CodeMessage codeMessage = new CodeMessage();

        sendMessage.setChatId(callbackQuery.getFrom().getId().toString());
        editMessageText.setChatId(callbackQuery.getFrom().getId().toString());
        editMessageText.setMessageId(callbackQuery.getMessage().getMessageId());

        String data = callbackQuery.getData();
        String[] text = data.split("/");

        selection(text, editMessageText, callbackQuery);

        codeMessage.setEditMessageText(editMessageText);
        codeMessage.setType(CodeMessageType.EDIT);
        return codeMessage;
    }

    public CodeMessage orderList(CallbackQuery callbackQuery) {

        EditMessageText editMessageText = new EditMessageText();
        SendMessage sendMessage = new SendMessage();
        CodeMessage codeMessage = new CodeMessage();

        sendMessage.setChatId(callbackQuery.getFrom().getId().toString());
        editMessageText.setChatId(callbackQuery.getFrom().getId().toString());
        editMessageText.setMessageId(callbackQuery.getMessage().getMessageId());

        String data = callbackQuery.getData();
        String[] text = data.split("/");

        selection(text, editMessageText, callbackQuery);

        codeMessage.setEditMessageText(editMessageText);
        codeMessage.setType(CodeMessageType.EDIT);
        return codeMessage;
    }

    public CodeMessage buscet(Message message) {

        SendMessage sendMessage = new SendMessage();
        CodeMessage codeMessage = new CodeMessage();

        sendMessage.setChatId(message.getChatId().toString());
        if (orderDto != null && productDto != null) {
            double sum = orderDto.getPrice();
            String s = productDto.getName() + " : " + orderDto.getQuantity() + " x " +
                    productDto.getPrice() + " = " + sum + "\n";

            sendMessage.setText("Sizning Buyurtmalaringiz\n\n" + s);
            sendMessage.setReplyMarkup(InlineKeyboardButtonClass.bucketMenu());
        } else {
            sendMessage.setText("Sizda hozirda buyurtmalar mavjud emas.");
        }

        codeMessage.setSendMessage(sendMessage);
        codeMessage.setType(CodeMessageType.MESSAGE);
        return codeMessage;
    }

    public static CodeMessage backBucket(CallbackQuery callbackQuery) {
        SendMessage sendMessage = new SendMessage();
        CodeMessage codeMessage = new CodeMessage();
        String responseText = "Assalomu alaykum online do'konimizga xush kelibsiz!";
        sendMessage.setChatId(callbackQuery.getMessage().getChatId().toString());
        sendMessage.setReplyMarkup(ReplayKeyboardButton.mainMenu());
        sendMessage.setText(responseText);
        codeMessage.setSendMessage(sendMessage);
        codeMessage.setType(CodeMessageType.MESSAGE);
        return codeMessage;
    }

    public static CodeMessage phoneNumber(CallbackQuery callbackQuery) {
        CodeMessage codeMessage = new CodeMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(callbackQuery.getFrom().getId().toString());
        sendMessage.setText("Iltimos, \"📞 Telefon nomer jo'natish\" tugmasini bosing.");
        sendMessage.setReplyMarkup(ReplayKeyboardButton.phoneNumberButton());
        codeMessage.setType(CodeMessageType.MESSAGE);
        codeMessage.setSendMessage(sendMessage);
        RepositoryList.backMessageButton.put(callbackQuery.getFrom().getId(), "sendPhone");
        return codeMessage;
    }

    public static CodeMessage phoneNumber(Message message) {
        CodeMessage codeMessage = new CodeMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getFrom().getId().toString());
        sendMessage.setText("Iltimos, \"📞 Telefon nomer jo'natish\" tugmasini bosing.");
        sendMessage.setReplyMarkup(ReplayKeyboardButton.phoneNumberButton());
        codeMessage.setType(CodeMessageType.MESSAGE);
        codeMessage.setSendMessage(sendMessage);
        RepositoryList.backMessageButton.put(message.getFrom().getId(), "sendPhone");
        return codeMessage;
    }

    public CodeMessage location(Message message) {
        CodeMessage codeMessage = new CodeMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Iltimos, “\uD83D\uDCCD Geolokatsiyani jo’natish” " +
                "tugmasini bosish orqali geolokatsiyangizni yuboring. Bunda telefoningizda" +
                " manzilni aniqlash funksiyasi yoqilgan bo’lishi lozim.");
        sendMessage.setReplyMarkup(ReplayKeyboardButton.locationButton());
        codeMessage.setType(CodeMessageType.MESSAGE);
        codeMessage.setSendMessage(sendMessage);
        RepositoryList.backMessageButton.put(message.getFrom().getId(), "sendloc");
        return codeMessage;
    }

    public CodeMessage againLocationButton(Message message) {
        CodeMessage codeMessage = new CodeMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Joylashuvni noto'g'rimi?\n" +
                "Qayta jo'nating\uD83D\uDCCD");
        sendMessage.setReplyMarkup(ReplayKeyboardButton.againLocationButton());
        codeMessage.setType(CodeMessageType.MESSAGE);
        codeMessage.setSendMessage(sendMessage);
        RepositoryList.backMessageButton.put(message.getFrom().getId(), "againSendloc");
        return codeMessage;
    }

    public void selection(String[] text, EditMessageText editMessageText, CallbackQuery callbackQuery) {
        if (text[1].equals("shop")) {
            categoryDTOList = new LinkedList<>();
            for (ShopDTO shopDTO : shopService.getAll()) {
                if (text[2].equals(shopDTO.getName())) {
                    for (CategoryDTO categoryDTO : categoryService.getAll()) {
                        if (shopDTO.getCategory().getId().equals(categoryDTO.getId())) {
                            categoryDTOList.add(categoryDTO);
                        }
                    }
                }
            }
            String responseText = "Kategoriyalar ro'yxati";
            editMessageText.setText(responseText);
            editMessageText.setReplyMarkup(InlineKeyboardButtonClass.categoryButtonsList(categoryDTOList));
        } else if (text[1].equals("category")) {
            if (text[2].equals("back")) {
                editMessageText.setText("Do'konlar ro'yxati");
                editMessageText.setReplyMarkup(InlineKeyboardButtonClass.shopButtonsList(shopService.getAll()));
            } else {
                productDTOList = new LinkedList<>();
                for (CategoryDTO categoryDTO : categoryService.getAll()) {
                    if (text[2].equals(categoryDTO.getName())) {
                        for (ProductDTO productDTO : productService.getAll()) {
                            if (productDTO.getCategory().getId().equals(categoryDTO.getId())) {
                                productDTOList.add(productDTO);
                            }
                        }
                    }
                }
                String responseText = "Mahsulotlar ro'yxati";
                editMessageText.setText(responseText);
                editMessageText.setReplyMarkup(InlineKeyboardButtonClass.productButtonsList(productDTOList));
            }
        } else if (text[1].equals("product")) {
            if (text[2].equals("back")) {
                editMessageText.setText("Kategoriyalar ro'yxati");
                editMessageText.setReplyMarkup(InlineKeyboardButtonClass.categoryButtonsList(categoryDTOList));
            } else {
                for (ProductDTO dto : productService.getAll()) {
                    if (text[2].equals(dto.getName())) {
                        productDto = dto;
                        editMessageText.setText(productDto.getName() +
                                "\n" + productDto.getPrice());
                        editMessageText.setReplyMarkup(InlineKeyboardButtonClass.orderMenu(callbackQuery.getMessage().getChatId()));
                        break;
                    }
                }
            }
        } else if (text[1].equals("order")) {
            if (text[2].equals("savatchagajoylash")) {
                OrderDetails entity = orderService.hisob(callbackQuery, editMessageText, productDto);
                orderDto = ToDTO.orderDetailsToDTO(entity);
                orderDetailsService.createOrder(orderDto);
            }
            if (text[2].equals("back")) {
                editMessageText.setText("Mahsulotlar ro'yxati");
                editMessageText.setReplyMarkup(InlineKeyboardButtonClass.productButtonsList(productDTOList));
            } else if (!text[2].equals("savatchagajoylash")) {
                editMessageText.setText(productDto.getName() +
                        "\n" + productDto.getPrice());
                orderService.orderAccount(callbackQuery, editMessageText, productDto);
            }
        }
    }

    public void selectionCard(String[] text, EditMessageText editMessageText, List<CardDTO> cardDTOList,
                              List<CashbackDTO> cashbackDTOList) {
        if (text[1].equals("card")) {
            if (text[2].equals("deleteCard")) {
                for (CardDTO d : cardDTOList) {
                    if (text[3].equals(d.getNumber())) {
                        cardService.deleteById(d.getId());
                        editMessageText.setText("Deleted");
                        break;
                    }
                }
            }
            for (CashbackDTO d : cashbackDTOList) {
                if (text[2].equals(String.valueOf(d.getId()))) {
                    editMessageText.setText("Karta nomi\n\t↪️" + d.getName() + "\n" +
                            "Karta balansi\n\t↪️" + d.getBalance() + "\n");
                }
            }

            for (CardDTO d : cardDTOList) {
                if (text[2].equals(d.getNumber())) {
                    editMessageText.setReplyMarkup(InlineKeyboardButtonClass.card(d.getNumber()));
                    editMessageText.setText("Karta nomi\n\t↪️" + d.getName() + "\n" +
                            "Karta nomeri\n\t↪️" + d.getNumber() + "\n" +
                            "Karta balansi\n\t↪️" + d.getBalance() + "\n");
                }
            }
        }
    }
}
