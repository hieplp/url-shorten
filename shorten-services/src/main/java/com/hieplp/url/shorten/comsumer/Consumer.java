package com.hieplp.url.shorten.comsumer;

public interface Consumer {
    Consumer init();

    Consumer api();

    Consumer cors();

    Consumer start();

    Consumer stop();
}
