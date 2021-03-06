package cn.kgc.tangcco.newdraft;

import cn.kgc.tangcco.newdraft.dao.ApplicationDao;
import cn.kgc.tangcco.newdraft.dao.PersonalCenterDao;
import cn.kgc.tangcco.newdraft.entity.News;
import cn.kgc.tangcco.newdraft.entity.Resources;
import cn.kgc.tangcco.newdraft.entity.Users;
import cn.kgc.tangcco.newdraft.service.ApplicationService;
import cn.kgc.tangcco.newdraft.service.LoginRegisterService;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewdraftApplicationTests {
    @Resource
    private ApplicationDao applicationDao;
    @Resource
    private ApplicationService applicationService;
    @Resource
    private LoginRegisterService loginRegisterService;
    @Resource
    private PersonalCenterDao personalCenterDao;



    @Test
    public void test03() {
        Resources resources = applicationDao.gettemplateResource();
        System.out.println(resources.getResLink());
    }


    @Test
    public void test04() {
        Resources resources = applicationService.gettemplateResource();
        System.out.println(resources.getResLink());
    }

    @Test
    public void test05() {
        Users userRoleByUserId = applicationDao.getUserRoleByUserId("3f910dc12718");
        System.out.println(userRoleByUserId.getUserRole());
    }

    @Test
    public void test06() {
        boolean userRoleByUserId = applicationService.getUserRoleByUserId("3f910dc12718");
        System.out.println(userRoleByUserId);
    }

    @Test
    public void test07() {
        int i = applicationService.addResource("xxxx", "xxxx", "xxxxx");
        System.out.println(i);
    }

    @Test
    public void test08() {
        int i = applicationDao.addmidClientsFirms("xxxx","xxxx");
        System.out.println(i);
    }
    @Test
    public void test09() {
        int i = applicationService.addmidClientsFirms("xxxx","xxxx");
        System.out.println(i);
    }

    @Test
    public void test10() {

    }



}
