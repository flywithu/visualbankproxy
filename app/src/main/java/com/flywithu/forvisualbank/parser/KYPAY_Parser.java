package com.flywithu.forvisualbank.parser;


import android.util.Log;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Setter;

public class KYPAY_Parser implements  MessageParser{
    private String default_account="0000";

    private SpendInfo getInfo(String title,String content,long when,String account) {
        SpendInfo si =new SpendInfo();
        si.setAccount(account);
        si.setTitle(title);
        si.setDateTime(when);
        String[] arr = Arrays.stream(content.trim().split("\n")).map(String::trim).toArray(String[]::new);
        String[] resultwon = Arrays.stream(content.trim().split("\\s* \\s*")).filter(value -> value.endsWith(("원"))).map(value -> value.replace("원","")).toArray(String[]::new);
        si.setRemark(arr[1]);
//        for(String a:resultwon)
//        {
//            System.out.println(a);
//        }
        NumberFormat frm = NumberFormat.getInstance(Locale.KOREA);

        try
        {
            si.setPrice(frm.parse(resultwon[0]).doubleValue());
        }
        catch(Exception e){}
        try
        {
            si.setBalance(frm.parse(resultwon[1]).doubleValue());
        }
        catch(Exception e){}


        return si;

    }

    @Override
    public void setAccount(String s) {
        default_account = s;
    }

    @Override
    public SpendInfo getInfo(String title,String content,long when) {

        return getInfo(title,content,when,"경기지역화폐("+default_account+")");
    }
}
