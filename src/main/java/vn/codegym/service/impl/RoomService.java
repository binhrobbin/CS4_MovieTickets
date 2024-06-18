package vn.codegym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.entity.Room;
import vn.codegym.repository.IRoomRepository;
import vn.codegym.service.IRoomService;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {
    @Autowired
    IRoomRepository roomRepository;
    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public void save(Room Room) {
        roomRepository.save(Room);
    }

    @Override
    public void update(Room Room) {
        roomRepository.save(Room);
    }

    @Override
    public void remove(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }
}
