package com.hust.soict.tienlm198261.utils;

public class StringUtil {
    public static boolean stringIsNullOrEmty(String str) {
        if (str == null)
            return true;
        else {
            if (str.trim().length() <= 0)
                return true;
        }
        return false;
    }
}
