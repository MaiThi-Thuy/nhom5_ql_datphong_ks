package vn.viettuts.qlks;

import java.awt.EventQueue;

import vn.viettuts.qlks.controller.LoginController;
import vn.viettuts.qlks.view.LoginView;
import vn.viettuts.qlks.view.CustomerView;
import vn.viettuts.qlks.controller.CustomerController;
import vn.viettuts.qlks.view.RoomView;
import vn.viettuts.qlks.controller.RoomController;
public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginView view = new LoginView();
            LoginController controller = new LoginController(view);
            // hiển thị màn hình login
            controller.showLoginView();
            
//            CustomerView customerView = new CustomerView();
//            CustomerController customerController = new CustomerController(customerView);
//            customerController.showCustomerView();
        });
    }
}