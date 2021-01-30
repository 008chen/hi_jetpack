package com.cl.loglib.Printer;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cl.loglib.LogConfig;

public class ConsolePrinter implements ILogPrinter {
    @Override
    public void print(@NonNull LogConfig logConfig, int level, String tag, @NonNull String printData){
        int len = printData.length();
        int countOfSub = len / LogConfig.MAX_LEN;
        if(countOfSub>0)
        {
            int index =0;
            for (int i = 0; i < countOfSub; i++) {
                Log.println(level,tag,printData.substring(index,index+LogConfig.MAX_LEN));
                index +=LogConfig.MAX_LEN;
            }
            if(index!=len)
            {
                Log.println(level,tag,printData.substring(index,len));
            }
        }
        else
        {
            Log.println(level,tag,printData);
        }
    }

}
