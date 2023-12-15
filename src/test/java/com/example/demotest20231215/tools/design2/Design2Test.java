package com.example.demotest20231215.tools.design2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auther: gina
 * @Date: 2023-11-08 22:32
 * @Description:
 */
@Component
@Slf4j
public class Design2Test {

    private final Selector selector;
    public Design2Test(Selector selector) {
        this.selector = selector;
    }

    @PostConstruct
    public void test() {
        // 模拟调用端传入策略标识
        this.doInvoke("child1");
        this.doInvoke("child2");
    }

    public void doInvoke(String type) {
        BaseClass baseClass = selector.select(type);
        baseClass.templateMethod();
    }


}
