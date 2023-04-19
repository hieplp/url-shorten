package com.hieplp.url.comsumer;

public interface Consumer {
    Consumer init();

    Consumer api();

    Consumer cors();

    Consumer start();
}
