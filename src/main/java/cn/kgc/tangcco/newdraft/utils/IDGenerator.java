package cn.kgc.tangcco.newdraft.utils;


import java.util.UUID;

/**
 * @author 姚顺
 * @version V1.0
 * @Project: newdraft
 * @Package cn.kgc.tangcco.newdraft.utils
 * @date 2020/1/23 02:22 星期四
 * id生成器 随机生成一个十二位唯一的id
 */
public class IDGenerator {

    public static String getID(){
        StringBuilder builder = new StringBuilder(UUID.randomUUID().toString());
        return (builder.substring(0,8) + builder.substring(9,13) + builder.substring(14,18) + builder.substring(19,23) + builder.substring(24)).toString().substring(0, 12);
    }

    /**
     * 测试唯一性
     * @param args
     */
    public static void main(String[] args){
        String[] str = new String[10000000];
        int count = 0;
        for(int i = 0; i < 10000000; i ++){
            str[i] = getID();
            for(int j = 0; i < i; j++){
                if(str[j].equals(str[i])){
                    count ++;
                }
            }
        }
        System.out.println(count);
    }
}
