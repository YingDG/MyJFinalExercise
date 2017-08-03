package yingdg.exercise.jfinalwebapp.controller.aop;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by YingDG on 2017/3/21.
 */
public class HelloAOP implements Interceptor {

    // 该方法会在全局拦截器执行中间执行
    @Override
    public void intercept(Invocation invocation) {
        System.out.println("Before method hello invoking");
        invocation.invoke();
        System.out.println("After method hello invoking");
    }
}
