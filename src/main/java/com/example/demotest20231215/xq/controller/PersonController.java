package com.example.demotest20231215.xq.controller;

import com.example.demotest20231215.xq.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取yml文件的值
 * */
@RestController
@Slf4j
public class PersonController {
    @Autowired
    private Person person;
    /*
    http://localhost:8081/query
    * */
    @GetMapping("/query")
    public Person query(){
        log.info("spring task 同步数据定时任务createDataCorn，时间是：{}","123123123123");

        return person;
    }





}
