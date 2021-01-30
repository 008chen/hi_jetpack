package com.cl.loglib.formatter;

public class ThreadLogFormatter implements ILogFormatter<Thread>{
    @Override
    public String format(Thread data) {
        return "Thread: "+data.getName();
    }
}
