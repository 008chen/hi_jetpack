package com.cl.loglib;

//import android.support.annotation.NonNull;

import androidx.annotation.NonNull;

import com.cl.loglib.Printer.ILogPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogManager {
    private LogConfig logConfig;

    private static LogManager instance;


    private List<ILogPrinter> printers =   new ArrayList<>();

    private LogManager(LogConfig logConfig,ILogPrinter[] printers) {
        this.logConfig = logConfig;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static LogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull LogConfig config,ILogPrinter... printers) {
        instance = new LogManager(config, printers);
    }

    public LogConfig getLogConfig() {
        return logConfig;
    }


    public void addPointer(ILogPrinter printer)
    {
        printers.add(printer);
    }
    public void removePointer(ILogPrinter printer)
    {
        if(printer !=null) {
            printers.remove(printer);
        }
    }

    public List<ILogPrinter> getPrinters() {
        return printers;
    }
}
