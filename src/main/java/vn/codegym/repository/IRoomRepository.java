package vn.codegym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.entity.Room;

public interface IRoomRepository extends JpaRepository<Room, Long> {
}
