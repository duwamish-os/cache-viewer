package com.cache;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import java.net.InetSocketAddress;
import java.util.function.Supplier;

public class CacheApplication {

    public static void main(String[] args) {
        String host = args[0];
        String port = args[1];

        System.out.println("connecting to : " + host + ":" + port);

        try {
            MemcachedClient cacheClient = new MemcachedClient(new InetSocketAddress(
                    host,
                    Integer.parseInt(port)
            ));

            String operation = args[2];
            Supplier<String> key = () -> args[3];
            Supplier<Integer> expiration = () -> Integer.valueOf(args[4]);
            Supplier<String> value = () -> args[5];

            System.out.println("|=========================|");
            System.out.println("| executing operation " + operation + " |");
            System.out.println("|=========================|");

            switch (operation) {
                case "add":
                    addCache(cacheClient, key.get(), expiration.get(), value.get());

                    break;
                case "set":
                    cacheClient.set(key.get(), expiration.get(), value.get()).addListener($ -> {
                        System.out.println("set cache for key " + key.get());
                        cacheClient.shutdown();
                    });
                    break;
                case "get":
                    getCache(cacheClient, key.get());
                    cacheClient.shutdown();
                    break;
                case "delete":
                    cacheClient.delete(key.get()).addListener($ -> {
                        System.out.println("deleted cache by key " + key.get() + " : " + $.get());
                        cacheClient.shutdown();
                    });
                    break;
                case "flush":
                    cacheClient.flush().addListener($ -> {
                        System.out.println("flushed cache " + $.get());
                        cacheClient.shutdown();
                    });
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getCache(MemcachedClient client, String key) {
        System.out.println("getting : " + key);
        Object o = client.get(key);
        System.out.println("get result: " + o);
    }

    private static void addCache(MemcachedClient client, String key, int expiration, String value) {
        System.out.println("adding key: " + key + ", value: " + value + ", expiration: " + expiration);
        OperationFuture<Boolean> add = client.add(key, expiration, value);
        add.addListener($ -> {
            System.out.println("add result: " + $.get());
            client.shutdown();
        });
    }
}
