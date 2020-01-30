package cn.kgc.tangcco.newdraft.service;



public interface ProService {
    String isLogin(String token);
    boolean resetToken(String token);
}
