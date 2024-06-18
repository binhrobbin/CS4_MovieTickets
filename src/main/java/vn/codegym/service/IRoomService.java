package vn.codegym.service;

import vn.codegym.entity.Room;

import java.util.List;
import java.util.Optional;

public interface IRoomService {
    List<Room> findAll();
    void save(Room room);
    void update(Room room);
    void remove(Long id);
    Optional<Room> findById(Long id);
}
