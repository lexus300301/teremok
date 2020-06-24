package dao;

import domain.Customer;
import domain.Worker;
import junit.framework.TestCase;

import java.sql.SQLException;

public class UserDaoTest extends TestCase {

    public void testGetCustomer() throws SQLException {
        UserDao dao  = new UserDao();
        System.out.println(dao.getCustomer(99));

    }

    public void testSaveCustomer() throws SQLException {
        UserDao dao  = new UserDao();
        Customer c = dao.getCustomer(1);
        c.setCustomerFullName("Александр Александрович Орел");
       dao.saveCustomer(c);

    }

    public void testGetWorker() throws SQLException {
        UserDao dao  = new UserDao();
        System.out.println(dao.getWorker(99));
    }

    public void testSaveWorker() throws SQLException {
        UserDao dao  = new UserDao();

        Worker w = dao.getWorker(1);
        w.setWorkerId(99);
        w.setWorkerName("lexus");
        dao.saveWorker(w);

    }

    public void testGetOrder() throws SQLException {
        UserDao dao = new UserDao();
        System.out.println(dao.getOrder(2));
    }
}