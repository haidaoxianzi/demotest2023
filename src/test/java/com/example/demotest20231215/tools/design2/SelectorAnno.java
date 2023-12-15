package com.example.demotest20231215.tools.design2;
import java.lang.annotation.*;

/**
 * @Auther: gina
 * @Date: 2023-11-09 09:14
 * @Description:
 */

/**
 * 选择器注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
@Documented
public @interface SelectorAnno {

    String value();

}
