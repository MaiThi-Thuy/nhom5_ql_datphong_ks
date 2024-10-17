package vn.viettuts.qlks.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import vn.viettuts.qlks.entity.Customer;

public class CustomerView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JButton addCustomerBtn;
    private JButton editCustomerBtn;
    private JButton deleteCustomerBtn;
    private JButton clearBtn;
    private JButton sortCustomerCCCDBtn;
    private JButton sortCustomerNameBtn;
    private JButton navigateToRoomViewBtn; // New button
    private JScrollPane jScrollPaneCustomerTable;
    private JScrollPane jScrollPaneAddress;
    private JTable customerTable;
    
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;
    private JLabel cccdLabel;
    private JLabel sdtLabel;
    private JLabel roomTypesLabel; //romm type label
    private JLabel lRoomLabel; //room label
    private JLabel checkInLabel; // New label
    private JLabel checkOutLabel; // New label

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea addressTA;
    private JTextField cccdField;
    private JTextField sdtField;
    private JTextField checkInField; // New field
    private JTextField checkOutField; // New field
    
    private JComboBox<String> l_RoomTypes;
    private JComboBox<String> l_Rooms;
    
    // định nghĩa các cột của bảng customer
    private String [] columnNames = new String [] {
            "ID", "Ten", "Tuoi", "DiaChi", "CCCD", "SDT", "CheckIn", "CheckOut"}; // Updated columns
    // định nghĩa dữ liệu mặc định của bẳng customer là rỗng
    private Object data = new Object [][] {};
    
    public CustomerView() {
        initComponents();
    }
    //Them loai phong vao combobox
    public void addTypesRoom(List<String> types){
        for(int i=0;i<types.size();i++){
            this.l_RoomTypes.addItem(types.get(i));
        }
            
    }
    public void addRooms(ArrayList<String> rooms){
        this.l_Rooms.removeAllItems();
        for(int i=0;i<rooms.size();i++){
            this.l_Rooms.addItem(rooms.get(i));
        }

    }
    public String getRoomType(){
        return (String) l_RoomTypes.getSelectedItem();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addCustomerBtn = new JButton("Add");
        editCustomerBtn = new JButton("Edit");
        deleteCustomerBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortCustomerCCCDBtn = new JButton("Sort By CCCD");
        sortCustomerNameBtn = new JButton("Sort By Name");
        navigateToRoomViewBtn = new JButton("Rooms"); // Initialize new button
        
        // khởi tạo bảng customer
        jScrollPaneCustomerTable = new JScrollPane();
        customerTable = new JTable();
        // khởi tạo các label
        idLabel = new JLabel("Id");
        nameLabel = new JLabel("Ten");
        ageLabel = new JLabel("Tuoi");
        addressLabel = new JLabel("DiaChi");
        cccdLabel = new JLabel("CCCD");
        sdtLabel = new JLabel("SDT");
        roomTypesLabel = new JLabel("Room Type");
        lRoomLabel = new JLabel("Rooms"); // Initialize rooms label
        checkInLabel = new JLabel("CheckIn"); // Initialize new label
        checkOutLabel = new JLabel("CheckOut"); // Initialize new label
        
        // khởi tạo các trường nhập dữ liệu cho customer
        idField = new JTextField(6);
        idField.setEditable(false);
        nameField = new JTextField(15);
        ageField = new JTextField(6);
        addressTA = new JTextArea();
        addressTA.setColumns(15);
        addressTA.setRows(2);
        jScrollPaneAddress = new JScrollPane();
        jScrollPaneAddress.setViewportView(addressTA);
        cccdField = new JTextField(15);
        sdtField = new JTextField(15);
        checkInField = new JTextField(15); // Initialize new field
        checkOutField = new JTextField(15); // Initialize new field
        
        // roomtypes show test
        l_RoomTypes = new JComboBox<String>();
        l_Rooms = new JComboBox<String>();
//        l_RoomTypes.addItem("VIP");
//        l_RoomTypes.addItem("Normal");
        
        
        // cài đặt các cột và data cho bảng customer
        customerTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneCustomerTable.setViewportView(customerTable);
        jScrollPaneCustomerTable.setPreferredSize(new Dimension (800, 340));
        
         // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Customer
        JPanel panel = new JPanel();
        panel.setSize(900, 500);    
        panel.setLayout(layout);
        panel.add(jScrollPaneCustomerTable);
        
        panel.add(addCustomerBtn);
        panel.add(editCustomerBtn);
        panel.add(deleteCustomerBtn);
        panel.add(clearBtn);
        panel.add(sortCustomerCCCDBtn);
        panel.add(sortCustomerNameBtn);
        panel.add(navigateToRoomViewBtn); // Add new button to panel
        panel.add(roomTypesLabel);
        panel.add(lRoomLabel); // Add new label to panel
        panel.add(checkInLabel); // Add new label to panel
        panel.add(checkOutLabel); // Add new label to panel


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
        panel.add(checkInField); // Add new field to panel
        panel.add(checkOutField); // Add new field to panel
        panel.add(l_RoomTypes);// add roomtypes to panel
        panel.add(l_Rooms);// add rooms to panel
        //label
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ageLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, addressLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addressLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, cccdLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cccdLabel, 140, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sdtLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sdtLabel, 170, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, roomTypesLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, roomTypesLabel, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, checkInLabel, 10, SpringLayout.WEST, panel); // Position new label
        layout.putConstraint(SpringLayout.NORTH, checkInLabel, 230, SpringLayout.NORTH, panel); // Position new label
        layout.putConstraint(SpringLayout.WEST, checkOutLabel, 10, SpringLayout.WEST, panel); // Position new label
        layout.putConstraint(SpringLayout.NORTH, checkOutLabel, 260, SpringLayout.NORTH, panel); // Position new label
        layout.putConstraint(SpringLayout.WEST, lRoomLabel, 10, SpringLayout.WEST, panel); // Position new label
        layout.putConstraint(SpringLayout.NORTH, lRoomLabel, 290, SpringLayout.NORTH, panel); // Position new label
        
        
        //Fiels
        layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ageField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, cccdField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cccdField, 140, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sdtField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sdtField, 170, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, l_RoomTypes, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, l_RoomTypes, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, checkInField, 100, SpringLayout.WEST, panel); // Position new field
        layout.putConstraint(SpringLayout.NORTH, checkInField, 230, SpringLayout.NORTH, panel); // Position new field
        layout.putConstraint(SpringLayout.WEST, checkOutField, 100, SpringLayout.WEST, panel); // Position new field
        layout.putConstraint(SpringLayout.NORTH, checkOutField, 260, SpringLayout.NORTH, panel); // Position new field
        layout.putConstraint(SpringLayout.WEST, l_Rooms, 100, SpringLayout.WEST, panel); // Position new field
        layout.putConstraint(SpringLayout.NORTH, l_Rooms, 290, SpringLayout.NORTH, panel); // Position new field


        layout.putConstraint(SpringLayout.WEST, jScrollPaneCustomerTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneCustomerTable, 10, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addCustomerBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addCustomerBtn, 360, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editCustomerBtn, 60, SpringLayout.WEST, addCustomerBtn);
        layout.putConstraint(SpringLayout.NORTH, editCustomerBtn, 360, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteCustomerBtn, 60, SpringLayout.WEST, editCustomerBtn);
        
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 360, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deleteCustomerBtn);
        
        layout.putConstraint(SpringLayout.NORTH, deleteCustomerBtn, 360, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortCustomerCCCDBtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortCustomerCCCDBtn, 360, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortCustomerNameBtn, 115, SpringLayout.WEST, sortCustomerCCCDBtn);
        layout.putConstraint(SpringLayout.NORTH, sortCustomerNameBtn, 360, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, navigateToRoomViewBtn, 115, SpringLayout.WEST, sortCustomerNameBtn); // Position new button
        layout.putConstraint(SpringLayout.NORTH, navigateToRoomViewBtn, 360, SpringLayout.NORTH, panel); // Position new button
        
        this.add(panel);
        this.pack();
        this.setTitle("Customer Information");
        this.setSize(1200, 520);
        // disable Edit and Delete buttons
        editCustomerBtn.setEnabled(false);
        deleteCustomerBtn.setEnabled(false);
        // enable Add button
        addCustomerBtn.setEnabled(true);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    // public void showTypesRoom(ArrayList<String> types){
    //     l_RoomTypes =new
    // }
    /**
     * hiển thị list customer vào bảng customerTable
     * 
     * @param list
     */
    public void showListCustomers(List<Customer> list) {
        int size = list.size();
        // với bảng customerTable có 8 cột, 
        // khởi tạo mảng 2 chiều customers, trong đó:
        // số hàng: là kích thước của list customer 
        // số cột: là 8
        Object [][] customers = new Object[size][8];
        for (int i = 0; i < size; i++) {
            customers[i][0] = list.get(i).getId();
            customers[i][1] = list.get(i).getName();
            customers[i][2] = list.get(i).getAge();
            customers[i][3] = list.get(i).getAddress();
            customers[i][4] = list.get(i).getCccd();
            customers[i][5] = list.get(i).getSdt();
            customers[i][6] = list.get(i).getCheckIn(); // New column
            customers[i][7] = list.get(i).getCheckOut(); // New column
        }
        customerTable.setModel(new DefaultTableModel(customers, columnNames));
    }
    
    /**
     * điền thông tin của hàng được chọn từ bảng customer 
     * vào các trường tương ứng của customer.
     */
    public void fillCustomerFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = customerTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(customerTable.getModel().getValueAt(row, 0).toString());
            nameField.setText(customerTable.getModel().getValueAt(row, 1).toString());
            ageField.setText(customerTable.getModel().getValueAt(row, 2).toString());
            addressTA.setText(customerTable.getModel().getValueAt(row, 3).toString());
            cccdField.setText(customerTable.getModel().getValueAt(row, 4).toString());
            sdtField.setText(customerTable.getModel().getValueAt(row, 5).toString());
            checkInField.setText(customerTable.getModel().getValueAt(row, 6).toString()); // New field
            checkOutField.setText(customerTable.getModel().getValueAt(row, 7).toString()); // New field
            // enable Edit and Delete buttons
            editCustomerBtn.setEnabled(true);
            deleteCustomerBtn.setEnabled(true);
            // disable Add button
            addCustomerBtn.setEnabled(false);
        }
    }

    /**
     * xóa thông tin customer
     */
    public void clearCustomerInfo() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressTA.setText("");
        cccdField.setText("");
        sdtField.setText("");
        checkInField.setText(""); // Clear new field
        checkOutField.setText(""); // Clear new field
        // disable Edit and Delete buttons
        editCustomerBtn.setEnabled(false);
        deleteCustomerBtn.setEnabled(false);
        // enable Add button
        addCustomerBtn.setEnabled(true);
    }
    
    /**
     * hiện thị thông tin customer
     * 
     * @param customer
     */
    public void showCustomer(Customer customer) {
        idField.setText("" + customer.getId());
        nameField.setText(customer.getName());
        ageField.setText("" + customer.getAge());
        addressTA.setText(customer.getAddress());
        cccdField.setText("" + customer.getCccd());
        sdtField.setText(""+customer.getSdt());
        checkInField.setText(customer.getCheckIn()); // New field
        checkOutField.setText(customer.getCheckOut()); // New field
        // enable Edit and Delete buttons
        editCustomerBtn.setEnabled(true);
        deleteCustomerBtn.setEnabled(true);
        // disable Add button
        addCustomerBtn.setEnabled(false);
    }
    
    /**
     * lấy thông tin customer
     * 
     * @return
     */
    public Customer getCustomerInfo() {
        // validate customer
        if (!validateName() || !validateAge() || !validateAddress() || !validateCCCD() || !validateSDT()) {
            return null;
        }
        try {
            Customer customer = new Customer();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                customer.setId(Integer.parseInt(idField.getText()));
            }
            customer.setName(nameField.getText().trim());
            customer.setAge(Byte.parseByte(ageField.getText().trim()));
            customer.setAddress(addressTA.getText().trim());
            customer.setCccd(cccdField.getText().trim());
            customer.setSdt(sdtField.getText().trim());
            customer.setCheckIn(checkInField.getText().trim()); // New field
            customer.setCheckOut(checkOutField.getText().trim()); // New field
            return customer;
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
    
    public void addAddCustomerListener(ActionListener listener) {
        addCustomerBtn.addActionListener(listener);
    }
    
    public void addEdiCustomerListener(ActionListener listener) {
        editCustomerBtn.addActionListener(listener);
    }
    
    public void addDeleteCustomerListener(ActionListener listener) {
        deleteCustomerBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }
    
    public void addSortCustomerCCCDListener(ActionListener listener) {
        sortCustomerCCCDBtn.addActionListener(listener);
    }
    
    public void addSortCustomerNameListener(ActionListener listener) {
        sortCustomerNameBtn.addActionListener(listener);
    }
    
    public void addListCustomerSelectionListener(ListSelectionListener listener) {
        customerTable.getSelectionModel().addListSelectionListener(listener);
    }
    
    //room types listener
    public void addRoomtypesListener(ActionListener listener){
        l_RoomTypes.addActionListener(listener);
    }

    public void addNavigateToRoomViewListener(ActionListener listener) {
        navigateToRoomViewBtn.addActionListener(listener);
    }
}
