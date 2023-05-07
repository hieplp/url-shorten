package com.hieplp.url.auth.comsumer;

public interface Consumer {
    Consumer init();

    Consumer cors();

    Consumer api();

    Consumer start();

    Consumer stop();
}
