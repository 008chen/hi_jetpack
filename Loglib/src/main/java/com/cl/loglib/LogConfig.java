package com.cl.loglib;

public abstract class LogConfig {
    public String getGlobalTag(){
        return "iLog";
    }
  public boolean enable()
  {
      return true;
  }

}
