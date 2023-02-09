package com.example.demo.single;

import java.lang.reflect.Constructor;
import java.nio.file.FileVisitOption;


public class ReflectTest {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Singleton.class;
            Constructor c = clazz.getDeclaredConstructor(null);
            c.setAccessible(true);

            Object instance1 = Singleton.getInstance();

            Object instance2 = c.newInstance();


            System.out.println(instance1);

            System.out.println(instance2);

            System.out.println(instance1 == instance2);

            Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("com.example.demo.single.SingletonEnum");
            FileVisitOption fileVisitOption = Enum.valueOf(FileVisitOption.class, "FOLLOW_LINKS");
            FileVisitOption followLinks = FileVisitOption.FOLLOW_LINKS;
            System.out.println();
            aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
