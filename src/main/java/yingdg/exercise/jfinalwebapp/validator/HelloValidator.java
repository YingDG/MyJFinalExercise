package yingdg.exercise.jfinalwebapp.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import yingdg.exercise.jfinalwebapp.model.User;

/**
 * 前端请求校验类示例
 * <p>
 * Created by YingDG on 2017/3/23.
 */
public class HelloValidator extends Validator {

    /*
    调用 validateXxx(…) 系列方法进行后端校验
     */
    @Override
    protected void validate(Controller controller) {
        System.out.println("进入校验");
        validateRequiredString("u.username", "", "请输入用户名");
        validateRequiredString("u.age", "", "请输入年龄");
    }

    /*
    调用 controller.keepPara(…)方法将提交的值再传回页面以便保持原先输入的值，
    还可调用 controller.render( …)方法来返回相应的页面 。
    handleError 只有在校验失败时调用
     */
    @Override
    protected void handleError(Controller controller) {
        // controller.keepPara("username");
        // 如果传递过来的是 model 对象，可以使用 keepModel 方法来保持住用户输入过的数据
        controller.keepModel(User.class, "u");

        // controller.renderText("校验不通过");
        controller.render("../index.html");
        // controller.redirect("../index.html"); // 返回首页
    }

}
