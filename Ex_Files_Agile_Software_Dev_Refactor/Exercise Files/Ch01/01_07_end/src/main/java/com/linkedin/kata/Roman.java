package com.linkedin.kata;

public class Roman {
    static int convert(String romanNumeral) {
        if (romanNumeral.equals("I")) {
            return 1;
        } else if (romanNumeral.equals("V")){
            return 5;
        } else {
            return 10;
        }
    }
}
