package com.example.demotest20231215.xq.bean;

import com.example.demotest20231215.xq.bean.view.OperateDataResultInfoView;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix="person")
@ToString
public class Person {
    private String usrName;
    private String address;
    private
    Integer age;
    private Date birth;
    private Boolean boss;
    private List<String> hobbys;
    private Map<String,Object> map;
    private OperateDataResultInfoView dept;
    private Map<String,OperateDataResultInfoView> allDeptMap;
    //private Map<String,List<Dept>> allDeptList;
}
