package yingdg.exercise.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import yingdg.exercise.model.User;

/*
定义的 public 无参方法称为一个 Action。
Action 是请求的最小单位 。
Action 方法必须在 Controller中声明，该方法必须是 public 且没有形参
Controller 中提供了 getPara 、getModel 系列方法 ，setAttr方法，以及  render系列方法供 Action 使用
 */
@Clear // 清除拦截器
public class HelloController extends Controller {

    // @ActionKey("/go") // 替换原有controllerKey：/hello路由，并使其失效，同时成为绝对路由
    // 默认访问index方法
    public void index() {
        renderText("Hello My JFinal.");
    }

    public void test() {
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

    // @ActionKey("/save")
    public void save() {
        // 页面的modelName正好是Blog类名的首字母小写
        // User user = getModel(User.class);

        // 如果表单域的名称为 "u.username"可加上一个参数来获取
        User user = getModel(User.class, "u");

        // renderText(user.toString());
        renderJson(user);
    }

}