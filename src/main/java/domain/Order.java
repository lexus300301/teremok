package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private int orderId;
    private Customer orderCustomer;
    private Worker orderWorker;
    private Service orderService;
    private Integer orderDuration;
    private Integer orderPrice;


    public Order() {
    }

    public Order(int orderId, Customer orderCustomer, Worker orderWorker, Service orderService, Integer orderDuration, Integer orderPrice) {
        this.orderId = orderId;
        this.orderCustomer = orderCustomer;
        this.orderWorker = orderWorker;
        this.orderService = orderService;
        this.orderDuration = orderDuration;
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderCustomer=" + orderCustomer +
                ", orderWorker=" + orderWorker +
                ", orderService=" + orderService +
                ", orderDuration=" + orderDuration +
                ", orderPrice=" + orderPrice +
                '}';
    }

    public void generateOrderPrice() {
        orderPrice = orderDuration * orderService.getServicePrice();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(Customer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public Worker getOrderWorker() {
        return orderWorker;
    }

    public void setOrderWorker(Worker orderWorker) {
        this.orderWorker = orderWorker;
    }

    public Service getOrderService() {
        return orderService;
    }

    public void setOrderService(Service orderService) {
        this.orderService = orderService;
    }

    public Integer getOrderDuration() {
        return orderDuration;
    }

    public void setOrderDuration(Integer orderDuration) {
        this.orderDuration = orderDuration;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }
}
