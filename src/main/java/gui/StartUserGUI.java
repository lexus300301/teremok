package gui;

import dao.UserDao;
import domain.Customer;
import domain.Order;
import domain.Service;
import domain.ServiceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class StartUserGUI extends JFrame{
    private JButton makeAnOrder;
    private JRadioButton GYM;
    private JRadioButton SWIMMINGR;
    private JRadioButton YOGA;
    private JRadioButton BOXING;
    private JTextField duration;

    private JButton admintoolButton;
    private JPanel panel;
    private JButton prices;
    private JTextField card;

    StartUserGUI(){

        setTitle("ГЛАВНОЕ ОКНО");
        UserDao  dao = new UserDao();
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(500, 400, 556, 220);
        ButtonGroup sportType = new ButtonGroup();
        sportType.add(GYM);
        sportType.add(SWIMMINGR);
        sportType.add(YOGA);
        sportType.add(BOXING);
        GYM.setSelected(true);



        makeAnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!card.getText().isEmpty()){
                    Customer customer = dao.getCustomer(Integer.valueOf(card.getText()));
                    System.out.println(customer);
                    Order order = new Order();
                    if(!duration.getText().isEmpty() && Integer.parseInt(duration.getText())>0){
                        order.setOrderDuration(Integer.parseInt(duration.getText()));
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Некорректная продолжительность абонемента", "Output", JOptionPane.ERROR_MESSAGE);

                        ServiceType serviceType = null;
                    if(GYM.isSelected()) serviceType=ServiceType.GYM;
                    if(SWIMMINGR.isSelected()) serviceType=ServiceType.SWIMMING;
                    if(YOGA.isSelected()) serviceType=ServiceType.YOGA;
                    if(BOXING.isSelected()) serviceType=ServiceType.BOXING;
                    Service service = dao.getService(serviceType.ordinal()+1);
                    order.setOrderService(service);
                    customer.makeOrder(order);
                    JOptionPane.showMessageDialog(null, "Абонемент оформлен", "Output", JOptionPane.PLAIN_MESSAGE);}
                    else
                        JOptionPane.showMessageDialog(null, "Введите карту клиента", "Output", JOptionPane.ERROR_MESSAGE);


                } catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(null, "Клиента не существует", "Output", JOptionPane.ERROR_MESSAGE);

                    throwables.printStackTrace();
                }
            }
        });

        prices.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();

                try {
                    message.append("Цена на " +dao.getService(1).getServiceType()+ ":" + dao.getService(1).getServicePrice()+"\n" );
                    message.append("Цена на " +dao.getService(2).getServiceType()+ ":" + dao.getService(2).getServicePrice()+"\n" );
                    message.append("Цена на " +dao.getService(3).getServiceType()+ ":" + dao.getService(3).getServicePrice()+"\n" );
                    message.append("Цена на " +dao.getService(4).getServiceType()+ ":" + dao.getService(4).getServicePrice()+"\n" );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }



                JOptionPane.showMessageDialog(null, message.toString(), "Output", JOptionPane.PLAIN_MESSAGE);

            }
        });

        admintoolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginIntoAdmGUI();
            }
        });



    }
}
