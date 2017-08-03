package yingdg.exercise.jfinalwebapp.repository;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import yingdg.exercise.jfinalwebapp.controller.aop.UserDaoAOP;
import yingdg.exercise.jfinalwebapp.model.User;

import java.util.List;

/**
 * Web环境下使用
 * <p>
 * Created by YingDG on 2017/3/21.
 */
public class UserDao {
    // AOP测试
    @Before(UserDaoAOP.class)
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

    public User findUser() {
        /*
        只有在同一个Model希望对应到多个数据源的table时才需要使用 use 方法，
        如果同一个 Model 唯一对应个数据源的一个 table，那么数据源的切换是自动的，无需使用 use 方法
         */
        return UserDao.dao.use("main") // 可以选择使用不同的数据源，只需调用一次use方法即可切换到另一数据源上去
                .findById(2);
    }

    public List<User> findAll() {
        // Db+Record 用法
        List<Record> records = Db.use("main").find("select * from user");
        System.out.println(records);

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
