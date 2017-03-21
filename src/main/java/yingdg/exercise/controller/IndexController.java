package yingdg.exercise.controller;

import com.jfinal.core.Controller;

/**
 * Created by YingDG on 2017/3/21.
 */
public class IndexController extends Controller {

    public void index() {
        redirect("index");
    }

}
