package com.company.springTest.handAOP;

public class TestAOP {

    public static void main(String[] args) {

        MethodInvocation logTask = () -> System.out.println("log task");
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);

        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloServiceImpl, beforeAdvice);

        helloServiceImplProxy.sayHelloWord();
    }


}
