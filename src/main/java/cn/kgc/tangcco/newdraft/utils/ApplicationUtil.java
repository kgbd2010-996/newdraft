package cn.kgc.tangcco.newdraft.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/30 13:32
 */

@Component
public class ApplicationUtil {
    public static String res(String str){
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat time = new SimpleDateFormat("HHmmss");
        String tradeDate = date.format(new Date());
        String tradeTime = time.format(new Date());
        String base = str +tradeDate+tradeTime;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5+".xlsx";
    }

}


