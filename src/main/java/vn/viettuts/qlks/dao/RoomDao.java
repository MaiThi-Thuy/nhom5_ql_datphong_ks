package vn.viettuts.qlks.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.viettuts.qlks.entity.Room;
import vn.viettuts.qlks.entity.RoomJSON;
import vn.viettuts.qlks.utils.FileUtils;
import vn.viettuts.qlks.dao.QLRoom;

/**
 * RoomDao class
 * 
 * @author viettuts.vn
 */
public class RoomDao {
    private final static String ROOM_FILE = "room.json";
    private List<Room> listRooms;
    private QLRoom qlRoom;
    public RoomDao() {
        this.listRooms = readListRooms();
        if (listRooms == null) {
            listRooms = new ArrayList<Room>();
        }
        this.qlRoom=new QLRoom(this.listRooms);
    }

    /**
     * Lưu các đối tượng room vào file room.json
     * 
     * @param rooms
     */
    public void writeListRooms(List<Room> rooms) {
        RoomJSON roomXML = new RoomJSON();
        roomXML.setRoom(rooms);
        FileUtils.writeJSONtoFile(ROOM_FILE, roomXML); // updated to JSON
    }



    /**
     * Đọc các đối tượng room từ file room.json
     * 
     * @return list room
     */
    public List<Room> readListRooms() {
        List<Room> list = new ArrayList<Room>();
        RoomJSON roomXML = FileUtils.readJSONFile(ROOM_FILE, RoomJSON.class); // updated to JSON
        if (roomXML != null) {
            list = roomXML.getRoom();
        }
        else System.out.println("not found");
        return list;
    }

    /**
     * thêm room vào listRooms và lưu listRooms vào file
     * 
     * @param room
     */

    public void add(Room room) {
        int id = 1;
        if (listRooms != null && listRooms.size() > 0) {
            id = listRooms.size() + 1;
        }
        room.setId(id);
        listRooms.add(room);
        writeListRooms(listRooms);

    } 
    public double roomPrice(List<Room> ID_room){
        int price=0;
        double s=0;
        for(Room r:ID_room){
            s+=r.getPrice();
        }
        return s;
    }
    /**
     * cập nhật room vào listRooms và lưu listRooms vào file
     * 
     * @param room
     */
    public void edit(Room room) {
        int size = listRooms.size();
        for (int i = 0; i < size; i++) {
            if (listRooms.get(i).getId() == room.getId()) {
                listRooms.get(i).setPrice(room.getPrice());
                listRooms.get(i).setType(room.getType());
                listRooms.get(i).setStatus(room.isStatus());
                writeListRooms(listRooms);
                break;
            }
        }
    }
    
    public QLRoom getQLRoom(){
        return this.qlRoom;
    }
    /**
     * xóa room từ listRooms và lưu listRooms vào file
     * 
     * @param room
     */
    public boolean delete(Room room) {
        boolean isFound = false;
        int size = listRooms.size();
        for (int i = 0; i < size; i++) {
            if (listRooms.get(i).getId() == room.getId()) {
                room = listRooms.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listRooms.remove(room);
            writeListRooms(listRooms);
            return true;
        }
        return false;
    }


    /**
     * sắp xếp danh sách room theo Price theo tứ tự tăng dần
     */
    public void sortRoomByPrice() {
        Collections.sort(listRooms, new Comparator<Room>() {
            public int compare(Room room1, Room room2) {
                return Double.compare(room1.getPrice(), room2.getPrice());
            }
        });
    }

    public List<Room> getListRooms() {
        return listRooms;
    }

    public void setListRooms(List<Room> listRooms) {
        this.listRooms = listRooms;
    }
    public Room getRoom(String id){
        for (Room room : listRooms) {
            if (room.getId() == Integer.parseInt(id)) {
                return room;
            }
        }
        return null;
    }
}
