package cn.linj2n.verification.repository;

import cn.linj2n.verification.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
