package yingdg.exercise.jfinalwebapp.controller.aop;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by YingDG on 2017/3/21.
 */
public class TestAOP implements Interceptor {

    // 该方法会在类拦截器中间执行
    @Override
    public void intercept(Invocation invocation) {
        invocation.invoke();
        System.out.println("After method test invoking" + invocation.getViewPath());
    }
}
