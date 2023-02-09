package com.example.demo.observer.mouseevent;


import com.example.demo.observer.mouseevent.core.EventListener;
import com.example.demo.observer.mouseevent.handler.Mouse;
import com.example.demo.observer.mouseevent.handler.MouseEventListener;
import com.example.demo.observer.mouseevent.handler.MouseEventType;

/**
 * Created by Tom.
 */
public class Test {
    public static void main(String[] args) {
        EventListener listener = new MouseEventListener();

        Mouse mouse = new Mouse();
        mouse.addListener(MouseEventType.ON_CLICK,listener);
        mouse.addListener(MouseEventType.ON_MOVE,listener);

        mouse.click();
        mouse.move();
    }
}
