package com.example.demo.adapter.demo.passport.adapterv2.adapters;


import com.example.demo.adapter.demo.passport.PassportService;
import com.example.demo.adapter.demo.passport.ResultMsg;

/**
 * Created by Tom.
 */
public abstract class AbstraceAdapter extends PassportService implements ILoginAdapter {
    protected ResultMsg loginForRegist(String username, String password){
        if(null == password){
            password = "THIRD_EMPTY";
        }
        super.regist(username,password);
        return super.login(username,password);
    }
}
