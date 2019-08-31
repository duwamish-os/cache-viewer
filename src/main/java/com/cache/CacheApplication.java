package com.cache;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import java.io.IOException;
import java.net.InetSocketAddress;

public class CacheApplication {

    public static void main(String[] args) throws IOException {
        String host = args[0];
        String port = args[1];
        System.out.println("connecting to : " + host + ":" + port);

        try {
            MemcachedClient client = new MemcachedClient(new InetSocketAddress(
                    host,
                    Integer.parseInt(port)
            ));

            String operation = args[2];
            String key = args[3];

            System.out.println("====================");
            System.out.println("operation " + operation);
            System.out.println("====================");
            if (operation.equals("add")) {
                String expiration = args[4];
                String value = args[5];
                System.out.println("adding key: " + key + ", value: " + value + ", expiration: " + expiration);
                OperationFuture<Boolean> add = client.add(key, Integer.valueOf(expiration), value);
                add.addListener($ -> {
                    System.out.println("add result: " + $.get());
                    client.shutdown();
                });
            } else if (operation.equals("get")) {
                System.out.println("getting : " + key);
                Object o = client.get(key);
                System.out.println("get result: " + o);
                client.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
