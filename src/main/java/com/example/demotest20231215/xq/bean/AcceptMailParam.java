package com.example.demotest20231215.xq.bean;

import com.example.demotest20231215.xq.bean.view.OperateDataResultInfoView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @Auther: gina
 * @Date: 2023-12-12 13:46
 * @Description:邮件接收参数
 */
@Getter
@Setter
public class AcceptMailParam {
    //标题
    private String title;
    //内容
    private String content;
    //接收人邮件地址
    private String receiveEmail[];
    //抄送人邮件地址
    private String chaoSongPersonEmail[];

    //附件，value 文件的绝对地址/动态模板数据
    private Map<String, Object> attachment;

    List<OperateDataResultInfoView> pageViewList;
}
