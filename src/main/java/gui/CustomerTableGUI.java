package gui;

import dao.UserDao;
import domain.Customer;
import exception.DaoException;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerTableGUI extends JFrame {
    JPanel panel1;
    JTable table1;
    UserDao userDao;
    List<Customer> customerList;


    CustomerTableGUI() {
        setTitle("Таблица пользователей");
        userDao = new UserDao();


        try {
            customerList = userDao.getCustomerList();
        } catch (DaoException e) {
            e.printStackTrace();
        }

        String[] columnNames = {
                "customerId", " customerFullName",  "customerPhone", "customerAddress"
        };

        String[][] data = new String[customerList.size()][];
        String[] buffer;
        int counter = 0;

        for (Customer o : customerList
        ) {

            buffer= new String[]{String.valueOf(o.getCustomerId()),
                    String.valueOf(o.getCustomerFullName()),
                    String.valueOf(o.getCustomerPhone()),
                    String.valueOf(o.getCustomerAddress())


            };


            data[counter] = buffer;
            counter++;
            table1 = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table1);
            getContentPane().add(scrollPane);
            setPreferredSize(new Dimension(560, 180));
            pack();
            setLocationRelativeTo(null);
            setVisible(true);

        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
