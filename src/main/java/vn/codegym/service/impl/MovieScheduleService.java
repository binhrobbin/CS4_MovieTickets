package vn.codegym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.dto.MovieScheduleDto;
import vn.codegym.entity.Movie;
import vn.codegym.entity.MovieSchedule;
import vn.codegym.entity.Room;
import vn.codegym.repository.IMovieScheduleRepository;
import vn.codegym.service.IMovieScheduleService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieScheduleService implements IMovieScheduleService {
    @Autowired
    private IMovieScheduleRepository movieScheduleRepository;
    @Override
    public List<MovieSchedule> findAll() {
        return movieScheduleRepository.findAll();
    }

    @Override
    public Page<MovieSchedule> findAll(Pageable pageable) {
        return movieScheduleRepository.findAll(pageable);
    }

    @Override
    public void save(MovieSchedule movieSchedule) {
        movieScheduleRepository.save(movieSchedule);
    }

    @Override
    public void update(long id, MovieSchedule movieSchedule) {
        movieScheduleRepository.save(movieSchedule);
    }

    @Override
    public void update(MovieSchedule movieSchedule) {
        movieScheduleRepository.save(movieSchedule);
    }

    @Override
    public void remove(long id) {
        movieScheduleRepository.deleteById(id);
    }

    @Override
    public Optional<MovieSchedule> findById(long id) {
        return movieScheduleRepository.findById(id);
    }

    @Override
    public List<MovieSchedule> findMovieSchedulesByMovie(Optional<Movie> movie) {
        return movieScheduleRepository.findMovieSchedulesByMovie(movie);
    }


//    @Override
//    public List<MovieSchedule> findMovieSchedulesByTimeSlot(String timeSlot) {
//        return movieScheduleRepository.findMovieSchedulesByTimeSlot(timeSlot);
//    }

    @Override
    public List<MovieSchedule> findMovieSchedulesByRoom(Room room) {
        return movieScheduleRepository.findMovieSchedulesByRoom(room);
    }

    @Override
    public List<MovieSchedule> findByScreeningDayAndRoom(Date screeningDay, Room room) {
        return movieScheduleRepository.findByScreeningDayAndRoom(screeningDay,room);
    }

//    @Override
//    public List<MovieSchedule> findMovieSchedulesByEmptySeat(String emptySeat) {
//        return movieScheduleRepository.findMovieSchedulesByEmptySeat(emptySeat);
//    }
}
