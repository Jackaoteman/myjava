package com.mycompany.dao;

import com.mycompany.domain.Queryvo;
import com.mycompany.domain.User;
import com.mycompany.domain.User2;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {

    List<User2>   findAll();

    void  saveUser(User user);

     void  deleteUser(Integer id);

     void   updateUser(User user);

     //多条件查询
     List<User>   findByvo(Queryvo vo);

     //多条件查询
     List<User>   findByusernameAndAge(@Param("username") String username,@Param("age") int age);
     //List<User>   findByusernameAndAge(Map map);


    //条件查询个数
    int  findToTal(@Param("username") String  name,@Param("age") int age);








}
