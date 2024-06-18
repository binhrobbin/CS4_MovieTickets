package vn.codegym.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
public class MovieSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String timeSlot;
    private Time timeStart;
    private Time timeStop;
    private Date screeningDay;
    private int emptySeat;
    @ManyToOne
//    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @ManyToOne
//    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
//    @OneToMany(mappedBy = "movieSchedule")
//    private List<Ticket> ticketList;

    public MovieSchedule() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

//    public String getTimeSlot() {
//        return timeSlot;
//    }
//
//    public void setTimeSlot(String timeSlot) {
//        this.timeSlot = timeSlot;
//    }


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

//    public List<Ticket> getTicketList() {
//        return ticketList;
//    }
//
//    public void setTicketList(List<Ticket> ticketList) {
//        this.ticketList = ticketList;
//    }

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
}
