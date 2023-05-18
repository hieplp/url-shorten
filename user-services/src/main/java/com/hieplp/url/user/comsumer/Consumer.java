package com.hieplp.url.user.comsumer;

public interface Consumer {
    Consumer init();

    Consumer cors();

    Consumer api();

    Consumer start();

    Consumer stop();
}
