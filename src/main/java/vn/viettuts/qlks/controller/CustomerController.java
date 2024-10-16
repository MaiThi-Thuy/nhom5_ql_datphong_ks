package vn.viettuts.qlks.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JTable;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vn.viettuts.qlks.controller.CustomerController.AddCustomerListener;
import vn.viettuts.qlks.controller.CustomerController.ClearCustomerListener;
import vn.viettuts.qlks.controller.CustomerController.DeleteCustomerListener;
import vn.viettuts.qlks.controller.CustomerController.EditCustomerListener;
import vn.viettuts.qlks.controller.CustomerController.ListCustomerSelectionListener;
import vn.viettuts.qlks.controller.CustomerController.NavigateRoomListener;
import vn.viettuts.qlks.controller.CustomerController.RoomTypesListener;
import vn.viettuts.qlks.controller.CustomerController.SortCustomerNameListener;
import vn.viettuts.qlks.dao.CustomerDao;
import vn.viettuts.qlks.dao.RoomDao;
import vn.viettuts.qlks.entity.Customer;
import vn.viettuts.qlks.entity.Room;
import vn.viettuts.qlks.view.CustomerView;
import vn.viettuts.qlks.view.RoomView;
public class CustomerController {
    //
    private CustomerDao customerDao;
    private CustomerView customerView;
    private RoomDao roomDao;
    public CustomerController(CustomerView view) {
        this.customerView = view;
        customerDao = new CustomerDao();
        roomDao= new RoomDao();
        view.addAddCustomerListener(new AddCustomerListener());
        view.addEdiCustomerListener(new EditCustomerListener());
        view.addDeleteCustomerListener(new DeleteCustomerListener());
        view.addClearListener(new ClearCustomerListener());
        //view.addSortCustomerCCCDListener(new SortCustomerCCCDListener());
        view.addSortCustomerNameListener(new SortCustomerNameListener());
        view.addListCustomerSelectionListener(new ListCustomerSelectionListener());
        view.addNavigateToRoomViewListener( new NavigateRoomListener());
        view.addRoomtypesListener(new RoomTypesListener());// RoomTypes listener
        view.addClickRoomListener(new ClickRoomListener());


    }

    public void showCustomerView() {
        List<Customer> customerList = customerDao.getListCustomers();
        customerView.setVisible(true);
        customerView.showListCustomers(customerList);
        customerView.addTypesRoom(roomDao.getQLRoom().showTypes());
    }

    /**
     * Lớp AddCustomerListener 
     * chứa cài đặt cho sự kiện click button "Add"
     * 
     * @author viettuts.vn
     */
    class AddCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getCustomerInfo();
            if (customer != null) {
                customerDao.add(customer);
                customerView.showCustomer(customer);
                customerView.showListCustomers(customerDao.getListCustomers());
                customerView.showMessage("Thêm thành công!");
            }
        }
    }

    /**
     * Lớp EditCustomerListener 
     * chứa cài đặt cho sự kiện click button "Edit"
     * 
     * @author viettuts.vn
     */
    class EditCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getCustomerInfo();
            if (customer != null) {
                customerDao.edit(customer);
                customerView.showCustomer(customer);
                customerView.showListCustomers(customerDao.getListCustomers());
                customerView.showMessage("Cập nhật thành công!");
            }
        }
    }

    /**
     * Lớp DeleteCustomerListener 
     * chứa cài đặt cho sự kiện click button "Delete"
     * 
     * @author viettuts.vn
     */
    class DeleteCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getCustomerInfo();
            if (customer != null) {
                customerDao.delete(customer);
                customerView.clearCustomerInfo();
                customerView.showListCustomers(customerDao.getListCustomers());
                customerView.showMessage("Xóa thành công!");
            }
        }
    }

    /**
     * Lớp ClearCustomerListener 
     * chứa cài đặt cho sự kiện click button "Clear"
     * 
     * @author viettuts.vn
     */
    class ClearCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerView.clearCustomerInfo();
        }
    }

    /**
     * Lớp SortCustomerCCCDListener 
     * chứa cài đặt cho sự kiện click button "Sort By CCCD"
     * 
     * @author viettuts.vn
     */
//    class SortCustomerCCCDListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            customerDao.sortCustomerByCCCD();
//            customerView.showListCustomers(customerDao.getListCustomers());
//        }
//    }

    /**
     * Lớp SortCustomerCCCDListener 
     * chứa cài đặt cho sự kiện click button "Sort By Name"
     * 
     * @author viettuts.vn
     */
    class SortCustomerNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerDao.sortCustomerByName();
            customerView.showListCustomers(customerDao.getListCustomers());
        }
    }
    class NavigateRoomListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            RoomView roomView = new RoomView();
            RoomController roomController = new RoomController(roomView);
            roomController.showRoomView();
            //roomController.showSelectionRoomView();
            customerView.setVisible(false);
        }
    }

    //RoomTypes listener
    class RoomTypesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //customerView.showMessage("RoomTypes");
            customerView.addRooms(roomDao.getQLRoom().searchRooms(customerView.getRoomType()));
        }
    }
    
    /**
     * Lớp ListCustomerSelectionListener 
     * chứa cài đặt cho sự kiện chọn customer trong bảng customer
     * 
     * @author viettuts.vn
     */
    class ListCustomerSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            customerView.fillCustomerFromSelectedRow();
        }
    }


    //ClickRoomListener
    class ClickRoomListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
             JTable table=customerView.getCustomerTable();
            int row=table.getSelectedRow();
            int col=table.getSelectedColumn();
            Customer customer=customerDao.getListCustomers().get(row);
            if(col==8)customerView.showMessage("Customer: "+customer.getName());
        }
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }

    }
}
