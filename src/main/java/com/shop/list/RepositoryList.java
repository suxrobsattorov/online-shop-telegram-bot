package com.shop.list;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;
import java.util.Map;

public interface RepositoryList {
    Map<String, Integer> userSelectedProductCount = new HashMap<>();

    Map<Long, String> backMessageButton = new HashMap<>();
}
