package vn.viettuts.qlks.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.viettuts.qlsv.entity.Customer;
import vn.viettuts.qlsv.entity.CustomerJSON;
import vn.viettuts.qlks.utils.FileUtils;

/**
 * CustomerDao class
 * 
 * @author viettuts.vn
 */
public class CustomerDao {
    private final static String STUDENT_FILE_NAME = "customer.json";
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
        FileUtils.writeJSONtoFile(STUDENT_FILE_NAME, customerXML); // updated to JSON
    }

    /**
     * Đọc các đối tượng customer từ file customer.json
     * 
     * @return list customer
     */
    public List<Customer> readListCustomers() {
        List<Customer> list = new ArrayList<Customer>();
        CustomerJSON customerXML = FileUtils.readJSONFile(STUDENT_FILE_NAME, CustomerJSON.class); // updated to JSON
        if (customerXML != null) {
            list = customerXML.getCustomer();
        }
        else System.out.println("not found");
        return list;
    }

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
     * sắp xếp danh sách customer theo CCCD theo tứ tự tăng dần
     */
//    public void sortCustomerByCCCD() {
//        Collections.sort(listCustomers, new Comparator<Customer>() {
//            public int compare(Customer customer1, Customer customer2) {
//                return Float.compare(customer1.getCccd(), customer2.getCccd());
//            }
//        });
//    }

    public List<Customer> getListCustomers() {
        return listCustomers;
    }

    public void setListCustomers(List<Customer> listCustomers) {
        this.listCustomers = listCustomers;
    }
}
