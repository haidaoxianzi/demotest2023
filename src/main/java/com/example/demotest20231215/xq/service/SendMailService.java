package com.example.demotest20231215.xq.service;


import com.example.demotest20231215.xq.bean.AcceptMailParam;

/**
 * @Auther: gina
 * @Date: 2023-12-12 13:49
 * @Description:
 */
public interface SendMailService {

    void sendTextMail(AcceptMailParam acceptMailParam);

    void sendHtmlMail(AcceptMailParam acceptMailParam, boolean isShowHtml);


    /**
     * 邮件内容排版调试
     */
    AcceptMailParam getMailContentAndSendMail(Integer curMonth);
}