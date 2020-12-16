package com.virtualspacex.middleware;

public enum EventPriority {
    //可忽视的
    ignorable,

    //不可忽视的
    nonnegligible,

    //一般的
    common,

    //尽快的
    sooner,

    //重要的
    important
}