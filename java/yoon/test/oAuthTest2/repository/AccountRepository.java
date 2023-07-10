package yoon.test.oAuthTest2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.test.oAuthTest2.domain.Accounts;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByEmail(String email);

    Accounts findAccountsByEmail(String email);

}
