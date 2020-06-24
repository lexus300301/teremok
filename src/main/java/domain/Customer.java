package domain;

import dao.UserDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class Customer {
    private int customerId;
    private String customerFullName;
    private String customerPhone;
    private String customerAddress;

    public Customer() {
    }

    public Customer(int customerId, String customerFullName, String customerPhone, String customerAddress) {
        this.customerId = customerId;
        this.customerFullName = customerFullName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
    }

    public void makeOrder(Order order) throws SQLException {
        UserDao dao = new UserDao();
        Worker w = dao.getWorker(new Random().nextInt((5 - 1) + 1) + 1);
//        Service service;
//        service=dao.getService(order.getOrderService().getServiceId());

        order.setOrderId(new Random().nextInt((999 - 10) + 1) + 10);
        order.setOrderCustomer(this);
        order.setOrderWorker(w);
//        order.setOrderService(service);
        order.setOrderDuration(order.getOrderDuration());
        order.generateOrderPrice();
        dao.saveOrder(order);




    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerFullName='" + customerFullName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                '}';
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
