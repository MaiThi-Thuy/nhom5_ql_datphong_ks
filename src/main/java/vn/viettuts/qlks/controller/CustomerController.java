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
import vn.viettuts.qlks.controller.CustomerController.AddRoom2CustomerListener;
import vn.viettuts.qlks.controller.CustomerController.ClearCustomerListener;
import vn.viettuts.qlks.controller.CustomerController.ClickRoomListener;
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
import vn.viettuts.qlks.entity.User;
import vn.viettuts.qlks.view.CustomerRoomView;
import vn.viettuts.qlks.view.CustomerView;
import vn.viettuts.qlks.view.LoginView;
import vn.viettuts.qlks.view.RoomView;
import vn.viettuts.qlks.view.StatsView;
public class CustomerController {
    //
    private CustomerDao customerDao;
    private CustomerView customerView;
    private RoomDao roomDao;
    private User curUser;
    public CustomerController(CustomerView view,User curUSer) {
        this.customerView = view;
        this.curUser=curUSer;
        customerDao = new CustomerDao();
        roomDao= new RoomDao();
        view.addAddCustomerListener(new AddCustomerListener());
        view.addEdiCustomerListener(new EditCustomerListener());
        view.addDeleteCustomerListener(new DeleteCustomerListener());
        view.addClearListener(new ClearCustomerListener());
        view.addSortCustomerPriceListener(new SortCustomerPriceListener());
        view.addSortCustomerNameListener(new SortCustomerNameListener());
        view.addListCustomerSelectionListener(new ListCustomerSelectionListener());
        view.addNavigateToRoomViewListener( new NavigateRoomListener());
        view.addRoomtypesListener(new RoomTypesListener());// RoomTypes listener
        view.addClickRoomListener(new ClickRoomListener()); //ClickRoomListener
        view.addAddRoomListener(new AddRoom2CustomerListener());
        view.addSearchListener(new SearchCustomer());
        view.addThongKeListener(new ThongkeView());
        view.addLogoutListener(new LogoutListener());
    }
    public User get_Cuser(){
        return curUser;
    }
    public void showCustomerView() {
        List<Customer> customerList = customerDao.getListCustomers();
        customerView.setVisible(true);
        
            customerView.showListCustomers(customerList,roomDao);
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
                customerView.showListCustomers(customerDao.getListCustomers(),roomDao);
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
            int cid=customerView.getSelectedCustomerID();
            Customer tmpCustomer = customerDao.searchCustomer(cid);
            Customer customer = customerView.getCustomerInfo();
            customer.setID_room(tmpCustomer.getID_room());
            if (customer != null) {
                customer.setTotalPrice(roomDao.roomPrice(customer.getID_room())*customerView.calculateDaysBetween(customer.getCheckIn(),customer.getCheckOut()));
                System.out.println(customer.getTotalPrice());
                customerDao.edit(customer);
                customerView.showCustomer(customer);
                
                customerView.showListCustomers(customerDao.getListCustomers(),roomDao);
                
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
                customerView.showListCustomers(customerDao.getListCustomers(),roomDao);
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
   class SortCustomerPriceListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           customerDao.sortCustomerByPrice();
               customerView.showListCustomers(customerDao.getListCustomers(),roomDao);
       }
   }

    /**
     * Lớp SortCustomerCCCDListener 
     * chứa cài đặt cho sự kiện click button "Sort By Name"
     * 
     * @author viettuts.vn
     */
    class SortCustomerNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerDao.sortCustomerByName();
            customerView.showListCustomers(customerDao.getListCustomers(),roomDao);
        }
    }
    class NavigateRoomListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(curUser.getRole().equals("admin")){
                RoomView roomView = new RoomView();
                RoomController roomController = new RoomController(roomView,curUser);
                roomController.showRoomView();
                customerView.setVisible(false);
            }
            else{
                customerView.showMessage("Bạn không có quyền truy cập");
            }
        }
    }

    //RoomTypes listener
    class RoomTypesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
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
            int col=table.getSelectedColumn();
            int Cid=table.getSelectedRow();
            Customer customer=customerDao.getListCustomers().get(Cid);
            if(col==8){
                CustomerRoomView CRView= new CustomerRoomView();
                CustomerRoomController CRController=new CustomerRoomController(CRView,roomDao,customerDao,customerView);
                CRController.showCustomerRoomView(customer);

            }
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
    //add room to customer
    class AddRoom2CustomerListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
                String RoomId = customerView.getRoom();
                int RoomId_t=Integer.parseInt(RoomId);
                Room room = roomDao.readListRooms().get(RoomId_t-1);
                int cID= customerView.getSelectedCustomerID();
                Customer customer =customerDao.searchCustomer(cID);
                //customerView.showMessage(RoomId);
            if (customer != null) {
                customer.getID_room().add(room);
                
                room.setStatus(false);
                roomDao.edit(room);
                customer.setTotalPrice(roomDao.roomPrice(customer.getID_room())*customerView.calculateDaysBetween(customer.getCheckIn(),customer.getCheckOut()));
                System.out.println(customer.getTotalPrice());
                customerDao.edit(customer);  
                customerView.addRooms(roomDao.getQLRoom().searchRooms(customerView.getRoomType()));
                customerView.showListCustomers(customerDao.getListCustomers(),roomDao);
                customerView.showMessage("Đặt phòng thành công!");
            }
        }
    }
    class SearchCustomer implements ActionListener{
        public void actionPerformed(ActionEvent e){
            customerView.showListCustomers(customerDao.searchCustomer(customerView.getKeyWord()),roomDao);
        }
    }
    class ThongkeView implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(curUser.getRole().equals("admin")){
                StatsView statsView = new StatsView(curUser);
                statsView.setVisible(true);
                customerView.setVisible(false);
            }
            else{
                customerView.showMessage("Bạn không có quyền truy cập");
            }
        }
    }
    class LogoutListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            customerView.showMessage("Đăng xuất thành công");
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            loginController.showLoginView();
            customerView.setVisible(false);
        }
    }
}
