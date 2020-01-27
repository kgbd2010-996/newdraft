package cn.kgc.tangcco.newdraft.utils;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

import java.io.IOException;

public class UserAgentUtils {

    public static UASparser uaSparser=null;

    static {
        try {
            uaSparser=new UASparser(OnlineUpdater.getVendoredInputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String request="";

        try {
            UserAgentInfo userAgentInfo= UserAgentUtils.uaSparser.parse(request);
            System.out.println(userAgentInfo.getDeviceType());
            System.out.println(userAgentInfo.getOsName());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
