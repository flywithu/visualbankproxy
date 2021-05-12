package com.flywithu.forvisualbank.gennoti;

import com.flywithu.forvisualbank.parser.SpendInfo;

public interface MessageGenerator {
    public abstract String genMessage(SpendInfo si);
}
