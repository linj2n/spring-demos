package cn.linj2n.spring.security.formlogin.repository;

import cn.linj2n.spring.security.formlogin.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
