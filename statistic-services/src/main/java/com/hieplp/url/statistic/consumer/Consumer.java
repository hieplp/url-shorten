package com.hieplp.url.statistic.consumer;

public interface Consumer {
    Consumer init();

    Consumer kafka();

    Consumer api();

    Consumer cors();

    Consumer start();

    Consumer stop();
}
