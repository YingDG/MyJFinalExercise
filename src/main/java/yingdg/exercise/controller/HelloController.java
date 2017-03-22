package yingdg.exercise.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import yingdg.exercise.controller.aop.HelloAOP;
import yingdg.exercise.controller.aop.Test2AOP;
import yingdg.exercise.controller.aop.TestAOP;
import yingdg.exercise.model.User;
import yingdg.exercise.repository.UserDao;

/*
定义的 public 无参方法称为一个 Action。
Action 是请求的最小单位 。
Action 方法必须在 Controller中声明，该方法必须是 public 且没有形参
Controller 中提供了 getPara 、getModel 系列方法 ，setAttr方法，以及  render系列方法供 Action 使用
 */
// 配置一个Class级别的拦截器，她将拦截本类中的所有方法
@Before(HelloAOP.class)
public class HelloController extends Controller {

    // @ActionKey("/go") // 替换原有controllerKey：/hello路由，并使其失效，同时成为绝对路由
    // 默认访问index方法
    public void index() {
        renderText("Hello My JFinal.");
    }

    // 配置多个Method级别的拦截器，仅拦截本方法
    @Before({TestAOP.class/*, Test2AOP.class*/}) // 方法级AOP不能与其他类共用，否则会有异常
    public void test() {
        // 示例Url：http://localhost:8080/hello/test/2-N2-a=2

        String para1 = getPara(0); // 获取/test/之后的参数，以"-"分隔，从下标0开始
        int para2 = getParaToInt(1); // 此时N或n可以表示负号，如：2-N8
        String para3 = getPara("a"); // 表单域名为"a"的值，url：?a=2
        renderText("Test." + para1 + para2);
    }

    @ActionKey("/mytest") // 此时/hello/test无效
    public void test2() {
        String para = getPara();// 获取/test/之后的全部参数作为字符串，不考虑有没有"-"
        renderText("Test." + para);
    }

    @Clear(HelloAOP.class) // 清除指定的拦截器，但Method级的拦截器无法被清除
    public void save() {
        // 页面的modelName正好是Blog类名的首字母小写
        // User user = getModel(User.class);

        // 如果表单域的名称为 "u.username"可加上一个参数来获取
        User user = getModel(User.class, "u");

        // AOP测试
        UserDao userDao1 = enhance(UserDao.class); // Controller.enhance()方法
        userDao1.go();
        UserDao userDao2 = Enhancer.enhance(UserDao.class); // 触发AOP
        userDao2.go();
//        UserDao userDao3 = Enhancer.enhance(UserDao.class, TestAOP.class);
//        userDao3.go();

        // renderText(user.toString());
        renderJson(user);
    }

}