package dev.onaxsys.onaxsecurity.config;

import org.springframework.beans.factory.annotation.Value;

public class Appsettings {
    
    @Value("${jwt.jwtSecret}")
    public static String jwtSecret;
}
