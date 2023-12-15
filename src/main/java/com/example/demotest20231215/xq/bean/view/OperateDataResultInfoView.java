package com.example.demotest20231215.xq.bean.view;

import com.common.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Auther: gina
 * @Date: 2023-12-13 13:41
 * @Description:处理数据结果集信息展示
 */
@Setter
@Getter
public class OperateDataResultInfoView {

    private Long id;

    /**
     * 存储当前年月,eg:202301
     */
    private Integer curMonth;
    /**
     * 库名
     */
    private String dbName;
    /**
     * 表名
     */
    private String tableName;

    /**
     * 计划生成数据量
     */
    private Long planGenDataCnt;

    /**
     * 实际生成数据量
     */
    private Long actualGenDataCnt;

    /**
     * 备注
     */
    private String remark;

    private String gmtCreate;

    private String gmtModified;

    /* *
     * 剩余待生成数据量
     */
    private Long remainToGenCnt;

    /* *
     * 生成数据成功率(实际生成数量/计划生成数量),单位%
     */
    private BigDecimal genDataSuccessRate;


    public OperateDataResultInfoView() {

        this.id = ThreadLocalRandom.current().nextLong(100L, 999L);
        this.curMonth = DateUtil.getPreMonth();
        String[] dbAndTableName = ("dbName" + id + ".tableName" + id).split("\\.");
        this.dbName = dbAndTableName[0];
        this.tableName = dbAndTableName[1];
        this.planGenDataCnt = id + ThreadLocalRandom.current().nextLong(10L, 99L);
        this.actualGenDataCnt = id;
        this.remark = null;
        this.gmtCreate = DateUtil.getNowStr();
        this.gmtModified = DateUtil.getNowStr();
        this.remainToGenCnt = (planGenDataCnt - actualGenDataCnt) >= 0 ? (planGenDataCnt - actualGenDataCnt) : 0;

        BigDecimal rate = new BigDecimal(actualGenDataCnt).divide(new BigDecimal(planGenDataCnt), 4, BigDecimal.ROUND_HALF_UP);
        this.genDataSuccessRate = rate.compareTo(new BigDecimal("1")) >= 0 ? new BigDecimal("100") : rate.multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}


