package yfs.study;

import org.junit.jupiter.api.Test;
import yfs.study.service.MybatisService;


public class MybatisTest {

    private MybatisService mybatisService = new MybatisService();

    @Test
    public void testSessionCache() {
        mybatisService.sessionCache();
    }

    @Test
    public void testSessionFactoryCache() {
        mybatisService.sessionFactoryCache();
    }



}
