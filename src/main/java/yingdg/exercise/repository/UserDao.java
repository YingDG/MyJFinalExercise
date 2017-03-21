package yingdg.exercise.repository;

import com.jfinal.aop.Before;
import yingdg.exercise.controller.aop.Test2AOP;

/**
 * Created by YingDG on 2017/3/21.
 */
public class UserDao {
    // AOP测试
    @Before(Test2AOP.class)
    public void go() {
        System.out.println("UserDao goes.");
    }
}
