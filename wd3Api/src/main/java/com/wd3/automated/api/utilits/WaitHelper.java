package com.wd3.automated.api.utilits;

import lombok.SneakyThrows;

public class WaitHelper {

    @SneakyThrows
    public static void sleep(long millis){
        Thread.sleep(millis);
    }
}
