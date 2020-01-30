package cn.kgc.tangcco.newdraft.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 姚顺
 * @version V1.0
 * @Project: newdraft
 * @Package cn.kgc.tangcco.newdraft.entity.VO
 * @date 2020/1/24 00:55 星期五
 * 这里只用于在不使用list集合的情况下用 和Users类相比少了个集合
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO {
    private String userId;
    private String userWebName;
    private String userPhone;
    private String userPassword;
    private String userWechart;
    private int userRole;
    private int userStatus;
}
