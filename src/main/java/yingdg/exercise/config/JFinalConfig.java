package yingdg.exercise.config;

import com.jfinal.config.*;
import yingdg.exercise.controller.HelloController;

public class JFinalConfig extends com.jfinal.config.JFinalConfig {
    public void configConstant(Constants me) {
        me.setDevMode(true);
    }

    public void configRoute(Routes me) {
        me.add("/hello", HelloController.class);
    }

    public void configPlugin(Plugins me) {
    }

    public void configInterceptor(Interceptors me) {
    }

    public void configHandler(Handlers me) {
    }
}