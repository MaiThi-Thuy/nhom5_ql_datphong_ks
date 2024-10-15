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

public class RoomView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JButton addRoomBtn;
    private JButton editRoomBtn;
    private JButton deleteRoomBtn;
    private JButton clearBtn;
    private JButton sortRoomCCCDBtn;
    private JButton sortRoomNameBtn;
    private JScrollPane jScrollPaneRoomTable;
    private JScrollPane jScrollPaneAddress;
    private JTable roomTable;
    
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;
    private JLabel cccdLabel;
    private JLabel sdtLabel;
    
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea addressTA;
    private JTextField cccdField;
    private JTextField sdtField;
    
    // định nghĩa các cột của bảng room
    private String [] columnNames = new String [] {
            "ID", "Ten", "Tuoi", "DiaChi", "CCCD", "SDT"};
    // định nghĩa dữ liệu mặc định của bẳng room là rỗng
    private Object data = new Object [][] {};
    
    public RoomView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addRoomBtn = new JButton("Add");
        editRoomBtn = new JButton("Edit");
        deleteRoomBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortRoomCCCDBtn = new JButton("Sort By CCCD");
        sortRoomNameBtn = new JButton("Sort By Name");
        // khởi tạo bảng room
        jScrollPaneRoomTable = new JScrollPane();
        roomTable = new JTable();
        
        // khởi tạo các label
        idLabel = new JLabel("Id");
        nameLabel = new JLabel("Ten");
        ageLabel = new JLabel("Tuoi");
        addressLabel = new JLabel("DiaChi");
        cccdLabel = new JLabel("CCCD");
        sdtLabel = new JLabel("SDT");
        
        // khởi tạo các trường nhập dữ liệu cho room
        idField = new JTextField(6);
        idField.setEditable(false);
        nameField = new JTextField(15);
        ageField = new JTextField(6);
        addressTA = new JTextArea();
        addressTA.setColumns(15);
        addressTA.setRows(5);
        jScrollPaneAddress = new JScrollPane();
        jScrollPaneAddress.setViewportView(addressTA);
        cccdField = new JTextField(15);
        sdtField = new JTextField(15);
        
        // cài đặt các cột và data cho bảng room
        roomTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneRoomTable.setViewportView(roomTable);
        jScrollPaneRoomTable.setPreferredSize(new Dimension (480, 300));
        
         // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Room
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneRoomTable);
        
        panel.add(addRoomBtn);
        panel.add(editRoomBtn);
        panel.add(deleteRoomBtn);
        panel.add(clearBtn);
        panel.add(sortRoomCCCDBtn);
        panel.add(sortRoomNameBtn);
        
        panel.add(idLabel);
        panel.add(nameLabel);
        panel.add(ageLabel);
        panel.add(addressLabel);
        panel.add(cccdLabel);
        panel.add(sdtLabel);
        
        panel.add(idField);
        panel.add(nameField);
        panel.add(ageField);
        panel.add(jScrollPaneAddress);
        panel.add(cccdField);
        panel.add(sdtField);
        
        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ageLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, addressLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addressLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, cccdLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cccdLabel, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sdtLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sdtLabel, 230, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ageField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, cccdField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cccdField, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sdtField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sdtField, 230, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, jScrollPaneRoomTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneRoomTable, 10, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addRoomBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addRoomBtn, 270, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editRoomBtn, 60, SpringLayout.WEST, addRoomBtn);
        layout.putConstraint(SpringLayout.NORTH, editRoomBtn, 270, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteRoomBtn, 60, SpringLayout.WEST, editRoomBtn);
        
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 270, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deleteRoomBtn);
        
        layout.putConstraint(SpringLayout.NORTH, deleteRoomBtn, 270, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortRoomCCCDBtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortRoomCCCDBtn, 330, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortRoomNameBtn, 115, SpringLayout.WEST, sortRoomCCCDBtn);
        layout.putConstraint(SpringLayout.NORTH, sortRoomNameBtn, 330, SpringLayout.NORTH, panel);
        
        this.add(panel);
        this.pack();
        this.setTitle("Room Information");
        this.setSize(800, 420);
        // disable Edit and Delete buttons
        editRoomBtn.setEnabled(false);
        deleteRoomBtn.setEnabled(false);
        // enable Add button
        addRoomBtn.setEnabled(true);
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
        // với bảng roomTable có 6 cột, 
        // khởi tạo mảng 2 chiều rooms, trong đó:
        // số hàng: là kích thước của list room 
        // số cột: là 6
        Object [][] rooms = new Object[size][6];
        for (int i = 0; i < size; i++) {
            rooms[i][0] = list.get(i).getId();
            rooms[i][1] = list.get(i).getName();
            rooms[i][2] = list.get(i).getAge();
            rooms[i][3] = list.get(i).getAddress();
            rooms[i][4] = list.get(i).getCccd();
            //rooms[i][5] = list.get(i).getSdt();
        }
        roomTable.setModel(new DefaultTableModel(rooms, columnNames));
    }
    
    /**
     * điền thông tin của hàng được chọn từ bảng room 
     * vào các trường tương ứng của room.
     */
    public void fillRoomFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = roomTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(roomTable.getModel().getValueAt(row, 0).toString());
            nameField.setText(roomTable.getModel().getValueAt(row, 1).toString());
            ageField.setText(roomTable.getModel().getValueAt(row, 2).toString());
            addressTA.setText(roomTable.getModel().getValueAt(row, 3).toString());
            cccdField.setText(roomTable.getModel().getValueAt(row, 4).toString());
            sdtField.setText(roomTable.getModel().getValueAt(row, 5).toString());
            // enable Edit and Delete buttons
            editRoomBtn.setEnabled(true);
            deleteRoomBtn.setEnabled(true);
            // disable Add button
            addRoomBtn.setEnabled(false);
        }
    }

    /**
     * xóa thông tin room
     */
    public void clearRoomInfo() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressTA.setText("");
        cccdField.setText("");
        sdtField.setText("");
        // disable Edit and Delete buttons
        editRoomBtn.setEnabled(false);
        deleteRoomBtn.setEnabled(false);
        // enable Add button
        addRoomBtn.setEnabled(true);
    }
    
    /**
     * hiện thị thông tin room
     * 
     * @param room
     */
    public void showRoom(Room room) {
        idField.setText("" + room.getId());
        nameField.setText(room.getName());
        ageField.setText("" + room.getAge());
        addressTA.setText(room.getAddress());
        cccdField.setText("" + room.getCccd());
        //sdtField.setText(room.getSdt());
        // enable Edit and Delete buttons
        editRoomBtn.setEnabled(true);
        deleteRoomBtn.setEnabled(true);
        // disable Add button
        addRoomBtn.setEnabled(false);
    }
    
    /**
     * lấy thông tin room
     * 
     * @return
     */
    public Room getRoomInfo() {
        // validate room
        if (!validateName() || !validateAge() || !validateAddress() || !validateCCCD() || !validateSDT()) {
            return null;
        }
        try {
            Room room = new Room();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                room.setId(Integer.parseInt(idField.getText()));
            }
            room.setName(nameField.getText().trim());
            room.setAge(Byte.parseByte(ageField.getText().trim()));
            room.setAddress(addressTA.getText().trim());
            room.setCccd(cccdField.getText().trim());
            //room.setSdt(sdtField.getText().trim());
            return room;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
    
    private boolean validateName() {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Name không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateAddress() {
        String address = addressTA.getText();
        if (address == null || "".equals(address.trim())) {
            addressTA.requestFocus();
            showMessage("Address không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateAge() {
        try {
            Byte age = Byte.parseByte(ageField.getText().trim());
            if (age <=0 || age > 100) {
                ageField.requestFocus();
                showMessage("Age không hợp lệ, age nên trong khoảng 0 đến 100.");
                return false;
            }
        } catch (Exception e) {
            ageField.requestFocus();
            showMessage("Age không hợp lệ!");
            return false;
        }
        return true;
    }
    
    private boolean validateCCCD() {
        try {
            int cccd=cccdField.getText().trim().length();
            if (cccd < 10 || cccd > 10) {
                cccdField.requestFocus();
                showMessage("CCCD không hợp lệ, cccd nên có độ dài bằng 10.");
                return false;
            }
        } catch (Exception e) {
            cccdField.requestFocus();
            showMessage("CCCD không hợp lệ!");
            return false;
        }
        return true;
    }
    
    private boolean validateSDT() {
        String sdt = sdtField.getText();
        if (sdt == null || "".equals(sdt.trim())) {
            sdtField.requestFocus();
            showMessage("SDT không được trống.");
            return false;
        }
        return true;
    }
    
    public void actionPerformed(ActionEvent e) {
    }
    
    public void valueChanged(ListSelectionEvent e) {
    }
    
    public void addAddRoomListener(ActionListener listener) {
        addRoomBtn.addActionListener(listener);
    }
    
    public void addEdiRoomListener(ActionListener listener) {
        editRoomBtn.addActionListener(listener);
    }
    
    public void addDeleteRoomListener(ActionListener listener) {
        deleteRoomBtn.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }
    
    public void addSortRoomCCCDListener(ActionListener listener) {
        sortRoomCCCDBtn.addActionListener(listener);
    }
    
    public void addSortRoomNameListener(ActionListener listener) {
        sortRoomNameBtn.addActionListener(listener);
    }
    
    public void addListRoomSelectionListener(ListSelectionListener listener) {
        roomTable.getSelectionModel().addListSelectionListener(listener);
    }
}
