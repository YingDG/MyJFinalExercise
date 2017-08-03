package yingdg.exercise.jfinalwebapp.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

/**
 * Created by YingDG on 2017/3/21.
 */
@Clear // 清除拦截器
public class IndexController extends Controller {

    public void index() {
        redirect("index.html"); // 重定向请求
    }

}
