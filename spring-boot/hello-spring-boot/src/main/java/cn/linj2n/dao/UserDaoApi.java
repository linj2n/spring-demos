package cn.linj2n.dao;

import cn.linj2n.domain.User;

public interface UserDaoApi {

    public int getMatchCount(String userName, String password);

    public User findUserByUserName(final String userName);

    public void updateLoginInfo(User user);

}
