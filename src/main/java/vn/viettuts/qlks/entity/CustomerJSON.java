package vn.viettuts.qlks.entity;

import java.util.List;

public class CustomerJSON {
    
    private List<Customer> customer;

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CustomerXML [customers=" + customer + "]";
    }
}
