package vn.viettuts.qlks.view;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import vn.viettuts.qlks.controller.CustomerController;

import vn.viettuts.qlks.entity.Customer;
import vn.viettuts.qlks.dao.CustomerDao;
import vn.viettuts.qlks.dao.RoomDao;
import vn.viettuts.qlks.entity.Room;
import vn.viettuts.qlks.entity.User;

public class StatsView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton CustomersBtn;
    private JButton ExportBtn;
    
    private JPanel chartPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private JFreeChart barChart;
    private JFreeChart frequentChart;
    private JFreeChart lineChart;
    private String [] columnNames = new String [] {"Tong khach hang", "Tong doanh thu","So khach thang nay", "Doanh thu thang nay"};
    private Object data = new Object [][] {};
    private List<Customer> cusList;
    private List<Room> roomList;
    private RoomDao roomDao;
    private CustomerDao customerDao;
    private ArrayList<String> SumStat;
    private User curUser;
    
    public StatsView(User curUser) {
        this.roomDao=new RoomDao();
        this.customerDao=new CustomerDao();
        this.roomList=roomDao.getListRooms();
        this.cusList=customerDao.getListCustomers();
        this.curUser=curUser;
        initComponents();
    }
    public void showStatsSummary(List<Customer> customers) {
        int totalCustomers = customers.size();
        int customersThisMonth = (int) customers.stream()
                .filter(c -> c.getCheckIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == LocalDate.now().getMonthValue())
                .count();
        double totalRevenue = customers.stream()
                .mapToDouble(Customer::getTotalPrice)
                .sum();
        double revenueThisMonth = customers.stream()
                .filter(c -> c.getCheckIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == LocalDate.now().getMonthValue())
                .mapToDouble(Customer::getTotalPrice)
                .sum();
        Object [][] stats = new Object[1][4];
        stats[0][0]=totalCustomers;
        stats[0][1]=CustomerView.formatDoubleToString(totalRevenue);
        stats[0][2]=customersThisMonth;
        stats[0][3]=CustomerView.formatDoubleToString(revenueThisMonth);
        this.SumStat.add(totalCustomers+"");
        this.SumStat.add(CustomerView.formatDoubleToString(totalRevenue));
        this.SumStat.add(customersThisMonth+"");
        this.SumStat.add(CustomerView.formatDoubleToString(revenueThisMonth));
        table.setModel(new DefaultTableModel(stats, columnNames));
        
    }
    private void initComponents() {
        this.SumStat = new ArrayList<String>();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        scrollPane=new JScrollPane();
        table = new JTable();
        table.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        CustomersBtn = new JButton("Customers");
        ExportBtn = new JButton("Export Report");
        chartPanel = new JPanel();
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension (500, 50));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(900, 500);
        panel.setLayout(layout);
        panel.add(scrollPane);
        panel.add(ExportBtn);
        panel.add(CustomersBtn);
        panel.add(chartPanel);

        layout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, CustomersBtn, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, CustomersBtn, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, chartPanel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, chartPanel, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, chartPanel, -10, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.SOUTH, chartPanel, -10, SpringLayout.SOUTH, panel);
        layout.putConstraint(SpringLayout.WEST, ExportBtn, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ExportBtn, 100, SpringLayout.NORTH, panel);


        this.add(panel);
        this.pack();
        this.setTitle("Statistics View");
        this.setSize(1200, 720);
        CustomersBtn.addActionListener(this);
        ExportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compileJasper();
            }
        });
        showStatsSummary(cusList);
        this.barChart = ChartFactory.createBarChart(
            "Tong doanh thu theo loai phong",
            "Loai Phong",
            "Doanh Thu (VND)",
            customerDao.PriceTypesDataSet(cusList),
            PlotOrientation.VERTICAL,
            true, true, false);

        this.lineChart = ChartFactory.createTimeSeriesChart(
            "Doanh thu theo thang",
            "Thang",
            "Doanh thu (VND)",
            customerDao.TimeseriesDataset(cusList),
            true,
            true,
            false
        );

        XYPlot plot = (XYPlot) lineChart.getPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));

        this.frequentChart = ChartFactory.createBarChart(
            "Tan suat thue theo loai phong", 
            "Loai phong",                    
            "Tan suat (Lan)",                
            customerDao.FrequentDataset(cusList),                         
            PlotOrientation.VERTICAL,       
            true, true, false);

        chartPanel.removeAll();
        chartPanel.setLayout(new java.awt.GridLayout(1, 3)); // Set layout to GridLayout with 1 row and 3 columns
        chartPanel.add(new ChartPanel(barChart));
        chartPanel.add(new ChartPanel(lineChart));
        chartPanel.add(new ChartPanel(frequentChart));
        chartPanel.validate();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    public void compileJasper() {
        try {
            String jrxmlFile = "Report.jrxml";
            //String jasperFile = "Reprort.jasper";
            
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);
             Map<String, Object> parameters = new HashMap<>();
        parameters.put("pic1", getChartImage(barChart)); // Get BufferedImage
        parameters.put("pic2", getChartImage(lineChart));
        parameters.put("pic3", getChartImage(frequentChart));

        // Populate parameters with values from SumStat
        parameters.put("tongdoanhthu", SumStat.get(1)+" VND"); // Total Revenue
        parameters.put("tongkhach", SumStat.get(0)); // Total Customers
        parameters.put("doanhthuthang", SumStat.get(3)+" VND"); // Revenue This Month
        parameters.put("khachthang", SumStat.get(2)); // Customers This Month
        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());

        // Export the report to PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
        //System.out.println("Report exported successfully!");
        showMessage("Xuất báo cáo thành công");    
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == CustomersBtn) {
            this.setVisible(false);
            CustomerView customerView = new CustomerView();
            CustomerController customerController=new CustomerController(customerView,curUser);
            customerController.showCustomerView();
            
         }
    }

// Helper method to create BufferedImage from JFreeChart
private BufferedImage getChartImage(JFreeChart chart) {
    return chart.createBufferedImage(500, 200); // Adjust size as needed
}


}
