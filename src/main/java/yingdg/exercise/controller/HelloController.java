package yingdg.exercise.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

@Clear // 清除拦截器
public class HelloController extends Controller {

    // @ActionKey("/go") // 替换原有/hello路由并使其失效
    // 默认访问index方法
    public void index() {
        // getPara(); // 取值
        renderText("Hello My JFinal.");
    }

}