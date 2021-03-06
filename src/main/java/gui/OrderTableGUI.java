package gui;

import dao.UserDao;
import domain.Customer;
import domain.Order;
import domain.Service;
import domain.Worker;
import exception.DaoException;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderTableGUI extends JFrame {
    JPanel panel1;
    JTable table1;
    UserDao userDao;
    List<Order> orderList;


    OrderTableGUI() {
        setTitle("Таблица заказов");

        userDao = new UserDao();


        try {
            orderList = userDao.getOrderList();
        } catch (DaoException e) {
            e.printStackTrace();
        }

        String[] columnNames = {
                "orderId", "customer", "orderWorker", "orderService", "orderDuration", "orderPrice"
        };

        String[][] data = new String[orderList.size()][];
        String[] buffer;
        int counter = 0;

        for (Order o : orderList
        ) {
            buffer= new String[]{String.valueOf(o.getOrderId()),
                    String.valueOf(o.getOrderCustomer().getCustomerFullName()),
                    String.valueOf(o.getOrderWorker().getWorkerSurname()),
                    String.valueOf(o.getOrderService().getServiceType()),
                    String.valueOf(o.getOrderDuration()),
                    String.valueOf(o.getOrderPrice()),


            };


            data[counter] = buffer;
            counter++;

        }
        table1 = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table1);
        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(560, 180));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
