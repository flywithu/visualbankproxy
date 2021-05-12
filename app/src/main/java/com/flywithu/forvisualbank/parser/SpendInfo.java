package com.flywithu.forvisualbank.parser;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SpendInfo {
    private String title="None";
//    private String dateTime="12/31 00:00";
    private long dateTime = 0;
    private String account="None(0000)";
    private double price=0;
    private String remark="None";
    private double  balance=0;
    private String withdraw="체크카드출금";
}
