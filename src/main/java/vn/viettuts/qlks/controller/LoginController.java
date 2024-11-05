package vn.viettuts.qlks.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import vn.viettuts.qlks.dao.UserDao;
import vn.viettuts.qlks.entity.User;
import vn.viettuts.qlks.view.LoginView;
import vn.viettuts.qlks.view.CustomerView;

public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private CustomerView customerView;
    
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     * 
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            String pass=user.getPassword();
            try {
                user.setPassword(userDao.encryptPassword(pass, pass));
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (userDao.checkUser(user)) {
                user.setRole(userDao.getUserRole(user));
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                loginView.showMessage("Đăng nhập thành công.");
                customerView = new CustomerView();
                CustomerController customerController = new CustomerController(customerView,user);
                customerController.showCustomerView();
                loginView.setVisible(false);
                
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }
}
