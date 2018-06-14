package cn.linj2n.thymeleaf.Repository;

import cn.linj2n.thymeleaf.domain.User;

import java.util.List;

/**
 * User repository interface
 */
public interface UserRepository {

    /**
     * save or update User information by pass a value of User object
     * @param user
     * @return
     */
    User saveOrUpdateUser(User user);

    /**
     * delete User by id
     * @param id
     */
    void deleteUser(Long id);

    /**
     * get User by id
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * list Users
     * @return
     */
    List<User> listUsers();


}
