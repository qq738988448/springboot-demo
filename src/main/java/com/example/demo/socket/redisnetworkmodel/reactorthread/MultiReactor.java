package com.example.demo.socket.redisnetworkmodel.reactorthread;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class MultiReactor implements Runnable{

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    public MultiReactor(int port) throws IOException {
        selector=Selector.open();
        serverSocketChannel= ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT,new MultiAcceptor(selector,serverSocketChannel));
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext()){
                    dispatch(iterator.next());
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void dispatch(SelectionKey key){
        //可能拿到的对象有两个
        // Acceptor
        // Handler
        Runnable runnable=(Runnable)key.attachment();
        if(runnable!=null){
            runnable.run(); //
        }
    }
}
