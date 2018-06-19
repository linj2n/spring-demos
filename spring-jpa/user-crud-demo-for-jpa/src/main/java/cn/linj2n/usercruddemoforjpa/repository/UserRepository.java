package cn.linj2n.usercruddemoforjpa.repository;

import cn.linj2n.usercruddemoforjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
}
