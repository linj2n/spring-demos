package cn.linj2n.thymeleaf.Repository;

import cn.linj2n.thymeleaf.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * User repository interface
 */
public interface UserRepository extends CrudRepository<User,Long>{
}
