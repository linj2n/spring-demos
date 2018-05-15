package cn.linj2n.service.impl;

import cn.linj2n.dao.LoginInfoDaoApi;
import cn.linj2n.dao.UserDaoApi;
import cn.linj2n.domain.LoginLog;
import cn.linj2n.domain.User;
import cn.linj2n.service.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserServiceApi {
    private UserDaoApi userDao;
    private LoginInfoDaoApi loginInfoDao;

    @Autowired
    public void setUserDao(UserDaoApi userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginInfoDaoApi(LoginInfoDaoApi loginInfoDaoApi) {
        this.loginInfoDao = loginInfoDaoApi;
    }

    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userDao.getMatchCount(userName,password);
        return matchCount > 0;
    }

    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    @Transactional
    public void loginSuccess(User user) {
        System.out.println("call loginSuccess: ");
        System.out.println(user.toString());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());

        // 持久化登录信息至数据库
        userDao.updateLoginInfo(user);
        loginInfoDao.insertLoginInfo(loginLog);
    }
}
