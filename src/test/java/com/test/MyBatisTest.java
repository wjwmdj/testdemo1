package com.test;

import com.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisTest {
    @Test
    public void test1() throws IOException {
        //获得核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获得session对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //使用代理模式
        /*
        * 规则:
        * 1.映射文件中的namespace必须与接口的全包名一致
        * 2.映射文件中的id必须与接口的方法名一致
        * 3.映射文件中的resultType必须与接口中方法的返回值类型一致
        * 4.映射文件中的ParameterType必须与接口中方法的参数类型一致
        * */
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //创建user对象
        User user = new User();
        user.setName("测试");
        user.setPwd("anccc");
        user.setBirthday(new Date());
        //执行保存操作
        userMapper.save(user);
        sqlSession.close();

    }
    @Test
    public void test2() throws IOException {
        //获得核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获得session对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //使用代理模式
        /*
         * 规则:
         * 1.映射文件中的namespace必须与接口的全包名一致
         * 2.映射文件中的id必须与接口的方法名一致
         * 3.映射文件中的resultType必须与接口中方法的返回值类型一致
         * 4.映射文件中的ParameterType必须与接口中方法的参数类型一致
         * */
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(6);
        System.out.println(user.getBirthday());
        sqlSession.close();

    }

    @Test
    public void test3() throws IOException {
        //获得核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获得session对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //使用代理模式
        /*
         * 规则:
         * 1.映射文件中的namespace必须与接口的全包名一致
         * 2.映射文件中的id必须与接口的方法名一致
         * 3.映射文件中的resultType必须与接口中方法的返回值类型一致
         * 4.映射文件中的ParameterType必须与接口中方法的参数类型一致
         * */
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //设置分页参数 当前页 页面大小
        PageHelper.startPage(1,2);
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        //获得与分页相关的参数
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        System.out.println("当前页:"+pageInfo.getPageNum());
        System.out.println("每页显示条数:"+pageInfo.getPageSize());
        System.out.println("总条数:"+pageInfo.getTotal());
        System.out.println("总页数:"+pageInfo.getPages());
        System.out.println("上一页:"+pageInfo.getPrePage());
        System.out.println("下一页:"+pageInfo.getNextPage());
        System.out.println("是否是第一页"+pageInfo.isIsFirstPage());
        System.out.println("是否是最后一页:"+pageInfo.isIsLastPage());


        sqlSession.close();

    }
}
