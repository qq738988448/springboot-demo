package com.example.demo.adapter.demo.passport.adapterv2.adapters;


import com.example.demo.adapter.demo.passport.ResultMsg;

/**
 * Created by Tom.
 */
public interface ILoginAdapter {
    boolean support(Object object);
    ResultMsg login(String id, Object adapter);
}
