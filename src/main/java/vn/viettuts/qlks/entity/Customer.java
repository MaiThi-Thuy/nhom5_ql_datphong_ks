package vn.viettuts.qlks.entity;

import java.io.Serializable;
import vn.viettuts.qlks.entity.Room;
import java.util.*;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private byte age;
    private String address;
    private String cccd;
    private String sdt;
    private Date checkIn;
    private Date checkOut;
    private List<Room> RoomS; 
    private double totalPrice;

    public Customer() {
        this.RoomS = new ArrayList<Room>(); // Initialize ID_room
    }

    public Customer(int id, String name, byte age, String address, String cccd, String sdt, Date checkIn, Date checkOut, List<Room> ID_room) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.cccd = cccd;
        this.sdt = sdt;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.RoomS = ID_room; // Initialize ID_room
        this.totalPrice = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public List<Room> getID_room() {
        return RoomS;
    }

    public void setID_room(List<Room> ID_room) {
        this.RoomS = ID_room;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", age=" + age +
               ", address=" + address + ", cccd=" + cccd + ", sdt=" + sdt +
               ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", ID_room=" + RoomS + "]"; 
    }
}
