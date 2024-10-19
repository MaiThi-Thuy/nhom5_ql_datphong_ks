package vn.viettuts.qlks.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.*;
import vn.viettuts.qlks.dao.CustomerDao;
import vn.viettuts.qlks.dao.RoomDao;
import vn.viettuts.qlks.dao.UserDao;
import vn.viettuts.qlks.entity.User;
import vn.viettuts.qlks.view.CustomerRoomView;
import vn.viettuts.qlks.view.CustomerView;
import vn.viettuts.qlks.entity.Customer;
import vn.viettuts.qlks.entity.Room;

public class CustomerRoomController {
    
    private CustomerRoomView crView;
    private RoomDao roomDao;
    private CustomerDao customerDao;
    private Customer customer;
    private CustomerView customerView;
    public CustomerRoomController(CustomerRoomView view, RoomDao roomDao, CustomerDao customerDao, CustomerView customerView) {
        this.crView = view;
        this.roomDao = roomDao;
        this.customerDao = customerDao;
        this.customerView = customerView;

        crView.addListRoomSelectionListener(new ListRoomSelectionListener());
        crView.addHuyPhongListener(new HuyPhongListener());
    }   
    
    
    public void showCustomerRoomView(Customer customer) {
        this.customer = customer;
        crView.setTitle("Room of "+ customer.getName());
        crView.showListRooms(roomDao.showListRooms(customer.getID_room()));
        crView.setVisible(true);
    }

    class ListRoomSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            crView.enableHuyPhongBut();
        }
    }
    class HuyPhongListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int RoomId= crView.getSelectedRoomID();
            Room room = roomDao.readListRooms().get(RoomId-1);
            room.setStatus(true);
            roomDao.edit(room);
            List<String> roomIds=customer.getID_room();
            System.out.println(roomIds);
            roomIds.remove(String.valueOf(RoomId-1));
            System.out.println(roomIds);
            customer.setID_room(roomIds);
            customerDao.edit(customer);
            customerView.addRooms(roomDao.getQLRoom().searchRooms(customerView.getRoomType()));
            customerView.showListCustomers(customerDao.getListCustomers(),roomDao);
            customerView.showMessage("Hủy phòng thành công!");
            crView.showListRooms(roomDao.showListRooms(customer.getID_room()));
            
        }
    }
    
}
