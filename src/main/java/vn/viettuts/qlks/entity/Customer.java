package vn.viettuts.qlks.entity;

import java.io.Serializable;
import java.util.*;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private byte age;
    private String address;
    private String cccd;
    private String sdt;
    private String checkIn;
    private String checkOut;
    private List<Integer> ID_room; // Changed to ArrayList<Integer>

    public Customer() {
    }

    public Customer(int id, String name, byte age, String address, String cccd, String sdt, String checkIn, String checkOut, List<Integer> ID_room) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.cccd = cccd;
        this.sdt = sdt;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ID_room = ID_room; // Initialize ID_room
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

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public List<Integer> getID_room() {
        return ID_room;
    }

    public void setID_room(List<Integer> ID_room) {
        this.ID_room = ID_room;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", age=" + age +
               ", address=" + address + ", cccd=" + cccd + ", sdt=" + sdt +
               ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", ID_room=" + ID_room + "]"; 
    }
}
