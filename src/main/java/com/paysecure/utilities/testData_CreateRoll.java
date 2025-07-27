package com.paysecure.utilities;

import java.util.Random;
import java.util.UUID;

public class testData_CreateRoll {

	
	
	public static String generateRandomUsername() {
        return "tomjerry_" + UUID.randomUUID().toString().substring(0,3);
    }

    public static String generateRandomEmail() {
        return "TomJerry" + UUID.randomUUID().toString().substring(0,2) + "@mail.com";
    }

    public static String generateRandomPassword() {
        String chars = "Aa1!Bb2@Cc3#Dd4$";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
	
	
}
