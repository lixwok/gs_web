package cn.com.wonder.xlab.util;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Description:����������
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
        //���ת����ı��ʽ
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
                        //��ʽ���¿��ܰ�������
                        value = Double.parseDouble(sb.toString().trim());
                    }
                    //���StringBuffer
                    sb.setLength(0);
                    formulaList.add(String.valueOf(value));
                    formulaList.add(new Character(ch[i]).toString());
                }
            //�������һ��counter,��(vs+sb)*cs
            }else if(i ==ch.length-1 && ch[ch.length-1] != ')'){
                //�����һ���ַ�����sb��,�γ���ɵ�һ��counter��
                sb.append(new Character(ch[i]).toString());
                try{
                    value = getHmpValue(hmp,sb.toString().trim());
                }catch (Exception e){
                    //��ʽ���¿��ܰ�������
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
