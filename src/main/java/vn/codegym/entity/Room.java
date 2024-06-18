package vn.codegym.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameRoom;
    private int quantitySeat;
    private String typeRoom;
    private long priceRoom;
//    @OneToMany(mappedBy = "room")
//    private List<MovieSchedule> movieScheduleList;

    public Room() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public int getQuantitySeat() {
        return quantitySeat;
    }

    public void setQuantitySeat(int quantitySeat) {
        this.quantitySeat = quantitySeat;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    public long getPriceRoom() {
        return priceRoom;
    }

    public void setPriceRoom(long priceRoom) {
        this.priceRoom = priceRoom;
    }

//    public List<MovieSchedule> getMovieScheduleList() {
//        return movieScheduleList;
//    }
//
//    public void setMovieScheduleList(List<MovieSchedule> movieScheduleList) {
//        this.movieScheduleList = movieScheduleList;
//    }
}
