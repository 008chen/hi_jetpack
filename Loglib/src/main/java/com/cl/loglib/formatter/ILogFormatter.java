package com.cl.loglib.formatter;

public interface ILogFormatter<T> {
    String format(T data);
}
