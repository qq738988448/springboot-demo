package com.example.demo.single;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SeriableSingletonTest {
    public static void main(String[] args) {




        Singleton s1 = null;
        Singleton s2 = Singleton.getInstance();

        FileOutputStream fos = null;
        try {
            //序列化
            //把内存中对象的状态转换为字节码的形式
            //把字节码通过IO输出流，写到磁盘上
            //永久保存下来，持久化
            fos = new FileOutputStream("Singleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();

            //反序列化
            //将持久化的字节码内容，通过IO输入流读到内存中来
            //转化成一个Java对象
            FileInputStream fis = new FileInputStream("Singleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (Singleton)ois.readObject();
            ois.close();

            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
