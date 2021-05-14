package com.flywithu.forvisualbank.parser;

import lombok.Setter;

public interface MessageParser {
    public abstract void setAccount(String s);
    public abstract SpendInfo getInfo(String title,String content,long when);
}
