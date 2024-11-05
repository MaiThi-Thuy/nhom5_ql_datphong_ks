package vn.viettuts.qlks.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vn.viettuts.qlks.dao.RoomDao;
import vn.viettuts.qlks.entity.Room;
import vn.viettuts.qlks.entity.User;
import vn.viettuts.qlks.view.CustomerView;
import vn.viettuts.qlks.view.RoomView;

public class RoomController {
    private RoomDao roomDao;
    private RoomView roomView;
    private User curUser;
    public RoomController(RoomView view,User curUser) {
        this.curUser=curUser;
        this.roomView = view;
        roomDao = new RoomDao();

        view.addAddRoomListener(new AddRoomListener());
        view.addEdiRoomListener(new EditRoomListener());
        view.addDeleteRoomListener(new DeleteRoomListener());
        view.addClearListener(new ClearRoomListener());
        view.addSortRoomPriceListener(new SortRoomPriceListener());
        view.addListRoomSelectionListener(new ListRoomSelectionListener());
        view.addNavigateToCustomerViewListener( new NavigateCustomerListener());
    }

    public void showRoomView() {
        List<Room> roomList = roomDao.getListRooms();
        //roomView.showSelectRooms()
        roomView.setVisible(true);
        roomView.showListRooms(roomList);
    }

    /**
     * Lớp AddRoomListener 
     * chứa cài đặt cho sự kiện click button "Add"
     * 
     * @author viettuts.vn
     */
    class AddRoomListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Room room = roomView.getRoomInfo();
            if (room != null) {
                roomDao.add(room);
                roomView.showRoom(room);
                roomView.showListRooms(roomDao.getListRooms());
                roomView.showMessage("Thêm thành công!");
            }
        }
    }

    /**
     * Lớp EditRoomListener 
     * chứa cài đặt cho sự kiện click button "Edit"
     * 
     * @author viettuts.vn
     */
    class EditRoomListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Room room = roomView.getRoomInfo();
            if (room != null) {
                roomDao.edit(room);
                roomView.showRoom(room);
                roomView.showListRooms(roomDao.getListRooms());
                roomView.showMessage("Cập nhật thành công!");
            }
        }
    }

    /**
     * Lớp DeleteRoomListener 
     * chứa cài đặt cho sự kiện click button "Delete"
     * 
     * @author viettuts.vn
     */
    class DeleteRoomListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Room room = roomView.getRoomInfo();
            if (room != null) {
                roomDao.delete(room);
                roomView.clearRoomInfo();
                roomView.showListRooms(roomDao.getListRooms());
                roomView.showMessage("Xóa thành công!");
            }
        }
    }

    /**
     * Lớp ClearRoomListener 
     * chứa cài đặt cho sự kiện click button "Clear"
     * 
     * @author viettuts.vn
     */
    class ClearRoomListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            roomView.clearRoomInfo();
        }
    }

    /**
     * Lớp SortRoomCCCDListener 
     * chứa cài đặt cho sự kiện click button "Sort By CCCD"
     * 
     * @author viettuts.vn
     */
   class SortRoomPriceListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           roomDao.sortRoomByPrice();
           roomView.showListRooms(roomDao.getListRooms());
       }
   }


    /**
     * Lớp ListRoomSelectionListener 
     * chứa cài đặt cho sự kiện chọn room trong bảng room
     * 
     * @author viettuts.vn
     */
    class ListRoomSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            roomView.fillRoomFromSelectedRow();
        }
    }
    class NavigateCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CustomerView customerView = new CustomerView();
                CustomerController customerController = new CustomerController(customerView,curUser);
                customerController.showCustomerView();
            roomView.setVisible(false);
        }
    }
}
