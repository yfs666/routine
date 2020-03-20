package yfs.study.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import yfs.study.MyTest;
import yfs.study.entity.GrazeUser;

import java.io.InputStream;

public class MybatisService {

    /**
     * @描述 通过修改配置文件查看一级缓存是否生效
     * @author Yangfengshuai
     * @date 2019/6/16
     */
    public void sessionCache() {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String statement = "yfs.study.mapper.GrazeUserMapper.get";//映射sql的标识字符串
        //查数据
        GrazeUser grazeUser1 = sqlSession.selectOne(statement, 1);
        GrazeUser grazeUser2 = sqlSession.selectOne(statement, 1);
        System.out.println(grazeUser1 == grazeUser2);
    }

    /**
     * @描述
     * @author Yangfengshuai
     * @date 2019/6/16
     */
    public void sessionFactoryCache() {
        String statement = "yfs.study.mapper.GrazeUserMapper.get";//映射sql的标识字符串
        SqlSessionFactory sqlSessionFactory1 = this.getSqlSessionFactory();
        SqlSession sqlSession1 = sqlSessionFactory1.openSession();
        GrazeUser grazeUser1 = sqlSession1.selectOne(statement, 1);
        //换一个sqlSessionFactory去查询
        SqlSessionFactory sqlSessionFactory2 = this.getSqlSessionFactory();
        SqlSession sqlSession2 = sqlSessionFactory2.openSession();
        GrazeUser grazeUser2 = sqlSession2.selectOne(statement, 1);
        System.out.println(grazeUser1 == grazeUser2);
    }


    private SqlSessionFactory getSqlSessionFactory() {
        String resource = "conf.xml";
        InputStream is = MyTest.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sessionFactory;
    }
}
