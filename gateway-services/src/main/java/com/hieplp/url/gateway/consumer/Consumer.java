package com.hieplp.url.gateway.consumer;

public interface Consumer {
    Consumer init();

    Consumer cors();

    Consumer api();

    Consumer start();
}
