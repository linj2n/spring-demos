package cn.linj2n.dao.impl;

import cn.linj2n.dao.LoginInfoDaoApi;
import cn.linj2n.domain.LoginLog;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class LoginInfoDaoImpl implements LoginInfoDaoApi {
    private JdbcTemplate jdbcTemplate;

    private final static String INSERT_LOGIN_INFO_SQL = " INSERT INTO t_login_log(user_id,ip,login_datetime) VALUES(?,?,?) ";

    public void insertLoginInfo(LoginLog loginLog) {
        Object[] args = new Object[]{
                loginLog.getUserId(),
                loginLog.getIp(),
                loginLog.getLoginDate()};

        int rs = jdbcTemplate.update(INSERT_LOGIN_INFO_SQL,args
                );
        System.out.println(rs);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public static void main (String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        BasicDataSource dataSource = context.getBean("dataSource",BasicDataSource.class);
    }
}
