package vn.viettuts.qlks.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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
    private JButton sortRoomPriceBtn;
    private JButton sortRoomNameBtn;
    private JButton navigateToCustomerViewBtn; // New button
    private JScrollPane jScrollPaneRoomTable;
    private JScrollPane jScrollPaneAddress;
    private JTable roomTable;
    
    private JLabel idLabel;
    private JLabel typeLabel;
    private JLabel priceLabel;
    private JLabel statusLabel;
    
    private JTextField idField;
    private JTextField typeField;
    private JTextField priceField;
    private JTextField statusField;
    
    // định nghĩa các cột của bảng room
    private String [] columnNames = new String [] {
            "ID", "Type", "Price", "Status"};
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
        sortRoomPriceBtn = new JButton("Sort By Price");
        sortRoomNameBtn = new JButton("Sort By Name");
        navigateToCustomerViewBtn = new JButton("Customers"); // Initialize new button
        // khởi tạo bảng room
        jScrollPaneRoomTable = new JScrollPane();
        roomTable = new JTable();
        
        // khởi tạo các label
        idLabel = new JLabel("Id");
        typeLabel = new JLabel("Type");
        priceLabel = new JLabel("Price");
        statusLabel = new JLabel("Status");
        
        // khởi tạo các trường nhập dữ liệu cho room
        idField = new JTextField(6);
        idField.setEditable(false);
        typeField = new JTextField(15);
        priceField = new JTextField(15);
        statusField = new JTextField(15);
        
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
        panel.add(sortRoomPriceBtn);
        panel.add(sortRoomNameBtn);
        panel.add(navigateToCustomerViewBtn); // Add new button to panel
        
        panel.add(idLabel);
        panel.add(typeLabel);
        panel.add(priceLabel);
        panel.add(statusLabel);
        
        panel.add(idField);
        panel.add(typeField);
        panel.add(priceField);
        panel.add(statusField);
        
        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, priceLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, priceLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, statusLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, statusLabel, 100, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, priceField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, priceField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, statusField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, statusField, 100, SpringLayout.NORTH, panel);
        
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
        layout.putConstraint(SpringLayout.WEST, sortRoomPriceBtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortRoomPriceBtn, 330, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortRoomNameBtn, 115, SpringLayout.WEST, sortRoomPriceBtn);
        layout.putConstraint(SpringLayout.NORTH, sortRoomNameBtn, 330, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, navigateToCustomerViewBtn, 20, SpringLayout.WEST, panel); // Set layout for new button
        layout.putConstraint(SpringLayout.NORTH, navigateToCustomerViewBtn, 310, SpringLayout.NORTH, panel); // Set layout for new button
        
        this.add(panel);
        this.pack();
        this.setTitle("Room Information");
        this.setSize(800, 420);
        addRoomBtn.setEnabled(true);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    //fomat gia
         public static String formatDoubleToString(double value) {
        // Create a DecimalFormat instance
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        // Format the double value to a string
        return decimalFormat.format(value);
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
            rooms[i][2] = formatDoubleToString(list.get(i).getPrice());
            rooms[i][3] = list.get(i).isStatus();
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
            typeField.setText(roomTable.getModel().getValueAt(row, 1).toString());
            priceField.setText(roomTable.getModel().getValueAt(row, 2).toString().replace(".", ""));
            statusField.setText(roomTable.getModel().getValueAt(row, 3).toString());
        }
    }

    /**
     * xóa thông tin room
     */
    public void clearRoomInfo() {
        idField.setText("");
        typeField.setText("");
        priceField.setText("");
        statusField.setText("");
        addRoomBtn.setEnabled(true);
    }
    
    /**
     * hiện thị thông tin room
     * 
     * @param room
     */
    public void showRoom(Room room) {
        idField.setText("" + room.getId());
        typeField.setText(room.getType());
        priceField.setText("" + room.getPrice());
        statusField.setText("" + room.isStatus());
    }
    
    /**
     * lấy thông tin room
     * 
     * @return
     */
    public Room getRoomInfo() {
        // validate room
        if (!validateType() || !validatePrice() || !validateStatus()) {
            return null;
        }
        try {
            Room room = new Room();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                room.setId(Integer.parseInt(idField.getText()));
            }
            room.setType(typeField.getText().trim());
            room.setPrice(Double.parseDouble(priceField.getText().trim()));
            room.setStatus(Boolean.parseBoolean(statusField.getText().trim()));
            return room;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
    
    private boolean validateType() {
        String type = typeField.getText();
        if (type == null || "".equals(type.trim())) {
            typeField.requestFocus();
            showMessage("Type không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validatePrice() {
        try {
            Double price = Double.parseDouble(priceField.getText().trim());
            if (price <= 0) {
                priceField.requestFocus();
                showMessage("Price không hợp lệ, price nên lớn hơn 0.");
                return false;
            }
        } catch (Exception e) {
            priceField.requestFocus();
            showMessage("Price không hợp lệ!");
            return false;
        }
        return true;
    }
    
    private boolean validateStatus() {
        String status = statusField.getText();
        if (status == null || "".equals(status.trim())) {
            statusField.requestFocus();
            showMessage("Status không được trống.");
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
    
    public void addSortRoomPriceListener(ActionListener listener) {
        sortRoomPriceBtn.addActionListener(listener);
    }
    
    public void addSortRoomNameListener(ActionListener listener) {
        sortRoomNameBtn.addActionListener(listener);
    }
    
    public void addListRoomSelectionListener(ListSelectionListener listener) {
        roomTable.getSelectionModel().addListSelectionListener(listener);
    }
    public void addNavigateToCustomerViewListener(ActionListener listener) {
        navigateToCustomerViewBtn.addActionListener(listener);
    }
}
