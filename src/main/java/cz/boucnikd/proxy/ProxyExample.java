package cz.boucnikd.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyExample {

    public static void main(String[] args) {
        // create a new instance of the real object
        RealObject realObject = new RealObject();

        // create a proxy instance of the real object
        MyInvocationHandler invocationHandler = new MyInvocationHandler(realObject);
        Interface proxyObject = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[] { Interface.class },
                invocationHandler
        );

        // call a method on the proxy object
        proxyObject.doSomething();
    }

    private interface Interface {
        void doSomething();
    }

    private static class RealObject implements Interface {
        public void doSomething() {
            System.out.println("Real object does something");
        }
    }

    private static class MyInvocationHandler implements InvocationHandler {
        private final Object realObject;

        public MyInvocationHandler(Object realObject) {
            this.realObject = realObject;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Proxy does something before invoking the method");
            Object result = method.invoke(realObject, args);
            System.out.println("Proxy does something after invoking the method");
            return result;
        }
    }
}
