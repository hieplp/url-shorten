package com.hieplp.url.shorten.comsumer;

public interface Consumer {
    Consumer init();

    Consumer api();

    Consumer start();

    Consumer stop();
}
