package com.example.demotest20231215.xq.service.impl;

import com.common.DateUtil;
import com.example.demotest20231215.xq.bean.AcceptMailParam;
import com.example.demotest20231215.xq.service.SendMailService;
import com.example.demotest20231215.xq.bean.view.OperateDataResultInfoView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: gina
 * @Date: 2023-12-12 13:49
 * @Description:发送邮件
 */
@Service
@Slf4j
public class SendMailSerivceImpl implements SendMailService {


    //template模板引擎
    @Autowired
    private TemplateEngine templateEngine;

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 纯文本邮件
     *
     * @param mail
     */
    @Async //不解释不懂自行百度，友情提示：有坑
    @Override
    public void sendTextMail(AcceptMailParam mail) {
        //建立邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); // 发送人的邮箱
        message.setSubject(mail.getTitle()); //标题
        message.setTo(mail.getReceiveEmail()); //发给谁  对方邮箱
        message.setCc(mail.getChaoSongPersonEmail());
        message.setText(mail.getContent()); //内容
        try {
            javaMailSender.send(message); //发送
        } catch (MailException e) {
            log.error("纯文本邮件发送失败->message:{}", e.getMessage());
            throw new RuntimeException("邮件发送失败");
        }
    }

    /**
     * 发送的邮件是富文本（附件，图片，html等）
     *
     * @param acceptMailParam
     * @param isShowHtml      是否解析html
     */
    @Async
    @Override
    public void sendHtmlMail(AcceptMailParam acceptMailParam, boolean isShowHtml) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            //是否发送的邮件是富文本（附件，图片，html等）
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(from);// 发送人的邮箱
            messageHelper.setTo(acceptMailParam.getReceiveEmail());//发给谁  对方邮箱
            messageHelper.setSubject(acceptMailParam.getTitle());//标题
            messageHelper.setText(acceptMailParam.getContent(), isShowHtml);//false，显示原始html代码，无效果
            //判断是否有附加图片等
            if (acceptMailParam.getAttachment() != null && acceptMailParam.getAttachment().size() > 0) {
                acceptMailParam.getAttachment().entrySet().stream().forEach(entrySet -> {
                    try {
                        File file = new File(String.valueOf(entrySet.getValue()));
                        if (file.exists()) {
                            messageHelper.addAttachment(entrySet.getKey(), new FileSystemResource(file));
                        }
                    } catch (MessagingException e) {
                        log.error("附件发送失败->message:{}", e.getMessage());
                        throw new RuntimeException("附件发送失败");
                    }
                });
            }
            //发送
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("富文本邮件发送失败->message:{}", e.getMessage());
            throw new RuntimeException("邮件发送失败");
        }
    }

    /**
     * 发送模板邮件 使用thymeleaf模板
     * 若果使用freemarker模板
     * Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
     * configuration.setClassForTemplateLoading(this.getClass(), "/templates");
     * String emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("mail.ftl"), params);
     * 目前用的比较多的是这个，springboot整合thymeleaf 发送邮件
     *
     * @param acceptMailParam
     */
    @Async
    public void sendTemplateMail(AcceptMailParam acceptMailParam, String templatePath) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(from);// 发送人的邮箱
            messageHelper.setTo(acceptMailParam.getReceiveEmail());//发给谁  对方邮箱
            messageHelper.setSubject(acceptMailParam.getTitle()); //标题
            //使用模板thymeleaf
            //Context是导这个包import org.thymeleaf.context.Context;
            Context context = new Context();
            //定义模板数据
            Map<String, Object> contentMap = new HashMap<>();
            contentMap.put("content", acceptMailParam.getContent());// mail.html调试页面展示
            contentMap.put("pageViewList", acceptMailParam.getPageViewList());//list.html页面展示数据处理结果集信息 列表

            context.setVariables(contentMap);
            //获取thymeleaf的html模板
            String emailContent = templateEngine.process(templatePath, context); //指定模板路径
            messageHelper.setText(emailContent, true);

            //判断是否有附加图片等
            if (acceptMailParam.getAttachment() != null && acceptMailParam.getAttachment().size() > 0) {
                acceptMailParam.getAttachment().entrySet().stream().forEach(entrySet -> {
                    try {
                        File file = new File(String.valueOf(entrySet.getValue()));
                        if (file.exists()) {
                            messageHelper.addAttachment(entrySet.getKey(), new FileSystemResource(file));
                        }
                    } catch (MessagingException e) {
                        log.error("附件发送失败->message:{}", e.getMessage());
                        throw new RuntimeException("附件发送失败");
                    }
                });
            }
            //发送邮件
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("模板邮件发送失败->message:{}", e.getMessage());
            throw new RuntimeException("邮件发送失败");
        } catch (Exception e) {
            log.error("模板邮件发送失败->message:{}", e.getMessage());
            throw new RuntimeException("邮件发送失败2");
        }
    }

    /**
     * 获取指定月份的邮件【邮件内容：生成数据的结果集 信息】
     * 次月1日8点 发送
     */
    @Override
    public AcceptMailParam getMailContentAndSendMail(Integer curMonth) {

        if (null == curMonth) {
            curMonth = DateUtil.getPreMonth();
        }
        if (curMonth >= DateUtil.getYearAndMonth()) {
            log.info("必须小于当前月份");
            return null;
        }
        if (curMonth % 100 == 0) {
            log.info("请输入有效月份");
            return null;
        }


        AcceptMailParam acceptMailParam = new AcceptMailParam();

        //对象拷贝，交叉元素会被赋值
        List<OperateDataResultInfoView> pageViewList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pageViewList.add(new OperateDataResultInfoView());
        }
        Collections.sort(pageViewList, Comparator.comparing(OperateDataResultInfoView::getId).reversed());
        acceptMailParam.setPageViewList(pageViewList);

        String[] emails = {"2402774969@qq.com"};
        String[] chaoSongPerson = {"2402774969@qq.com"};//2368919598@qq.com
        acceptMailParam.setReceiveEmail(emails);
        acceptMailParam.setChaoSongPersonEmail(chaoSongPerson);
        acceptMailParam.setTitle("统计业务表生成数据结果");

        //添加附件
        Map<String, Object> map = new HashMap<>();
        map.put("1-1412-拒审.zip", "/Users/gina/Downloads/FeiHe/xiaoZhiZu_TaskList/task10-同步生产数据量级/data/1412-reject.zip");
        map.put("2-test.xlsx", "/Users/gina/Downloads/FeiHe/xiaoZhiZu_TaskList/task10-同步生产数据量级/data/test.xlsx");
        acceptMailParam.setAttachment(map);
        //调试邮件内容排版时，此处可注释掉。
        //this.sendTemplateMail(acceptMailParam,"/mail/list");
        return acceptMailParam;
    }
}
