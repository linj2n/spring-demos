package cn.linj2n.service;
import cn.linj2n.domain.User;

public interface UserServiceApi {
    public boolean hasMatchUser(String userName,String password);
    public User findUserByUserName(String userName);
    public void loginSuccess(User user);
}
