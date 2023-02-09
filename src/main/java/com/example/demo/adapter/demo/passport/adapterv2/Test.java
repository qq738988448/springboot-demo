package com.example.demo.adapter.demo.passport.adapterv2;

import com.example.demo.adapter.demo.passport.Member;

/**
 * Created by Tom.
 */
public class Test {
    public static void main(String[] args) {
        IPassportForThird adapter = new PassportForThirdAdapter();
        adapter.loginForQQ("sdfasdfasfasfas");
        adapter.loginForTelphone("","");
        adapter.loginForToken("");
        adapter.loginForWechat("");

    }
}
