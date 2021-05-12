package com.flywithu.forvisualbank.gennoti;

import com.flywithu.forvisualbank.parser.SpendInfo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class KBPush_Gen implements  MessageGenerator{
    //String msg = "05/11 18:14 \n1111 \n호식이 \n체크카드출금 1,000원 \n잔액 2,000원";

    @Override
    public String genMessage(SpendInfo si) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm", Locale.KOREA);
        DecimalFormat wonformat = new DecimalFormat("###,###,###원");
        return String.format("%s \n%s \n%s \n%s %s \n잔액 %s",
                dateFormat.format(si.getDateTime()),
                si.getAccount().replaceAll("\\D+",""),
                si.getRemark(),
                si.getWithdraw(),
                wonformat.format(si.getPrice())
                ,wonformat.format(si.getBalance()));




    }
}
