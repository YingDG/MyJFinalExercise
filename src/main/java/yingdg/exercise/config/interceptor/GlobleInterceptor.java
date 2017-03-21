package yingdg.exercise.config.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by YingDG on 2017/3/21.
 */
// AOP实现
public class GlobleInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        System.out.println("全局拦截器before" + invocation.getMethodName());
        invocation.invoke();
        System.out.println("全局拦截器after");
    }
}
