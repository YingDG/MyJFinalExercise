package yingdg.exercise.controller;

import com.jfinal.core.Controller;

public class HelloController extends Controller {
    public void index() {
        renderText("Hello My JFinal.");
    }

}