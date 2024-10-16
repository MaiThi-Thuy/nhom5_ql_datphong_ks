/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.viettuts.qlks.dao;
import vn.viettuts.qlks.entity.Room;
import java.util.*;

/**
 *
 * @author hieu7
 */
public class QLRoom {
    private TreeMap<String,ArrayList<Room>> qlLoai;
    public QLRoom(){
        this.qlLoai=new TreeMap<>();
    }
    public void addRooms(ArrayList<Room> rooms){
        for (Room room : rooms) {
            ArrayList<Room> ls=new ArrayList<>();
            if(this.qlLoai.get(room.getType())==null){
                ls.add(room);
                this.qlLoai.put(room.getType(), ls);
            }
            else{
                this.qlLoai.get(room.getType()).add(room);
            }
        }
    }
    public ArrayList<Room> searchRooms(String loai){
        ArrayList<Room> arr= new ArrayList<>();
        for(Room r:this.qlLoai.get(loai)){
            if(r.isStatus())arr.add(r);
        }
        return arr;
    }
    
}
