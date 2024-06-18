package vn.codegym.dto;

import vn.codegym.entity.Movie;
import vn.codegym.entity.Room;
import vn.codegym.entity.Ticket;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

public class MovieScheduleDto {
    private Long id;
    private Time timeStart;
    private Time timeStop;
    private Date screeningDay;
    private int emptySeat;
    private Room room;
    private Movie movie;
    private List<Ticket> ticketList;

    public MovieScheduleDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    public Time getTimeStop() {
        return timeStop;
    }

    public void setTimeStop(Time timeStop) {
        this.timeStop = timeStop;
    }

    public Date getScreeningDay() {
        return screeningDay;
    }

    public void setScreeningDay(Date screeningDay) {
        this.screeningDay = screeningDay;
    }

    public int getEmptySeat() {
        return emptySeat;
    }

    public void setEmptySeat(int emptySeat) {
        this.emptySeat = emptySeat;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieScheduleDto that = (MovieScheduleDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
