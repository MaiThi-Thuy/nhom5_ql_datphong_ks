package vn.viettuts.qlks.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vn.viettuts.qlks.dao.RoomDao;
import vn.viettuts.qlks.dao.UserDao;
import vn.viettuts.qlks.entity.User;
import vn.viettuts.qlks.view.CustomerRoomView;
import vn.viettuts.qlks.view.CustomerView;
import vn.viettuts.qlks.entity.Customer;

public class CustomerRoomController {
    
    private CustomerRoomView crView;
    
    public CustomerRoomController(CustomerRoomView view) {
        this.crView = view;
        
    }
    
    public void showCustomerRoomView(RoomDao roomDao,Customer customer) {
        crView.showListRooms(roomDao.showListRooms(customer.getID_room()));
        crView.setVisible(true);
    }
    
}
