package com.flywithu.forvisualbank.parser;

public interface MessageParser {
    public abstract SpendInfo getInfo(String title,String content,long when);
}
