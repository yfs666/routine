package yfs.study;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import yfs.study.entity.GrazeUser;

import java.io.InputStream;

public class MyTest {
    public static void main(String[] args) {
        String resource = "conf.xml";
        InputStream is = MyTest.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //查数据
        GrazeUser grazeUser = getById(sessionFactory);
        grazeUser.setName("yangfengshuai");
        //查数据
        GrazeUser grazeUser1 = getById(sessionFactory);
        System.out.println(grazeUser);
    }

    public static GrazeUser getById(SqlSessionFactory sessionFactory) {
        SqlSession session = sessionFactory.openSession();
        String statement = "yfs.study.mapper.GrazeUserMapper.get";//映射sql的标识字符串
        //查数据
        GrazeUser grazeUser = session.selectOne(statement, 1);
        session.commit();
        return grazeUser;
    }

}

