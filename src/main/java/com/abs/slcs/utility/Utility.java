package com.abs.slcs.utility;

/**
 * Transform string to url compatible parameter.
 * replace all the spaces with '%20'
 */
public class Utility {
    public  String urlify(char[] chars) {
        if (chars == null) {
            return "";
        }

        int spaceCount = 0;
        int index = 0;
        int len = chars.length-1;
        // first loop, locate last position
        for (int i = 0; i < len; i++) {
            if (chars[i] == ' ') {
                spaceCount++;
            }
        }
        char[] result= new char[len+spaceCount*3];
        spaceCount=0;
        for (int i = 0; i <= len; i++) {
            if (chars[i] == ' ') {
                result[i+spaceCount]='%';
                result[i+spaceCount+1]='2';
                result[i+spaceCount+2]='0';
                spaceCount+=2;
            }else {
                result[i+spaceCount]= chars[i];

            }
        }


        return new String(result).trim();
    }

}
