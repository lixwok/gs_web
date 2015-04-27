package cn.com.wonder.xlab.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Description:四则混合运算
 * Created by Administrator on 2015/4/24.
 */
public class Calculator {
    public static void main(String[] args) {
        HashMap<String,Double>hmp = new HashMap<String,Double>();
        hmp.put("a",12.00);
        hmp.put("b",12.00);
        hmp.put("c",12.00);
        Double x = getValueByFormula("a+b+c",hmp);
        System.out.println("x = " + x);
    }

    /**
     *
     * @param formula
     * @return
     */
    public static Double getValueByFormula(String formula,HashMap<String, Double> hmp){
        //存放转换后的表达式
        LinkedList formulaList = new LinkedList();
        char[] ch = formula.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i < ch.length ; i++){
            Double value = 0.00;
            if (ch[i] == '+' || ch[i] == '-' || ch[i] == '*' || ch[i] == '/' || ch[i] == '(' ||ch[i] == ')'){
                if (sb.toString().equals("")) {
                    formulaList.add(new Character(ch[i]).toString());
                }else{
                    try {
                        value = getHmpValue(hmp, sb.toString().trim());
                    } catch (Exception e){
                        //公式本事可能包含数字
                        value = Double.parseDouble(sb.toString().trim());
                    }
                    //清空StringBuffer
                    sb.setLength(0);
                    formulaList.add(String.valueOf(value));
                    formulaList.add(new Character(ch[i]).toString());
                }
            //处理最后一个counter,如(vs+sb)*cs
            }else if(i == ch.length-1 && ch[ch.length-1] != ')'){
                //将最后一个字符加入sb中,形成完成的一个counter；
                sb.append(new Character(ch[i]).toString());
                try{
                    value = getHmpValue(hmp,sb.toString().trim());
                }catch (Exception e){
                    //公式本事可能包含数字
                    value = Double.parseDouble(sb.toString().trim());
                }
                sb.setLength(0);
                formulaList.add(String.valueOf(value));
            }else{
                sb.append(new Character(ch[i]).toString());
            }
        }
        Double result = compute(formulaList);
        return result;
    }
    public static Double compute(LinkedList formulaList) {
        LinkedList postFormula = transform(formulaList);
        Double result = doParse(postFormula);
        return result;
    }
    /**
     * 将中序表达式转化成后序表达式（逆波兰式）
     * @return
     */
    public static Double doParse(LinkedList inputList){
        String ch;
        Double num1,num2,interAns;
        Stack stack = new Stack();
        for (int j = 0;j <inputList.size(); j++){
            // for each char,
            ch = (String) inputList.get(j); // read from input
            if (ch.equals("+") || ch.equals("-") || ch.equals("*")|| ch.equals("/")) {
                // if it's a operator
                num2 = (Double) stack.pop();
                num1 = (Double) stack.pop();
                if (ch.equals("+")) {
                    interAns = new Double(num1.doubleValue() + num2.doubleValue());
                } else if (ch.equals("-")) {
                    interAns = new Double(num1.doubleValue() - num2.doubleValue());
                } else if (ch.equals("*")) {
                    interAns = new Double(num1.doubleValue() * num2.doubleValue());
                } else if (ch.equals("/")) {
                    if (num2.doubleValue() != 0)
                        interAns = new Double(num1.doubleValue() / num2.doubleValue());
                    else
                        interAns = new Double(0);
                } else {
                    interAns = new Double(0);
                }
                stack.push(interAns);
            } else {
                // it's an number
                stack.push(new Double(ch));
            }
        }
        interAns = (Double) stack.pop();
        return interAns;
    }
    public static LinkedList transform(LinkedList inputList){
        Stack stack = new Stack();
        LinkedList outputList = new LinkedList();
        for (int i= 0; i< inputList.size();i++){
            Object value = inputList.get(i);
            if (value.equals("+")||value.equals("-")){
                getOper(value,1,stack,outputList);
            }else if (value.equals("*")||value.equals("/")){
                getOper(value,2,stack,outputList);
            }else if (value.equals("(")) {
                stack.push(value);
            }else if (value.equals(")")){
                while (!stack.isEmpty()){
                    Object ch = stack.pop();
                    if (ch.equals("("))
                        break;
                    else
                        outputList.add(ch);
                }
            }else{
                outputList.add(value);
            }

        }
        while (!stack.empty())
            outputList.add(stack.pop());
        return  outputList;
    }

    /**
     * @param value
     * @param perc  优先级
     * @param stack 运算符
     * @param outputList 输出表达式
     */
    public static void getOper(Object value,int perc,Stack stack,LinkedList outputList){
        while (!stack.isEmpty()){
            Object popValue = stack.pop();
            //如果是操作符“（”， 则放回栈中
            if (popValue.equals("(")){
                stack.push(popValue);
                break;
            }else{
                int per;
                if (popValue.equals("+")||popValue.equals("-"))
                    per = 1;
                else
                    per = 2;
                if (per<perc){
                    stack.push(popValue);
                    break;
                }else
                    outputList.add(popValue);
            }
        }
        stack.push(value);
        
    }

    public static Double getHmpValue(HashMap<String,Double>hmp,String counter){
           return  hmp.containsKey(counter) ? hmp.get(counter) : 0.00;
    }
}
