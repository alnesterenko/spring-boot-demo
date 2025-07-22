package com.example.demo.auntefication.count.auntefication.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
/* Аннотация @ApplicationScope распространяет область видимости бина на всё приложение */
@ApplicationScope
public class LoginCountService {

    private int count;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public void resetCount() {
        count = 0;
    }
}
