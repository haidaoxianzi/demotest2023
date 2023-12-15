package com.example.demotest20231215.tools.design2;

/**
 * @Auther: gina
 * @Date: 2023-11-09 09:12
 * @Description:
 */

import org.springframework.stereotype.Component;

/**
 * 子类2
 */
@Component
@SelectorAnno("child2")
public class ChildClass2 extends BaseClass {

    public ChildClass2(QueryService queryService) {
        super.queryService = queryService;
    }

    @Override
    protected void method1() {
        System.out.println("执行子类2的方法1");
    }

}

