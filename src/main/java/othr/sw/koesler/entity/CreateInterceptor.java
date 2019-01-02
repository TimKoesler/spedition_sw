package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.Protokollieren;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor @Protokollieren
public class CreateInterceptor implements Serializable {

    @AroundInvoke
    public Object parseInfoToSysOut(InvocationContext context) throws Exception {
        System.out.println("Method called: " + context.getMethod() + " Parameters: " + context.getParameters());

        Object result = context.proceed();

        return result;
    }
}
