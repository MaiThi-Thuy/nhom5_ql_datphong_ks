package vn.viettuts.qlsv.entity;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private byte age;
    private String address;
    private String cccd; // điểm trung bình của sinh viên

    public Customer() {
    }

    public Customer(int id, String name, byte age, String address, String cccd) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.cccd = cccd;
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

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", age=" + age +
               ", address=" + address + ", cccd=" + cccd + "]";
    }
}
