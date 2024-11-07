package vn.viettuts.qlks.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


import vn.viettuts.qlks.dao.RoomDao;
import vn.viettuts.qlks.entity.Customer;

public class CustomerView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JButton addCustomerBtn;
    private JButton editCustomerBtn;
    private JButton deleteCustomerBtn;
    private JButton clearBtn;
    private JButton sortCustomerByPriceBtn;
    private JButton sortCustomerNameBtn;
    private JButton navigateToRoomViewBtn; // New button
    private JButton addRoom; // New button addRoom
    private JButton logoutBut; // New button logout
    private JScrollPane jScrollPaneCustomerTable;
    private JScrollPane jScrollPaneAddress;
    private SimpleDateFormat DateF;
    private JTable customerTable;
    private JDateChooser CheckIn; // calendar
    private JDateChooser CheckOut;
    private JButton searchButton;
    private JButton ThongKeButton;

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
    private JLabel searchLabel; //search label
    

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea addressTA;
    private JTextField cccdField;
    private JTextField sdtField;
    private JTextField searchField;//search field
    
    private JComboBox<String> l_RoomTypes;
    private JComboBox<String> l_Rooms;
    
    // định nghĩa các cột của bảng customer
    private String [] columnNames = new String [] {
            "ID", "Ten", "Tuoi", "Dia Chi", "CCCD", "SDT", "CheckIn", "CheckOut","Phong Thue","Don Gia"}; // Updated columns
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
    public String getRoom(){
        return (String) l_Rooms.getSelectedItem();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addCustomerBtn = new JButton("Add");
        editCustomerBtn = new JButton("Edit");
        deleteCustomerBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortCustomerByPriceBtn = new JButton("Sort By Don gia");
        sortCustomerNameBtn = new JButton("Sort By Name");
        navigateToRoomViewBtn = new JButton("Rooms"); // Initialize new button
        ThongKeButton=new JButton("Thong Ke");

        addRoom = new JButton("Dat Phong"); // Initialize addRoom button
        CheckIn=new JDateChooser(); //Them checkIn
        CheckOut=new JDateChooser();//CheckOut
        CheckIn.setPreferredSize(new Dimension(135, 20));//setSize
        CheckOut.setPreferredSize(new Dimension(135, 20));
        CheckIn.setDateFormatString("dd/MM/yyyy");
        CheckOut.setDateFormatString("dd/MM/yyyy");
        DateF= new SimpleDateFormat("dd/MM/yyyy");
        searchButton=new JButton("Search");
        logoutBut=new JButton("Logout");
        
        // khởi tạo bảng customer
        jScrollPaneCustomerTable = new JScrollPane();
        customerTable = new JTable();
        // khởi tạo các label
        idLabel = new JLabel("Id");
        nameLabel = new JLabel("Ten");
        ageLabel = new JLabel("Tuoi");
        addressLabel = new JLabel("Dia Chi");
        cccdLabel = new JLabel("CCCD");
        sdtLabel = new JLabel("SDT");
        roomTypesLabel = new JLabel("Room Type");
        lRoomLabel = new JLabel("Rooms"); // Initialize rooms label
        checkInLabel = new JLabel("CheckIn"); // Initialize new label
        checkOutLabel = new JLabel("CheckOut"); // Initialize new label
        searchLabel=new JLabel("Tim Kiem");//tim kiem label

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
        searchField=new JTextField(15);
        
        // roomtypes show test
        l_RoomTypes = new JComboBox<>();
        l_Rooms = new JComboBox<>();
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

        panel.add(sortCustomerByPriceBtn);
        panel.add(sortCustomerNameBtn);
        panel.add(navigateToRoomViewBtn); // Add new button to panel
        panel.add(searchButton);
        panel.add(ThongKeButton);
        panel.add(logoutBut);

        panel.add(addRoom); // Add addRoom button to panel
        panel.add(roomTypesLabel);
        panel.add(lRoomLabel); // Add new label to panel
        panel.add(checkInLabel); // Add new label to panel
        panel.add(checkOutLabel); // Add new label to panel
        panel.add(searchLabel);//add search label
        
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
        panel.add(l_RoomTypes);// add roomtypes to panel
        panel.add(l_Rooms);// add rooms to panel
        panel.add(CheckIn);//add CheckIn
        panel.add(CheckOut);//add CheckOut
        panel.add(searchField);//search field


        //label layout
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
        layout.putConstraint(SpringLayout.WEST, searchLabel, 300, SpringLayout.WEST, panel); 
        layout.putConstraint(SpringLayout.NORTH, searchLabel, 10, SpringLayout.NORTH, panel);
        
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
        layout.putConstraint(SpringLayout.WEST, CheckIn, 100, SpringLayout.WEST, panel); // Position new field
        layout.putConstraint(SpringLayout.NORTH, CheckIn, 230, SpringLayout.NORTH, panel); // Position new field
        layout.putConstraint(SpringLayout.WEST, CheckOut, 100, SpringLayout.WEST, panel); // Position new field
        layout.putConstraint(SpringLayout.NORTH, CheckOut, 260, SpringLayout.NORTH, panel); // Position new field
        layout.putConstraint(SpringLayout.WEST, l_Rooms, 100, SpringLayout.WEST, panel); // Position new field
        layout.putConstraint(SpringLayout.NORTH, l_Rooms, 290, SpringLayout.NORTH, panel); // Position new field
        layout.putConstraint(SpringLayout.WEST, searchField, 370, SpringLayout.WEST, panel); // Position new field
        layout.putConstraint(SpringLayout.NORTH, searchField, 10, SpringLayout.NORTH, panel); // Position new field

        layout.putConstraint(SpringLayout.WEST, jScrollPaneCustomerTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneCustomerTable, 40, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addCustomerBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addCustomerBtn, 360, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editCustomerBtn, 60, SpringLayout.WEST, addCustomerBtn);
        layout.putConstraint(SpringLayout.NORTH, editCustomerBtn, 360, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteCustomerBtn, 60, SpringLayout.WEST, editCustomerBtn);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 360, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deleteCustomerBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteCustomerBtn, 360, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortCustomerByPriceBtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortCustomerByPriceBtn, 390, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortCustomerNameBtn, 115, SpringLayout.WEST, sortCustomerByPriceBtn);
        layout.putConstraint(SpringLayout.NORTH, sortCustomerNameBtn, 390, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, navigateToRoomViewBtn, 115, SpringLayout.WEST, sortCustomerNameBtn); // Position new button
        layout.putConstraint(SpringLayout.NORTH, navigateToRoomViewBtn, 390, SpringLayout.NORTH, panel); // Position new button
        layout.putConstraint(SpringLayout.WEST, addRoom, 20, SpringLayout.WEST, panel); // Position addRoom button
        layout.putConstraint(SpringLayout.NORTH, addRoom, 390, SpringLayout.NORTH, panel); // Position addRoom button
        layout.putConstraint(SpringLayout.WEST, searchButton, 520, SpringLayout.WEST, panel); // Position search button
        layout.putConstraint(SpringLayout.NORTH, searchButton, 10, SpringLayout.NORTH, panel); // Position search button
        layout.putConstraint(SpringLayout.WEST, ThongKeButton, 600, SpringLayout.WEST, panel); // Position ThongKe button
        layout.putConstraint(SpringLayout.NORTH, ThongKeButton, 390, SpringLayout.NORTH, panel); // Position ThongKe button
        layout.putConstraint(SpringLayout.WEST, logoutBut, 700, SpringLayout.WEST, panel); // Position logout button
        layout.putConstraint(SpringLayout.NORTH, logoutBut, 390, SpringLayout.NORTH, panel); // Position logout button

        this.add(panel);
        this.pack();
        this.setTitle("Customer Information");
        this.setSize(1200, 520);
        // disable Edit and Delete buttons
        editCustomerBtn.setEnabled(false);
        deleteCustomerBtn.setEnabled(false);
        // enable Add button
        addCustomerBtn.setEnabled(true);
        addRoom.setEnabled(false);
        
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void autoResizeColumnWidth(JTable table) {// dieu chinh kich thuoc bang tu dong
    // Duyệt qua từng cột trong bảng
    for (int column = 0; column < table.getColumnCount(); column++) {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        int preferredWidth = tableColumn.getMinWidth();
        int maxWidth = tableColumn.getMaxWidth();

        // Duyệt qua từng hàng trong bảng để tính chiều rộng cần thiết
        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
            Component c = table.prepareRenderer(cellRenderer, row, column);
            int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
            preferredWidth = Math.max(preferredWidth, width);

            // Đảm bảo chiều rộng không vượt quá maxWidth
            if (preferredWidth >= maxWidth) {
                preferredWidth = maxWidth;
                break;
            }
        }

        // Tính chiều rộng dựa trên tiêu đề của cột
        TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
        Component headerComponent = headerRenderer.getTableCellRendererComponent(table, tableColumn.getHeaderValue(), false, false, 0, column);
        int headerWidth = headerComponent.getPreferredSize().width;
        preferredWidth = Math.max(preferredWidth, headerWidth);

        // Thiết lập chiều rộng của cột
        tableColumn.setPreferredWidth(preferredWidth);
    }
}
//fomat gia
         public static String formatDoubleToString(double value) {
        // Create a DecimalFormat instance
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        // Format the double value to a string
        return decimalFormat.format(value);
    }

    /**
     * hiển thị list customer vào bảng customerTable
     * 
     * @param list
     */
    
    public void showListCustomers(List<Customer> list,RoomDao roomDao) {
        int size = list.size();
        Object [][] customers = new Object[size][10];
        for (int i = 0; i < size; i++) {
            customers[i][0] = list.get(i).getId();
            customers[i][1] = list.get(i).getName();
            customers[i][2] = list.get(i).getAge();
            customers[i][3] = list.get(i).getAddress();
            customers[i][4] = list.get(i).getCccd();
            customers[i][5] = list.get(i).getSdt();
            customers[i][6] = DateF.format(list.get(i).getCheckIn());
            customers[i][7] = DateF.format(list.get(i).getCheckOut());
            if (!list.get(i).getID_room().isEmpty()) {
                customers[i][8] = list.get(i).getID_room().size(); // New column
                customers[i][9]= formatDoubleToString(list.get(i).getTotalPrice()); 
            }
            else {
                customers[i][8] = 0; // New column
                customers[i][9] = formatDoubleToString(0.0); // New column
            }
        }
        customerTable.setModel(new DefaultTableModel(customers, columnNames));
        
        autoResizeColumnWidth(customerTable); // auto resize column width
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
            try {
                CheckIn.setDate(DateF.parse(customerTable.getModel().getValueAt(row, 6).toString()));
                CheckOut.setDate(DateF.parse(customerTable.getModel().getValueAt(row, 7).toString()));
            } catch (ParseException ex) {
                Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            // enable Edit and Delete buttons
            editCustomerBtn.setEnabled(true);
            deleteCustomerBtn.setEnabled(true);
            // disable Add button
            addCustomerBtn.setEnabled(false);
            addRoom.setEnabled(true);
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
        CheckIn.setDate(null);
        CheckOut.setDate(null);
        // disable Edit and Delete buttons
        editCustomerBtn.setEnabled(false);
        deleteCustomerBtn.setEnabled(false);
        // enable Add button
        addCustomerBtn.setEnabled(true);
        addRoom.setEnabled(true);
    }
    //lay tu khoa
    public String getKeyWord(){
        return searchField.getText().trim();
    }


    //Tinh so ngay thue
    public static int calculateDaysBetween(Date startDate, Date endDate) {
        // Convert Date to LocalDate
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Calculate the difference in days
        long daysBetween = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);

        // Return the difference as an int
        return (int) daysBetween;
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
            CheckIn.setDate(customer.getCheckIn());
            CheckOut.setDate(customer.getCheckOut());
        // enable Edit and Delete buttons
        editCustomerBtn.setEnabled(true);
        deleteCustomerBtn.setEnabled(true);
        // disable Add button
        addCustomerBtn.setEnabled(false);
        addRoom.setEnabled(true);
        
    }
    
    /**
     * lấy thông tin customer
     * 
     * @return
     */
    public int getSelectedCustomerID(){
        int row = customerTable.getSelectedRow();
        if (row >= 0) {
            return Integer.parseInt(customerTable.getModel().getValueAt(row, 0).toString());
        }
        return -1;
    }
    public Customer getCustomerInfo() {
        // validate customer
        if (!validateName() || !validateAge() || !validateAddress() || !validateCCCD() || !validateSDT()|| !validateCheckIn()|| !validateCheckOut()) {
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
            customer.setCheckIn(CheckIn.getDate()); // New field
            customer.setCheckOut(CheckOut.getDate()); // New field
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
    private boolean validateCheckIn() {
        Date checkIn = CheckIn.getDate();
        if (checkIn == null) {
            CheckIn.requestFocus();
            showMessage("CheckIn không được trống.");
            return false;
        }
        return true;
    }
    private boolean validateCheckOut() {
        Date checkOut = CheckOut.getDate();
        if (checkOut == null) {
            CheckOut.requestFocus();
            showMessage("CheckOut không được trống.");
            return false;
        }
        return true;
    }
    public JTable getCustomerTable() {
        return customerTable;
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
    
    public void addSortCustomerPriceListener(ActionListener listener) {
        sortCustomerByPriceBtn.addActionListener(listener);
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

    public void addClickRoomListener(MouseListener listener){
        customerTable.addMouseListener(listener);
    }
    public void addAddRoomListener(ActionListener listener){
        addRoom.addActionListener(listener);
    }
    public void addSearchListener(ActionListener listener){
        searchButton.addActionListener(listener);
    }
    public void addThongKeListener(ActionListener listener){
        ThongKeButton.addActionListener(listener);
    }
    public void addLogoutListener(ActionListener listener){
        logoutBut.addActionListener(listener);
    }
}
