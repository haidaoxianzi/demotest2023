package com.example.demotest20231215.tools.design;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: gina
 * @Date: 2023-11-08 11:00
 * @Description:
 */
@Slf4j
public class ConcreteClassB extends AbstractClassTemplate{
    @Override
    void step3() {
        log.info("do step3...B");
    }

    @Override
    void step4() {
        log.info("do step3...B");
    }
}
