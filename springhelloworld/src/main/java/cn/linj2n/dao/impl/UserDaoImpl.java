package cn.linj2n.dao.impl;

import cn.linj2n.dao.UserDaoApi;
import cn.linj2n.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository // 1. 通过 Spring 注解定义一个 DAO
public class UserDaoImpl implements UserDaoApi {
    private JdbcTemplate jdbcTemplate;

    private final  static String MACTCH_COUNT_SQL = " SELECT count(*) FROM t_user  " +
            " WHERE user_name =? and password=? ";

    private final static String UPDATE_LOGIN_INFO_SQL = " UPDATE t_user SET " +
            " last_visit=?,last_ip=? WHERE user_id=? ";


    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MACTCH_COUNT_SQL,new Object[]{userName,password},Integer.class);
    }

    public User findUserByUserName (final String userName) {
        final User user = new User();

        jdbcTemplate.query(MACTCH_COUNT_SQL, new Object[]{userName},
                // 匿名方式实现的回调函数
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(resultSet.getString(userName));
                    }
                }
        );
        return user;
    }

    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[] {user.getLastVisit(),
                user.getLastIp(),user.getUserId()});
    }

    @Autowired // 2. 自动注入 JdbcTemplate Bean
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
