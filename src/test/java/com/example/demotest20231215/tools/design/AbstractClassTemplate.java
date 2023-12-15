package com.example.demotest20231215.tools.design;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: gina
 * @Date: 2023-11-08 10:48
 * @Description:
 */
@Slf4j
public abstract class AbstractClassTemplate {
    void step1(String str) {
        log.info("do step1...");
        if (step2(str)) {
            step3();
        } else {
            step4();
        }
        step5();
    }


    boolean step2(String str) {
        log.info("do step2...");
        if (str.equals("hello")) {
            return true;
        }
        return false;
    }

    abstract void step3();

    abstract void step4();

    /**
     * 钩子方法，可重写也可不必重写
     */
    void step5() {

        log.info("do step5...");
    }

    public void run(String str) {
        step1(str);
    }

}
