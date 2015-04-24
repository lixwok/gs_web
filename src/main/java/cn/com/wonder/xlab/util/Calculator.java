package cn.com.wonder.xlab.util;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Description:四则混合运算
 * Created by Administrator on 2015/4/24.
 */
public class Calculator {
    public static void main(String[] args) {

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
            Double value = 0.00d;
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
            }else if(i ==ch.length-1 && ch[ch.length-1] != ')'){
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
//        Double result = compute(formulaList);
        return 0.00;
    }

    public static Double getHmpValue(HashMap<String,Double>hmp,String counter){
           return  hmp.containsKey(counter) ? hmp.get(counter) : 0.00;
    }
}
