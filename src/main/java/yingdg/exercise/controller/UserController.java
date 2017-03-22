package yingdg.exercise.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
import yingdg.exercise.model.User;
import yingdg.exercise.repository.UserDao;

import java.util.List;

/**
 * Created by YingDG on 2017/3/21.
 */
public class UserController extends Controller {
    private UserDao dao = new UserDao();

    public void put() {
        dao.createUser();
        renderText("创建");
    }

    public void post() {
        dao.updateUser();
        renderText("更新");
    }

    public void delete() {
        dao.removeUser();
        renderText("删除");
    }

    public void get() {
        User user = dao.findUser();
        renderText("查询\n" + user.toString());
    }

    public void page() {
        dao.pageFindUsers();
        renderText("分页");
    }

    public void all() {
        List<User> all = dao.findAll();
        System.out.println(all);
        renderText("全查\n" + all);
    }

    @ActionKey("/user/all/cache")
    /*
    CacheInterceptor可以将 action 将所需数据全部缓存起来，
    下次请求到时如果 cache 存在则直接使用数据并 render，而不会去调用 action
    默认使用法将使用 action Key 作为 cacheName
     */
    @Before(CacheInterceptor.class)
    @CacheName("/user/all/cache")
    public void all2() {
        // List<User> users = UserDao.dao.findByCache("/user/all/cache", "id", "select * from user");
        // if (users == null || users.isEmpty()) {
        List<User> all = dao.findAll();
        setAttr("request", all).render(all.toString());
        // }
    }

}
