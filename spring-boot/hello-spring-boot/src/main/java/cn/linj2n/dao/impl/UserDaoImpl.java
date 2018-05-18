package cn.linj2n.dao.impl;

import cn.linj2n.dao.UserDaoApi;
import cn.linj2n.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
        String sqlStr = " SELECT user_id,user_name,password"
                + " FROM t_user WHERE user_name =? ";

        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[]{ userName },
                // 匿名方式实现的回调函数
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(userName);
                        user.setPassword(resultSet.getString("password"));
                    }
                }
        );
        return user;
    }

    public void updateLoginInfo(User user) {
        Object[] args = new Object[]{user.getLastVisit(),
                user.getLastIp(),user.getUserId()};
        int rs = jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, args );

    }

    @Autowired // 2. 自动注入 JdbcTemplate Bean
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public static void main (String[] args) {
        String UPDATE_LOGIN_INFO_SQL = " UPDATE t_user SET " +
                " last_visit=?,last_ip=? WHERE user_id=? ";
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();

        user.setUserId(1);
        user.setUserName("admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit(new Date());

        Object[] args1 = new Object[]{user.getLastVisit(),
                user.getLastIp(),user.getUserId()};

        JdbcTemplate jdbcTemplate = userDao.getJdbcTemplate();

        int update = jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, args1);

    }
}
