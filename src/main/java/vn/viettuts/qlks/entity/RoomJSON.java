package vn.viettuts.qlks.entity;

import java.util.List;

public class RoomJSON {
    
    private List<Room> room;

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "RoomXML [rooms=" + room + "]";
    }
}
