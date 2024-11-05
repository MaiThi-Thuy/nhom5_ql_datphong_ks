package vn.viettuts.qlks.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import vn.viettuts.qlks.controller.CustomerRoomController.HuyPhongListener;
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
        crView.showListRooms(customer.getID_room());
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
            Room room = customer.getID_room().get(RoomId-1);
            room.setStatus(true);
            customer.getID_room().remove(room);
            List<Room> roomIds=customer.getID_room();
            roomDao.edit(room);
            customer.setID_room(roomIds);
            customer.setTotalPrice(roomDao.roomPrice(customer.getID_room())*customerView.calculateDaysBetween(customer.getCheckIn(),customer.getCheckOut()));
            customerDao.edit(customer);

            customerView.addRooms(roomDao.getQLRoom().searchRooms(customerView.getRoomType()));
            customerView.showListCustomers(customerDao.getListCustomers(),roomDao);
            customerView.showMessage("Hủy phòng thành công!");
            crView.showListRooms(customer.getID_room());
            
        }
    }
    
}
