package com.example.demo.adapter.demo.passport.adapterv2;


import com.example.demo.adapter.demo.passport.ResultMsg;
import com.example.demo.adapter.demo.passport.adapterv2.adapters.ILoginAdapter;
import com.example.demo.adapter.demo.passport.adapterv2.adapters.LoginForQQAdapter;
import com.example.demo.adapter.demo.passport.adapterv2.adapters.LoginForTelAdapter;
import com.example.demo.adapter.demo.passport.adapterv2.adapters.LoginForTokenAdapter;
import com.example.demo.adapter.demo.passport.adapterv2.adapters.LoginForWechatAdapter;

/**
 * Created by Tom.
 */
public class PassportForThirdAdapter implements IPassportForThird {

    public ResultMsg loginForQQ(String openId) {
        return processLogin(openId, LoginForQQAdapter.class);
    }

    public ResultMsg loginForWechat(String openId) {

        return processLogin(openId, LoginForWechatAdapter.class);

    }

    public ResultMsg loginForToken(String token) {

        return processLogin(token, LoginForTokenAdapter.class);
    }

    public ResultMsg loginForTelphone(String phone, String code) {
        return processLogin(phone, LoginForTelAdapter.class);
    }


    private ResultMsg processLogin(String id,Class<? extends ILoginAdapter> clazz){
        try {
            ILoginAdapter adapter = clazz.newInstance();
            if (adapter.support(adapter)){
                return adapter.login(id,adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
