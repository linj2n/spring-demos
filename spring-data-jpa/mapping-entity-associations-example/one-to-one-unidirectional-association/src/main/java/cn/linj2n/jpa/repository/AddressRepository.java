package cn.linj2n.jpa.repository;

import cn.linj2n.jpa.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>{
    List<Address> findByCity(String city);
}
