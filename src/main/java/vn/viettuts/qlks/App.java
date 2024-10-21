package vn.viettuts.qlks;

import java.awt.EventQueue;

import vn.viettuts.qlks.controller.LoginController;
import vn.viettuts.qlks.view.LoginView;
import vn.viettuts.qlks.view.CustomerView;
import vn.viettuts.qlks.controller.CustomerController;
import vn.viettuts.qlks.view.RoomView;
import vn.viettuts.qlks.controller.RoomController;
import vn.viettuts.qlks.view.CustomerRoomView;
import vn.viettuts.qlks.controller.CustomerRoomController;
public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            // hiển thị màn hình login
//            LoginView view = new LoginView();
//            LoginController controller = new LoginController(view);
//            controller.showLoginView();
            
            CustomerView customerView = new CustomerView();
            CustomerController customerController = new CustomerController(customerView);
            customerController.showCustomerView();

        });
    }
}