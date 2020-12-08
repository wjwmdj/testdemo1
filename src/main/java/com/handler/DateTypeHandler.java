package com.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DateTypeHandler extends BaseTypeHandler<Date> {

    /**
     * 将java类型转换成数据库需要的类型
     * @param preparedStatement 设置类型使用
     * @param i 位置
     * @param date java类型的数据
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        //将date类型转换成为数字类型
        Long time = date.getTime();
        preparedStatement.setLong(i,time);
    }


    /**
     * 将数据库中类型转换成为java类型
     * @param resultSet 查询出的结果集
     * @param s 数据库中字段的名称
     * @return 返回转换后的java类型
     * @throws SQLException
     */
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        //获取出结果集中的数据并转换成为Date类型
        Long aLong = resultSet.getLong(s);
        Date date = new Date(aLong);
        return date;
    }

    /**
     * 将数据库中类型转换成为java类型
     * @param resultSet 查询出的结果集
     * @param i 数据库中字段的位置
     * @return 返回转换后的java类型
     * @throws SQLException
     */
    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        //获取出结果集中的数据并转换成为Date类型
        Long aLong = resultSet.getLong(i);
        Date date = new Date(aLong);
        return date;
    }

    //将数据库中类型转换成为java类型
    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Long aLong = callableStatement.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
}
