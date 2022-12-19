package com.sophos.bankapp.service;

public class AccountGenerator {
    
    public String accountGenerator(String accountType){

        String code = "";
    
        if (accountType.equalsIgnoreCase("Saving")){
            code = "46";
            for (int i=1; i<=8; i++) {
                    
                int number = (int)(Math.random()*10);
                String a = String.valueOf(number);
                    
                code = code + a;	
            }
    
            } else if (accountType.equalsIgnoreCase("Checking")){
                code = "23";
                for (int i=1; i<=8; i++) {
                   
                   int number = (int)(Math.random()*10);
                   String a = String.valueOf(number);
                   
                   code = code + a;	
               }
    
            } 
        return code;
    }

}
