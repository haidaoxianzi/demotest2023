package com.example.demotest20231215.tools;

import com.example.demotest20231215.tools.design.AbstractClassTemplate;
import com.example.demotest20231215.tools.design.ConcreteClassA;
import com.example.demotest20231215.tools.design2.Design2Test;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Auther: gina
 * @Date: 2023-11-02 13:17
 * @Description:设计模式调试demo  <br/>
 * todo 123wq
 * 任务顺序
 * 1、日志done  + 造数据done【策略+模版 】+ 表数据回退功能done【后边优化：改为并发形式】 + 代码上传到github + 所有缓存记得加下超时时间+
 * 错误数据排版发邮件，邮件内容如下：<br/>
 * 【1、想下要统计哪些信息，计划某表造数据量，实际造数据量，如果不一致 输出错误信息，整体成功率计算一下】 <br/>
 * 【2、哪些表是新增的，跟上个月需要造的数据量做对比。】
 * 【3、新增加一个 生成数据操作记录表，字段：
 * id ,
 * oper_type ：
 *      1、gen_data_month造数据月份，造数据指标一个json（涉及到表名和数据量）。
 *      2、actual_gen_data_cnt 月底最后一天写入实际造数据（同上）。
 *      3 数据回退 （涉及到表名和数据量）
 *      4 造数据配置表【黑名单库json，
 *      现有已造数据总配置json，
 *      次月造数据总配置】
 * create_time
 *      此表数据仅保留 最新10天数据
 *  更新时间
 *  生效状态 status 0 物理删除
 *  】
 * 
 * 当识别到没有写的表，要发邮件告知迭代
 *
 * jekins 部署，写好执行脚本直接执行就行，各个属性文件属性都保持 一致
 *
 *  //todo 123wq 本月待造业务表数据 - 上个月待造业务表数据excel  == 代码增量  ，改这里 & 增加sql
 *
 *
 *  todo 整理信息输出到启动日志，方便使用：eg：缓存key ，回退数据 链接。
 *
 *  ------ del 2、适配器模式，组合模式，观察者模式，模板模式，迭代器模式，并发模式，总结 优先级靠后
 *
 *  todo 在excel 里存储，放到项目里
 *   记录全量不参与生成数据的库 ，，灵活点。然后放到缓存，永久生效，如果改的话，把缓存清理下，直接查excel
 */
@SpringBootTest
@Slf4j
public class DesignTest {
    @Resource
    Design2Test abcTest;

    /**
     * 模板和策略模式结合使用demo
     * */
    @Test
    void aaa(){
        abcTest.test();
    }



    /** 简单demo
     * 模板模式测试
     */
    @Test
    void testTemplate() {
        AbstractClassTemplate abstractClassTemplate = new ConcreteClassA();
        abstractClassTemplate.run("hello");
        log.info("=========================================================");
        abstractClassTemplate.run("hi");
        /*输出：
        do step1...
        do step2...
        do step3...A
        do step5...
        =========================================================
        do step1...
        do step2...
        do step4...A
        do step5...*/
    }
}



