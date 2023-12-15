package com.example.demotest20231215.controller;

import com.example.demotest20231215.xq.bean.AcceptMailParam;
import com.example.demotest20231215.xq.service.SendMailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: gina
 * @Date: 2023-12-12 16:34
 * @Description:发送邮件单元测试
 */
@SpringBootTest
@Slf4j
public class SendMailTestController {

    @Resource
    private SendMailService mailService;
    AcceptMailParam acceptMailParam = new AcceptMailParam();

    @BeforeEach
    public void initBean() {

        String[] emails = {"2402774969@qq.com"};
        //String[] chaoSongPerson = {"2368919598@qq.com"};
        acceptMailParam.setReceiveEmail(emails);
        // acceptMailParam.setChaoSongPersonEmail(chaoSongPerson);
        acceptMailParam.setTitle("title--1215");
        acceptMailParam.setContent(" 要不今儿开始减肥？");
    }

    /**
     * 测试通过，据资料提供者描述这里有坑，不推荐
     */
    @Test
    public void testMail() {
        try {
            mailService.sendTextMail(acceptMailParam);
        } catch (Exception e) {
            log.error("testMail fail ,msg={}", e.getMessage());
        }

    }


    @Test
    public void htmlMail() {
        try {
            AcceptMailParam acceptMailParam = new AcceptMailParam();
            Map<String, Object> map = new HashMap<>();
            map.put("1412-拒审.zip", "/Users/gina/Downloads/FeiHe/xiaoZhiZu_TaskList/task10-同步生产数据量级/data/1412-reject.zip");
            map.put("test.xlsx", "/Users/gina/Downloads/FeiHe/xiaoZhiZu_TaskList/task10-同步生产数据量级/data/test.xlsx");
            acceptMailParam.setAttachment(map);
            mailService.sendHtmlMail(acceptMailParam, false);
        } catch (Exception e) {
            log.error("htmlMail fail ,msg={}", e.getMessage());
        }

    }


}
