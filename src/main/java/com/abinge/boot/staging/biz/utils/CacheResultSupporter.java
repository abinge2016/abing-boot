package com.abinge.boot.staging.biz.utils;

@FunctionalInterface
public interface CacheResultSupporter<T> {
    T execute();
}