package yingdg.exercise.repository;

import com.jfinal.aop.Before;
import com.jfinal.aop.Enhancer;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.*;
import com.jfinal.plugin.activerecord.cache.EhCache;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import yingdg.exercise.controller.aop.Test2AOP;
import yingdg.exercise.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by YingDG on 2017/3/21.
 */
@Before(Test2AOP.class)
public class UserDao2 {
    /*
    定义的 public static final User dao 对象是全局共享的，
    只能用于数据库查询， 不能用于数据承载对象 ，
    数据承载需要使用 new User()实现
    */
    public static final User dao = new User();

    static {
        Prop p = PropKit.use("jdbc.properties");
        C3p0Plugin c3p0Plugin = new C3p0Plugin(p.get("jdbc.url"), p.get("username"), p.get("password"));
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(c3p0Plugin);
        activeRecordPlugin.setCache(new EhCache());
        activeRecordPlugin.addMapping("User", User.class);

        // 与web环境需要手动调用一次相关插件的start()方法
        c3p0Plugin.start();
        activeRecordPlugin.start();
    }

    /* CRUD操作，Db + Record 用法*/

    public boolean createUser() {
        Record user = new Record().set("username", "wangwu").set("age", 30);
        return Db.save("user", user);
    }

    @Before(Tx.class) // 声明式事务
    public boolean removeUser() {
        return Db.deleteById("user", 1);
    }

    public boolean updateUser() {
        Record user = Db.findById("user", 4).set("username", "maqi");
        return Db.update("user", user);
    }

    public User findUser() {
        Record user = Db.use("main").findById("user", 4);
        System.out.println(user);
        /*
        只有在同一个Model希望对应到多个数据源的table时才需要使用 use 方法，
        如果同一个 Model 唯一对应个数据源的一个 table，那么数据源的切换是自动的，无需使用 use 方法
         */
        return UserDao2.dao.use("main") // 可以选择使用不同的数据源，只需调用一次use方法即可切换到另一数据源上去
                .findById(3);
    }

    public List<User> findAll() {
        // Db+Record 用法
        List<Record> records = Db.use("main").find("select * from user");
        System.out.println(records);

        return UserDao2.dao.find("select * from user");
    }

    public void pageFindUsers() {
        // 分页查询年龄大于20的user,当前页号为1,每页2个user
        Page<User> userPage = UserDao2.dao.paginate(
                1,
                2,
                "select *",
                "from user where age > ?",
                20);

        List<User> userList = userPage.getList();
        // System.out.println(userList);

        Page<Record> userPage2 = Db.paginate(
                1,
                2,
                "select *",
                "from user where age > ?",
                20
        );
        System.out.println(userPage2);
    }

    public static void main(String[] args) {
        UserDao2 userDao = Enhancer.enhance(UserDao2.class);

        // userDao.createUser();
        // userDao.pageFindUsers();
        userDao.findUser();

        // JFinal事务
        Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                int result1 = Db.update("update user set username = ?, age = ? where id = ?", "user2", 10, 13);
                int result2 = Db.update("update user set age = age +2 where id = ?", 14);
                return result1 == 1 && result2 == 2;
            }
        });

    }

}
