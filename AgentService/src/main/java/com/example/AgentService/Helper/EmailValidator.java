package com.example.AgentService.Helper;

import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.function.Predicate;

@NoArgsConstructor
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if(matcher.matches()){
            return true;
        }
        return false;
    }
}
