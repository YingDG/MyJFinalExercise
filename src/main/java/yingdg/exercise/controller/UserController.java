package yingdg.exercise.controller;

import com.jfinal.core.Controller;
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
        User user = dao.finaUser();
        renderText("查询\n" + user.toString());
    }

    public void page() {
        dao.pageFindUsers();
        renderText("分页");
    }

    public void all() {
        List<User> all = dao.findAll();
        renderText("全查\n" + all);
    }
}
