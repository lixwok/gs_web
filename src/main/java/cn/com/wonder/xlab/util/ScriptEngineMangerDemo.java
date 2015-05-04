package cn.com.wonder.xlab.util;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Administrator on 2015/4/24.
 */
public class ScriptEngineMangerDemo {
    public static void main(String[] args) {
        /*script 引擎管理*/
        ScriptEngineManager sem = new ScriptEngineManager();
        /*script 引擎*/
        ScriptEngine se = sem.getEngineByName("javascript");
        try {
            /*四则混合运算*/
            se.eval("1+2+3");
            /* 执行一段script */
            se.eval(" var strname = 'Key' ") ;
            se.eval("function sayHello(   ) { " + " print('Hello '+strname+'!');return 'my name is '+strname;" + "}");   /* 添加一个方法*/

            Invocable invocableEngine = (Invocable) se ;
            String callBackValue= null;   /*调用方法中的函数*/
            try {
                callBackValue = (String) invocableEngine.invokeFunction("sayHello" );
                System.out.println(callBackValue) ;              /** 打印返回值*/
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
