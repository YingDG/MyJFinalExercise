package yingdg.exercise.repository;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import yingdg.exercise.controller.aop.Test2AOP;
import yingdg.exercise.model.User;

import java.util.List;

/**
 * Created by YingDG on 2017/3/21.
 */
public class UserDao {
    // AOP测试
    @Before(Test2AOP.class)
    public void go() {
        System.out.println("UserDao goes.");
    }

    /* CRUD操作*/

    /*
    定义的 public static final User dao 对象是全局共享的，
    只能用于数据库查询， 不能用于数据承载对象 ，
    数据承载需要使用 new User()实现

    需要Web启动实现
    */
    public static final User dao = new User();

    public boolean createUser() {
        return new User()
                // .set("id", 1)
                .set("username", "zhangsan")
                .set("age", 22)
                .save();
    }

    public boolean removeUser() {
        return UserDao.dao.deleteById(1);
    }

    public boolean updateUser() {
        return UserDao.dao.findById(2).set("username", "lisi").update();
    }

    public User finaUser() {
        return UserDao.dao.findById(2);
    }

    public List<User> findAll() {
        return UserDao.dao.find("select * from user");
    }

    public void pageFindUsers() {
        // 分页查询年龄大于20的user,当前页号为1,每页2个user
        Page<User> userPage = UserDao.dao.paginate(
                1,
                2,
                "select *",
                "from user where age > ?",
                20);

        List<User> userList = userPage.getList();
        System.out.println(userList);
    }

}
