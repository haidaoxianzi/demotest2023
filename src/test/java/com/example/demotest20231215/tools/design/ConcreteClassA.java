package com.example.demotest20231215.tools.design;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: gina
 * @Date: 2023-11-08 10:48
 * @Description:
 */
@Slf4j
public  class ConcreteClassA extends AbstractClassTemplate{


    @Override
    void step3() {
        log.info("do step3...A");
    }

    @Override
    void step4() {
        log.info("do step4...A");
    }
}
