package cn.kgc.tangcco.newdraft.entity.VO;

import cn.kgc.tangcco.newdraft.utils.IDGenerator;
import lombok.Data;

/**
 * @author 姚顺
 * @version V1.0
 * @Project: newdraft
 * @Package cn.kgc.tangcco.newdraft.entity.BO
 * @date 2020/1/23 00:47 星期四
 * 该类用于添加用户
 */
@Data
public class AddUsersVO {

    private String userId;
    private String userWebName;
    private String tel;
    private String passport;
    private String role;

    public AddUsersVO(String userWebName, String tel, String passport, String role) {
        userId = IDGenerator.getID(); //id生成器
        this.userWebName = userWebName;
        this.tel = tel;
        this.passport = passport;
        this.role = role;
    }
}
