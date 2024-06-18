package vn.codegym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
