package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Lend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LendRepository extends JpaRepository<Lend,Long> {

}
