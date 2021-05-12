package com.flywithu.forvisualbank;


import com.flywithu.forvisualbank.gennoti.KBPush_Gen;
import com.flywithu.forvisualbank.parser.KYPAY_Parser;
import com.flywithu.forvisualbank.parser.MessageParser;
import com.flywithu.forvisualbank.parser.SpendInfo;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void check_kpay()
    {
        String title = "결제 완료";
        long when =348764400000l;
        String content = "3,000원 결제가 완료되었습니다.\n" +
                "    상점명\n" +
                "    카드 잔액: 1,000원";

        MessageParser k = new KYPAY_Parser();

        SpendInfo si = k.getInfo(title,content,when);
        assertEquals(si.getAccount(),"경기지역화폐(1111)");
        assertEquals(si.getBalance(),1000,0);
        assertEquals(si.getDateTime(),348764400000l);
        assertEquals(si.getPrice(),3000,0);
        assertEquals(si.getRemark(),"상점명");
        assertEquals(si.getTitle(),"결제 완료");
        assertEquals(si.getWithdraw(),"체크카드출금");


    }

    @Test
    public void check_kbpush()
    {
        String title = "결제 완료";
        long when =348764400000l;
        String content = "3,000원 결제가 완료되었습니다.\n" +
                "    상점명\n" +
                "    카드 잔액: 1,000원";

        MessageParser k = new KYPAY_Parser();
        SpendInfo si = k.getInfo(title,content,when);
        String test = new KBPush_Gen().genMessage(si);
        String testmessage = "01/20 00:00 \n1111 \n상점명 \n체크카드출금 3,000원 \n잔액 1,000원";
        System.out.println(test);
        System.out.println(testmessage);
        assertEquals(test,testmessage);
    }
}