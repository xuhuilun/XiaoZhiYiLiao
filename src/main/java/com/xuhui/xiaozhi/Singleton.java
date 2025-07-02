package com.xuhui.xiaozhi;

/**
 * @author 伦旭辉
 * @date 2025/06/25 22:12
 **/
public class Singleton {
    private static volatile Singleton instance;
    private Singleton() {

    }

    public static Singleton getInstance() {
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
