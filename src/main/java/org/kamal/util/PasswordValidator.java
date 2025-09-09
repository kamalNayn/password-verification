package org.kamal.util;

import org.kamal.exception.IncorrectPasswordException;

public class PasswordValidator {

    /*
        Validate Password
     */
    public static boolean isValidPassword(String password){
        //conditionMet counter
        int conditionMet = 0;

        try{
            //check if password is not null
            if (password != null) {
                conditionMet++;

                // check password length should be larger than 8
                if(password.length()<8){
                    throw new IncorrectPasswordException("Password should be larger than 8 chars");
                }else {
                    conditionMet++;
                }
                int hasOneUpperCase = 0;
                int hasOneLowerCase = 0;
                int hasOneNumber = 0;

                for(char c: password.toCharArray()){
                    if (Character.isUpperCase(c)) {
                        hasOneUpperCase++;
                    } else if (Character.isLowerCase(c)) {
                        hasOneLowerCase++;
                    } else if (Character.isDigit(c)) {
                        hasOneNumber++;
                    }
                }
                //check if password has one upper case
                if(hasOneUpperCase==0){
                    throw new IncorrectPasswordException("Password should have one uppercase letter at least");
                }else{
                    conditionMet++;
                }
                //check if password has one lower case
                if(hasOneLowerCase==0){
                    throw new IncorrectPasswordException("Password should have one lowercase letter at least");
                }else {
                    conditionMet++;
                }
                //check if password has one number
                if(hasOneNumber==0){
                    throw new IncorrectPasswordException("Password should have one number at least");
                }else {
                    conditionMet++;
                }
                return conditionMet >= 3;
            }else{
                throw new IncorrectPasswordException("Password should not be null");
            }
        }catch (IncorrectPasswordException e){
            if(conditionMet>=3){
                return true;
            }else{
                throw e;
            }
        }
    }
}
