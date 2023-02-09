package com.example.demo.single;

import java.io.Serializable;


public class Singleton implements Serializable {

    public  final static Singleton INSTANCE = new Singleton();

    private Singleton(){
        if(INSTANCE !=null){
            throw new RuntimeException();
        }
    }

    public static Singleton getInstance(){
        return INSTANCE;
    }

    private Object readResolve(){ return INSTANCE;}

}
