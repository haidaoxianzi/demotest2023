package com.example.demotest20231215.tools.design2;

/**
 * @Auther: gina
 * @Date: 2023-11-09 09:07
 * @Description:
 */
public abstract class BaseClass {

    protected QueryService queryService;

    final public void templateMethod() {
        System.out.println("执行模板方法");
        queryService.query();
        method1();
    }

    abstract protected void method1();

}

