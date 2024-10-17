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
    private TreeMap<String,List<Room>> qlLoai;
    public QLRoom(List<Room> rooms){
        this.qlLoai=new TreeMap<>();
        addRooms(rooms);
    }
    public void addRooms(List<Room> rooms){
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
    public void deleteRoom(int id){
        for(Map.Entry<String,List<Room>> entry:this.qlLoai.entrySet()){
            for(Room r:entry.getValue()){
                if(r.getId()==id){
                    r.setStatus(false);
                    return;
                }
            }
        }
    }
    public ArrayList<String>showTypes(){
        ArrayList<String> arr=new ArrayList<>();
        for(Map.Entry<String,List<Room>> entry:this.qlLoai.entrySet()){
            arr.add(entry.getKey());
        }
        return arr;
    }
    public ArrayList<String> searchRooms(String loai){
        ArrayList<String> arr= new ArrayList<>();
        for(Room r:this.qlLoai.get(loai)){
            if(r.isStatus())arr.add(r.toString());
        }
        return arr;
    }
    
}
