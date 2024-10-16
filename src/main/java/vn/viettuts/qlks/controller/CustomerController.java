package vn.viettuts.qlks.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import vn.viettuts.qlks.dao.CustomerDao;
import vn.viettuts.qlks.entity.Customer;
import vn.viettuts.qlks.view.CustomerView;
import vn.viettuts.qlks.view.RoomView;
public class CustomerController {
    private CustomerDao customerDao;
    private CustomerView customerView;

    public CustomerController(CustomerView view) {
        this.customerView = view;
        customerDao = new CustomerDao();

        view.addAddCustomerListener(new AddCustomerListener());
        view.addEdiCustomerListener(new EditCustomerListener());
        view.addDeleteCustomerListener(new DeleteCustomerListener());
        view.addClearListener(new ClearCustomerListener());
        //view.addSortCustomerCCCDListener(new SortCustomerCCCDListener());
        view.addSortCustomerNameListener(new SortCustomerNameListener());
        view.addListCustomerSelectionListener(new ListCustomerSelectionListener());
        view.addNavigateToRoomViewListener( new NavigateRoomListener());
    }

    public void showCustomerView() {
        List<Customer> customerList = customerDao.getListCustomers();
        customerView.setVisible(true);
        customerView.showListCustomers(customerList);
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
        @Override
        public void actionPerformed(ActionEvent e) {
            RoomView roomView = new RoomView();
            RoomController roomController = new RoomController(roomView);
            roomController.showRoomView();
            //roomController.showSelectionRoomView();
            customerView.setVisible(false);
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
}
