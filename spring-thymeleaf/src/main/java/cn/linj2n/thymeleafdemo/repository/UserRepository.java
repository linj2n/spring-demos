package cn.linj2n.thymeleafdemo.repository;

import cn.linj2n.thymeleafdemo.domain.User;

import java.util.List;

/**
 * User Repository
 */
public interface UserRepository {
    /**
     * create or update the message of the User
     * @param user
     * @return
     */
    User saveOrUpdateUser(User user);

    /**
     * delete User by id
     * @param id
     */
    void deleteUser(Long id);

    User getUserById(Long id);

    List<User> listUsers();

}
