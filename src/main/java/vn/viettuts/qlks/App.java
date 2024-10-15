package vn.viettuts.qlks;

import java.awt.EventQueue;

import vn.viettuts.qlks.controller.LoginController;
import vn.viettuts.qlsv.view.LoginView;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginView view = new LoginView();
            LoginController controller = new LoginController(view);
            // hiển thị màn hình login
            controller.showLoginView();
        });
    }
}