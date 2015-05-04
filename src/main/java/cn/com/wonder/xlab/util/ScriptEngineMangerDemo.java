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
        /*script �������*/
        ScriptEngineManager sem = new ScriptEngineManager();
        /*script ����*/
        ScriptEngine se = sem.getEngineByName("javascript");
        try {
            /*����������*/
            se.eval("1+2+3");
            /* ִ��һ��script */
            se.eval(" var strname = 'Key' ") ;
            se.eval("function sayHello(   ) { " + " print('Hello '+strname+'!');return 'my name is '+strname;" + "}");   /* ���һ������*/

            Invocable invocableEngine = (Invocable) se ;
            String callBackValue= null;   /*���÷����еĺ���*/
            try {
                callBackValue = (String) invocableEngine.invokeFunction("sayHello" );
                System.out.println(callBackValue) ;              /** ��ӡ����ֵ*/
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
