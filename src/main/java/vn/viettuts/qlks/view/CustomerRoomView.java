package vn.viettuts.qlks.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import vn.viettuts.qlks.entity.Room;

public class CustomerRoomView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JScrollPane jScrollPaneRoomTable;
    private JScrollPane jScrollPaneAddress;
    private JTable roomTable;
    
    // định nghĩa các cột của bảng room
    private String [] columnNames = new String [] {
            "ID", "Type", "Price", "Status"};
    // định nghĩa dữ liệu mặc định của bẳng room là rỗng
    private Object data = new Object [][] {};
    
    public CustomerRoomView() {
        initComponents();
    }

    private void initComponents() {
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng

        // khởi tạo bảng room
        jScrollPaneRoomTable = new JScrollPane();
        roomTable = new JTable();
        
        // khởi tạo các label

        
        // khởi tạo các trường nhập dữ liệu cho room
        
        // cài đặt các cột và data cho bảng room
        roomTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneRoomTable.setViewportView(roomTable);
        jScrollPaneRoomTable.setPreferredSize(new Dimension (480, 300));
        
         // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Room
        JPanel panel = new JPanel();
        panel.setSize(540, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneRoomTable);
        
        layout.putConstraint(SpringLayout.WEST, jScrollPaneRoomTable, 20, SpringLayout.WEST, panel);
        
        
        
        this.add(panel);
        this.pack();
        this.setTitle("Room Information");
        this.setSize(540, 420);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    /**
     * hiển thị list room vào bảng roomTable
     * 
     * @param list
     */
    public void showListRooms(List<Room> list) {
        int size = list.size();
        Object [][] rooms = new Object[size][4];
        for (int i = 0; i < size; i++) {
            rooms[i][0] = list.get(i).getId();
            rooms[i][1] = list.get(i).getType();
            rooms[i][2] = list.get(i).getPrice();
            rooms[i][3] = list.get(i).isStatus();
        }
        roomTable.setModel(new DefaultTableModel(rooms, columnNames));
    }
    
    
    public void actionPerformed(ActionEvent e) {
    }
    
    public void valueChanged(ListSelectionEvent e) {
    }
    
    
    public void addListRoomSelectionListener(ListSelectionListener listener) {
        roomTable.getSelectionModel().addListSelectionListener(listener);
    }
}
