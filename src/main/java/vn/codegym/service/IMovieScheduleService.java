package vn.codegym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.dto.MovieScheduleDto;
import vn.codegym.entity.Movie;
import vn.codegym.entity.MovieSchedule;
import vn.codegym.entity.Room;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface IMovieScheduleService {
    List<MovieSchedule> findAll();
    Page<MovieSchedule> findAll(Pageable pageable);
    void save(MovieSchedule movieSchedule);

    void update(long id,MovieSchedule movieSchedule);
    void update(MovieSchedule movieSchedule);
    void remove(long id);
    Optional<MovieSchedule> findById(long id);
    List<MovieSchedule> findMovieSchedulesByMovie(Optional<Movie> movie);
//    List<MovieSchedule> findMovieSchedulesByTimeSlot(String timeSlot);
    List<MovieSchedule> findMovieSchedulesByRoom(Room room);
//    List<MovieSchedule> findMovieSchedulesByEmptySeat(String emptySeat);

    List<MovieSchedule> findByScreeningDayAndRoom(Date screeningDay, Room room);

}
