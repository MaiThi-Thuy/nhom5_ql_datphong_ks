package vn.viettuts.qlks.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import vn.viettuts.qlks.entity.Customer;
import vn.viettuts.qlks.entity.CustomerJSON;
import vn.viettuts.qlks.entity.Room;
import vn.viettuts.qlks.utils.FileUtils;
import vn.viettuts.qlks.view.CustomerView;
import vn.viettuts.qlks.view.RoomView;

/**
 * CustomerDao class
 * 
 * @author viettuts.vn
 */
public class CustomerDao {
    private final static String CUSTOMER_FILE = "customer.json";
    private List<Customer> listCustomers;

    public CustomerDao() {
        this.listCustomers = readListCustomers();
        if (listCustomers == null) {
            listCustomers = new ArrayList<Customer>();
        }
    }

    /**
     * Lưu các đối tượng customer vào file customer.json
     * 
     * @param customers
     */
    public void writeListCustomers(List<Customer> customers) {
        CustomerJSON customerXML = new CustomerJSON();
        customerXML.setCustomer(customers);
        FileUtils.writeJSONtoFile(CUSTOMER_FILE, customerXML); // updated to JSON
    }

    /**
     * Đọc các đối tượng customer từ file customer.json
     * 
     * @return list customer
     */
    public List<Customer> readListCustomers() {
        List<Customer> list = new ArrayList<Customer>();
        CustomerJSON customerXML = FileUtils.readJSONFile(CUSTOMER_FILE, CustomerJSON.class); // updated to JSON
        if (customerXML != null) {
            list = customerXML.getCustomer();
        }
        else System.out.println("not found");
        return list;
    }
    public Customer searchCustomer(int id) {
        for (Customer customer : listCustomers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
    //tinh gia phong
    

    /**
     * thêm customer vào listCustomers và lưu listCustomers vào file
     * 
     * @param customer
     */
    public void add(Customer customer) {
        int id = 1;
        if (listCustomers != null && listCustomers.size() > 0) {
            id = listCustomers.size() + 1;
        }
        customer.setId(id);
        listCustomers.add(customer);
        writeListCustomers(listCustomers);
    }
    /**
     * cập nhật customer vào listCustomers và lưu listCustomers vào file
     * 
     * @param customer
     */
    public void edit(Customer customer) {
        int size = listCustomers.size();
        for (int i = 0; i < size; i++) {
            if (listCustomers.get(i).getId() == customer.getId()) {
                listCustomers.get(i).setName(customer.getName());
                listCustomers.get(i).setAge(customer.getAge());
                listCustomers.get(i).setAddress(customer.getAddress());
                listCustomers.get(i).setCccd(customer.getCccd());
                listCustomers.get(i).setSdt(customer.getSdt());
                listCustomers.get(i).setCheckIn(customer.getCheckIn());
                listCustomers.get(i).setCheckOut(customer.getCheckOut());
                listCustomers.get(i).setID_room(customer.getID_room());
                listCustomers.get(i).setTotalPrice(customer.getTotalPrice());
                writeListCustomers(listCustomers);
                break;
            }
        }
    }

    /**
     * xóa customer từ listCustomers và lưu listCustomers vào file
     * 
     * @param customer
     */
    public boolean delete(Customer customer) {
        boolean isFound = false;
        int size = listCustomers.size();
        for (int i = 0; i < size; i++) {
            if (listCustomers.get(i).getId() == customer.getId()) {
                customer = listCustomers.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listCustomers.remove(customer);
            writeListCustomers(listCustomers);
            return true;
        }
        return false;
    }
    /**
     * sắp xếp danh sách customer theo name theo tứ tự tăng dần
     */
    public void sortCustomerByName() {
        Collections.sort(listCustomers, new Comparator<Customer>() {
            public int compare(Customer customer1, Customer customer2) {
                return customer1.getName().compareTo(customer2.getName());
            }
        });
    }

    /**
     * sắp xếp danh sách customer theo TotalPrice theo tứ tự tăng dần
     */
   public void sortCustomerByPrice() {
       Collections.sort(listCustomers, new Comparator<Customer>() {
           public int compare(Customer customer1, Customer customer2) {
               return Double.compare(customer1.getTotalPrice(), customer2.getTotalPrice());
           }
       });
   }

    public List<Customer> getListCustomers() {
        return listCustomers;
    }
    
    public void setListCustomers(List<Customer> listCustomers) {
        this.listCustomers = listCustomers;
    }
    public List<Customer> searchCustomer(String keyword){
        if (keyword.isEmpty()) {
            return listCustomers;
        }
        List<Customer> result=new ArrayList<Customer>();
        for(Customer customer:listCustomers){
            if(customer.getName().contains(keyword)){
                result.add(customer);
            }
            else if (customer.getCccd().contains(keyword)){
                result.add(customer);
            }
            else if (customer.getSdt().contains(keyword)){
                result.add(customer);
            }
            else if (customer.getAddress().contains(keyword)){
                result.add(customer);
            }
            else if (String.valueOf(customer.getAge()).contains(keyword)){
                result.add(customer);
            }
            else if (String.valueOf(customer.getId()).contains(keyword)){
                result.add(customer);
            }
        }
        
        return result;
    }

    public DefaultCategoryDataset PriceTypesDataSet(List<Customer>c){
        Map<String,Double> m=new HashMap<>() {};
        for(Customer cs:c){
            for(Room r: cs.getID_room()){
                    Double s=r.getPrice()*CustomerView.calculateDaysBetween(cs.getCheckIn(), cs.getCheckOut());
                    m.put(r.getType(), m.getOrDefault(r.getType(), 0.0)+s);   
            }
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(String s:m.keySet()){
            dataset.addValue(m.get(s), s, s);
        }
        return dataset;
    }

     public TimeSeriesCollection TimeseriesDataset(List<Customer> customers) {
        TimeSeries series = new TimeSeries("Doanh thu");
        Map<Month, Double> monthlyRevenue = new HashMap<>();
        for (Customer customer : customers) {
            for (Room room : customer.getID_room()) {
                double revenue = room.getPrice() * CustomerView.calculateDaysBetween(customer.getCheckIn(), customer.getCheckOut());;
                Calendar cal = Calendar.getInstance();
                cal.setTime(customer.getCheckIn());
                Month month = new Month(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
                monthlyRevenue.put(month, monthlyRevenue.getOrDefault(month, 0.0) + revenue);
            }
        }
        for (Map.Entry<Month, Double> entry : monthlyRevenue.entrySet()) {
            series.add(entry.getKey(), entry.getValue());
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }
    public DefaultCategoryDataset FrequentDataset(List<Customer> customers) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Long> roomTypeCount = new HashMap<>();
        for (Customer customer : customers) {
            for (Room room : customer.getID_room()) {
                String roomType = room.getType(); // Lấy loại phòng
                roomTypeCount.put(roomType, roomTypeCount.getOrDefault(roomType, 0L) + 1);
            }
        }
        for (Map.Entry<String, Long> entry : roomTypeCount.entrySet()) {
            dataset.addValue(entry.getValue(), entry.getKey(), entry.getKey());
        }

        return dataset;
    }
    
}
