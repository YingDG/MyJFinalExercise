package yingdg.exercise.jfinalwebapp.controller.aop;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by YingDG on 2017/3/21.
 */
public class UserDaoAOP implements Interceptor {

    @Override
    public void intercept(Invocation invocation) {
        System.out.println("userDao." + invocation.getMethodName());
        invocation.invoke();
    }

}
