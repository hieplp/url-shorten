package com.hieplp.url.statistic.consumer;

public interface Consumer {
    Consumer init();

    Consumer kafka();

    Consumer api();

    Consumer start();

    Consumer stop();
}
