package com.example.demotest20231215.tools.design2;

/**
 * @Auther: gina
 * @Date: 2023-11-09 09:10
 * @Description:
 */

import org.springframework.stereotype.Component;

/**
 * 子类1
 */

/**
 * 子类1
 */
@Component
@SelectorAnno("child1")
public class ChildClass1 extends BaseClass {

    public ChildClass1(QueryService queryService) {
        super.queryService = queryService;
    }

    @Override
    protected void method1() {
        System.out.println("执行子类1的方法1");
    }

}


