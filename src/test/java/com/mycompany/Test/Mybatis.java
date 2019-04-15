package com.mycompany.Test;

import com.mycompany.dao.UserDao;
import com.mycompany.domain.Queryvo;
import com.mycompany.domain.User;
import com.mycompany.domain.User2;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mybatis {
    //获取配置文件
    InputStream in;
    //2.创建 SqlSessionFactory 的构建者对象
    SqlSession sqlSession;
    UserDao userDao;
//3.使用构建者创建工厂对象 SqlSessionFactory

//4.使用 SqlSessionFactory 生产 SqlSession 对象
    @Before
    public  void  init() throws IOException {
        in=Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);
        System.out.println("测试开始啦");
    }
    @After
    public  void  destory() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }
    @Test
     public  void  findAll(){
        List<User2> user = userDao.findAll();
        for (User2 u: user) {
            System.out.println(u);
        }
    }

    @Test
    public   void  saveUser(){


    }
    @Test
    public  void  deleteUser(){
        userDao.deleteUser(1);
    }

    @Test
    public  void updateUser(){
        User  user=new User();
        user.setId(9);
        user.setUsername("苏菲玛索");
        user.setAge(40);
        user.setPhone("231312414");
        user.setAddress("英国伦敦市");
        userDao.updateUser(user);
    }

    /**
     * 多条件查询
     */
    @Test
    public  void  findUserByname(){
        User  user=new User();
        user.setUsername("张三丰");
        user.setAge(23);
        Queryvo vo=new Queryvo();
        vo.setUser(user);
        List<User> u = userDao.findByvo(vo);
        for (User use : u) {
            System.out.println(use);
        }
    }

    /**
     * 多条件查询
     *
     */
    @Test
    public  void  findByusernameAndAge(){
        List<User> list = userDao.findByusernameAndAge("%张%",20);
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     *查询名字带张的，并且年龄为20的个数
     */
    @Test
    public   void  findTotal(){
        int num = userDao.findToTal("%张%", 20);
        System.out.println(num);

    }







}
