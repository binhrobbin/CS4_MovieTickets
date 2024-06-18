package vn.codegym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.entity.Movie;
import vn.codegym.entity.MovieSchedule;
import vn.codegym.entity.Room;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface IMovieScheduleRepository extends JpaRepository<MovieSchedule, Long> {
    List<MovieSchedule> findMovieSchedulesByMovie(Optional<Movie> movie);
//    List<MovieSchedule> findMovieSchedulesByTimeSlot(String timeSlot);
    List<MovieSchedule> findByScreeningDayAndRoom(Date screeningDay, Room room);

    List<MovieSchedule> findMovieSchedulesByRoom(Room room);
//    List<MovieSchedule> findMovieSchedulesByEmptySeat(String emptySeat);
//    @Query(value = "select * from Movie where name = :keyword", nativeQuery = true)
//    List<Movie> searchByName(@Param("keyword") String name);
}
