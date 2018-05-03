package xyz.frt.govern.common;

import org.junit.Before;
import org.junit.Test;
import xyz.frt.govern.GovernApplicationTests;
import xyz.frt.govern.model.User;

import java.util.Map;

import static org.junit.Assert.*;

public class BaseUtilsTest extends GovernApplicationTests {

    private User user;

    @Before
    public void before() {
        user = new User();
        user.setId(1);
        user.setUsername("phw");
        user.setPassword("199798");
        user.setPhone("17776304754");
        user.setTrueName("phw");
    }

    @Test
    public void object2ConditionMap() {
        Map<String, Object> map = BaseUtils.Object2ConditionMap(user);
        if (BaseUtils.isNullOrEmpty(map)) {
            System.out.println("Error");
            return;
        }
        System.out.println(map.toString());
    }

}