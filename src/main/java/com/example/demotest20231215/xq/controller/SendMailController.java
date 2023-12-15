package com.example.demotest20231215.xq.controller;

import com.example.demotest20231215.xq.service.SendMailService;
import com.example.demotest20231215.xq.bean.AcceptMailParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @Auther: gina
 * @Date: 2023-12-13 11:09
 * @Description:发送邮件Controller 适用场景：定时发送邮件未生效时，可在这里手动触发发送邮件
 */
@Controller
@Slf4j
public class SendMailController {

    /**
     * 调试生成的页面样式，不发邮件
     * 数据处理结果集view
     */
    @Resource
    private SendMailService sendMailService;

    /**
     * http://localhost:8081/sendMail?curMonth=202311
    */
    @RequestMapping("sendMail")
    public String sendMail(Model model, @RequestParam(name = "curMonth") Integer curMonth) {
        try {
            AcceptMailParam acceptMailParam = sendMailService.getMailContentAndSendMail(curMonth);
            model.addAttribute("title", acceptMailParam.getTitle());
            model.addAttribute("pageViewList", acceptMailParam.getPageViewList());
        } catch (Exception e) {
            log.error("sendMail fail ,msg={}", e.getMessage());
        }
        return "mail/list";
    }
}


