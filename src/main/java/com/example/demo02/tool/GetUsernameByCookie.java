package com.example.demo02.tool;

import java.util.Arrays;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;
import java.util.Optional;

public class GetUsernameByCookie {
    
    public static String get(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        Optional<Cookie> usernameCookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("username"))
                .findFirst();

            String username = usernameCookie.get().getValue();

            return username;
    }
}
