package com.banana.job.admin.dao;

import com.banana.job.entity.po.XxlJobLogGlue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XxlJobLogGlueDaoTest {

    @Resource
    private XxlJobLogGlueDao xxlJobLogGlueDao;

    @Test
    public void test(){
        XxlJobLogGlue logGlue = new XxlJobLogGlue();
        logGlue.setJobId(1);
        logGlue.setGlueType("1");
        logGlue.setGlueSource("1");
        logGlue.setGlueRemark("1");
        int ret = xxlJobLogGlueDao.save(logGlue);

        List<XxlJobLogGlue> list = xxlJobLogGlueDao.findByJobId(1);

        int ret2 = xxlJobLogGlueDao.removeOld(1, 1);

        int ret3 =xxlJobLogGlueDao.deleteByJobId(1);
    }

}
