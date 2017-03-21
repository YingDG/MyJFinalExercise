package yingdg.exercise.controller.aop;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import yingdg.exercise.repository.UserDao;

/**
 * Created by YingDG on 2017/3/21.
 */
public class Test2AOP implements Interceptor {

    @Override
    public void intercept(Invocation invocation) {
        System.out.println("userDao." + invocation.getMethodName());
    }

}
