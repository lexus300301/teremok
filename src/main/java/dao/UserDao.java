package dao;

import config.Config;
import domain.*;
import exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserDao {

    public static final String SELECT_WORKER = "SELECT * FROM worker WHERE worker_id=? ;";
    public static final String SELECT_WORKER_LIST = "SELECT * FROM worker";
    public static final String SELECT_CUSTOMER = "SELECT * FROM customer WHERE customer_id =  ?";
    public static final String SELECT_CUSTOMER_LIST = "SELECT * FROM customer";
    public static final String SELECT_SERVICE = "SELECT * FROM service WHERE service_id=  ?";
    public static final String SELECT_ORDER = "SELECT * FROM orders WHERE order_id=  ?";
    public static final String SELECT_ORDER_LIST_DESC = "SELECT * from orders order by order_id DESC";


    public static final String SET_TOTAL_ORDER_PRICE = "UPDATE orders SET order_totalprice=? WHERE order_id=?";


    public static final String INSERT_ORDER = "INSERT INTO orders(" +
            " order_customer, order_worker, order_service, order_duration, order_price)" +
            "VALUES (?, ?, ?, ?, ?);";
    public static final String INSERT_CUSTOMER = "INSERT INTO customer(" +
            " customer_fullname, customer_phone, customer_address)" +
            "VALUES (?, ?, ?);";
    public static final String INSERT_WORKER = "INSERT INTO worker(" +
            "worker_id, worker_salary, worker_name, worker_surname, worker_phone)" +
            "VALUES (?, ?, ?, ?, ?);";

    public static final String SELECT_ORDER_LIST = "SELECT * from orders";


    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;
    }


    public Customer getCustomer(Integer customerId) throws SQLException {
        Customer result = new Customer();


        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_CUSTOMER)
        ) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

//            if(!rs.next() ) throw new  SQLException("Bad client");

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setCustomerFullName(rs.getString("customer_fullname"));
                customer.setCustomerAddress(rs.getString("customer_address"));
                customer.setCustomerPhone(rs.getString("customer_phone"));

                result = customer;
            }

        }
        return result;

    }

    public void saveCustomer(Customer customer) throws SQLException {


        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_CUSTOMER)
        ) {
            stmt.setString(1, customer.getCustomerFullName());
            stmt.setString(2, customer.getCustomerPhone());
            stmt.setString(3, customer.getCustomerAddress());
            stmt.executeUpdate();


        }

    }

    public Worker getWorker(Integer workerId) throws SQLException {
        Worker result = new Worker();


        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_WORKER)
        ) {
            stmt.setInt(1, workerId);
            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
                Worker worker = new Worker();
                worker.setWorkerId(rs.getInt("worker_id"));
                worker.setWorkerSalary(rs.getInt("worker_salary"));
                worker.setWorkerName(rs.getString("worker_name"));
                worker.setWorkerSurname(rs.getString("worker_surname"));
                worker.setWorkerPhone(rs.getString("worker_phone"));


                result = worker;
            }

        }
        return result;

    }

    public Order getOrder(Integer orderId) throws SQLException {
        Order result = new Order();


        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_ORDER)
        ) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
                Order order = new Order();
                Worker w = getWorker(rs.getInt("order_worker"));
                Service service = getService(rs.getInt("order_service"));
                Customer c = getCustomer(rs.getInt("order_customer"));
                order.setOrderId(rs.getInt("order_id"));
                order.setOrderWorker(w);
                order.setOrderCustomer(c);
                order.setOrderService(service);
                order.setOrderDuration(rs.getInt("order_duration"));
                order.setOrderPrice(rs.getInt("order_price"));


                result = order;
            }

        }
        return result;

    }


    public Service getService(Integer serviceId) throws SQLException {
        Service result = new Service();


        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_SERVICE)
        ) {
            stmt.setInt(1, serviceId);
            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
                Service service = new Service();
                service.setServiceId(rs.getInt("service_id"));
                ServiceType type = ServiceType.values()[rs.getInt("service_s_type")-1];
                service.setServiceType(type);
                service.setServicePrice(rs.getInt("service_price"));


                result = service;
            }

        }
        return result;

    }


    public void saveWorker(Worker worker) throws SQLException {


        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_WORKER)
        ) {
            stmt.setInt(1, worker.getWorkerId());
            stmt.setInt(2, worker.getWorkerSalary());
            stmt.setString(3, worker.getWorkerName());
            stmt.setString(4, worker.getWorkerSurname());
            stmt.setString(5, worker.getWorkerPhone());
            stmt.executeUpdate();


        }

    }

    public void saveOrder(Order order) throws SQLException {


        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_ORDER)
        ) {
            order.generateOrderPrice();
            stmt.setInt(1, order.getOrderCustomer().getCustomerId());
            stmt.setInt(2, order.getOrderWorker().getWorkerId());
            stmt.setInt(3, order.getOrderService().getServiceType().ordinal()+1);
            stmt.setInt(4, order.getOrderDuration());
            stmt.setInt(5, order.getOrderPrice());
            stmt.executeUpdate();




        }

    }

    public List<Customer> getCustomerList() throws DaoException {
        List<Customer> result = new ArrayList<>();


        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()
        ) {

            ResultSet rs = stmt.executeQuery(SELECT_CUSTOMER_LIST);


            while (rs.next()) {
                Customer customer= new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setCustomerFullName(rs.getString("customer_fullname"));
                customer.setCustomerPhone(rs.getString("customer_phone"));
                customer.setCustomerAddress(rs.getString("customer_address"));
                result.add(customer);
            }




        } catch (SQLException ex) {
            ex.printStackTrace();

        }


        return result;
    }


    public List<Order> getOrderList() throws DaoException {
        List<Order> result = new ArrayList<>();


        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()
        ) {

            ResultSet rs = stmt.executeQuery(SELECT_ORDER_LIST);


            while (rs.next()) {
                Order order = new Order();
                Worker w = getWorker(rs.getInt("order_worker"));
                Service service = getService(rs.getInt("order_service"));
                Customer c = getCustomer(rs.getInt("order_customer"));
                order.setOrderId(rs.getInt("order_id"));
                order.setOrderWorker(w);
                order.setOrderCustomer(c);
                order.setOrderService(service);
                order.setOrderDuration(rs.getInt("order_duration"));
                order.setOrderPrice(rs.getInt("order_price"));
                result.add(order);
            }




        } catch (SQLException ex) {
            ex.printStackTrace();

        }


        return result;
    }


    public List<Order> getOrderListDesc() throws DaoException {
        List<Order> result = new ArrayList<>();


        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()
        ) {

            ResultSet rs = stmt.executeQuery(SELECT_ORDER_LIST_DESC);


            while (rs.next()) {
                Order order = new Order();
                Worker w = getWorker(rs.getInt("order_worker"));
                Service service = getService(rs.getInt("order_service"));
                Customer c = getCustomer(rs.getInt("order_customer"));
                order.setOrderId(rs.getInt("order_id"));
                order.setOrderWorker(w);
                order.setOrderCustomer(c);
                order.setOrderService(service);
                order.setOrderDuration(rs.getInt("order_duration"));
                order.setOrderPrice(rs.getInt("order_price"));
                result.add(order);
            }




        } catch (SQLException ex) {
            ex.printStackTrace();

        }


        return result;
    }



//    insert_procedure(order_id, product_id,quantituy)
//
//    public List<Service> getProductTable() throws DaoException {
//        List<Service> result = new ArrayList<>();
//
//
//        try (Connection connection = getConnection();
//             Statement stmt = connection.createStatement()
//        ) {
//
//            ResultSet rs = stmt.executeQuery(SELECT_PRODUCT_TABLE);
//            while (rs.next()) {
//                Service product = new Service();
//                product.setProductId(rs.getInt("product_id"));
//                product.setProductName(rs.getString("product_name"));
//                product.setProductPrice(rs.getInt("product_price"));
//                product.setProductWeight(rs.getInt("product_weight"));
//                ServiceType category = ServiceType.values()[rs.getInt("product_category") - 1];
//                product.setProductCategory(category);
//                Integer providerId = rs.getInt("product_provider");
//                Provider provider = getProvider(providerId);
////                System.out.println(provider);
//                product.setProductProvider(provider);
//                result.add(product);
//
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//
//        }
//
//
//        return result;
//    }
//
//    public List<Service> getOrderedPTASC() throws DaoException {
//        List<Service> result = new ArrayList<>();
//
//
//        try (Connection connection = getConnection();
//             Statement stmt = connection.createStatement()
//        ) {
//
//            ResultSet rs = stmt.executeQuery(SELECT_PRODUCT_ORDERASC);
//            while (rs.next()) {
//                Service product = new Service();
//                product.setProductId(rs.getInt("product_id"));
//                product.setProductName(rs.getString("product_name"));
//                product.setProductPrice(rs.getInt("product_price"));
//                product.setProductWeight(rs.getInt("product_weight"));
//                ServiceType category = ServiceType.values()[rs.getInt("product_category") - 1];
//                product.setProductCategory(category);
//                Integer providerId = rs.getInt("product_provider");
//                Provider provider = getProvider(providerId);
////                System.out.println(provider);
//                product.setProductProvider(provider);
//                result.add(product);
//
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//
//        }
//
//
//        return result;
//    }
//
//    public List<Service> getOrderedPTDESC() throws DaoException {
//        List<Service> result = new ArrayList<>();
//
//
//        try (Connection connection = getConnection();
//             Statement stmt = connection.createStatement()
//        ) {
//
//            ResultSet rs = stmt.executeQuery(SELECT_PRODUCT_ORDERDESC);
//            while (rs.next()) {
//                Service product = new Service();
//                product.setProductId(rs.getInt("product_id"));
//                product.setProductName(rs.getString("product_name"));
//                product.setProductPrice(rs.getInt("product_price"));
//                product.setProductWeight(rs.getInt("product_weight"));
//                ServiceType category = ServiceType.values()[rs.getInt("product_category") - 1];
//                product.setProductCategory(category);
//                Integer providerId = rs.getInt("product_provider");
//                Provider provider = getProvider(providerId);
////                System.out.println(provider);
//                product.setProductProvider(provider);
//                result.add(product);
//
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//
//        }
//
//
//        return result;
//    }
//
//
//    public List<Service> getOrderedPTProvider() throws DaoException {
//        List<Service> result = new ArrayList<>();
//
//
//        try (Connection connection = getConnection();

//             Statement stmt = connection.createStatement()
//        ) {
//
//            ResultSet rs = stmt.executeQuery(SELECT_PRODUCT_ORDERPROVIDER);
//            while (rs.next()) {
//                Service product = new Service();
//                product.setProductId(rs.getInt("product_id"));
//                product.setProductName(rs.getString("product_name"));
//                product.setProductPrice(rs.getInt("product_price"));
//                product.setProductWeight(rs.getInt("product_weight"));
//                ServiceType category = ServiceType.values()[rs.getInt("product_category") - 1];
//                product.setProductCategory(category);
//                Integer providerId = rs.getInt("product_provider");
//                Provider provider = getProvider(providerId);
////                System.out.println(provider);
//                product.setProductProvider(provider);
//                result.add(product);
//
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//
//        }
//
//
//        return result;
//    }
//
//
//    public List<Service> findProduct(String pattern) throws DaoException {
//        List<Service> result = new LinkedList<>();
//
//        try (Connection con = getConnection();
//             PreparedStatement stmt = con.prepareStatement(GET_PRODUCT_BY_NAME)) {
//
//            stmt.setString(1, "%" + pattern + "%");
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Service product = new Service();
//                product.setProductId(rs.getInt("product_id"));
//                product.setProductName(rs.getString("product_name"));
//                product.setProductPrice(rs.getInt("product_price"));
//                product.setProductWeight(rs.getInt("product_weight"));
//                ServiceType category = ServiceType.values()[rs.getInt("product_category") - 1];
//                product.setProductCategory(category);
//                Integer providerId = rs.getInt("product_provider");
//                Provider provider = getProvider(providerId);
//                product.setProductProvider(provider);
//
//                result.add(product);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        if (result.isEmpty()) throw new DaoException();
//
//        return result;
//    }
//
//    public Provider getProvider(int providerId) throws DaoException {
//        Provider result = new Provider();
//
//
//        try (Connection connection = getConnection();
//             PreparedStatement stmt = connection.prepareStatement(SELECT_PROVIDER)
//        ) {
//
//            stmt.setInt(1, providerId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                result.setProviderId(rs.getInt("provider_id"));
//                result.setProviderName(rs.getString("provider_name"));
//                result.setProviderPhone(rs.getString("provider_phone"));
//                result.setProviderEmail(rs.getString("provider_email"));
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//
//        }
//
//        return result;
//    }
//
//
//    public List<Worker> getWorkerList() throws SQLException {
//        List<Worker> result = new ArrayList<>();
//
//
//        try (Connection connection = getConnection();
//             Statement stmt = connection.createStatement()
//        ) {
//
//            ResultSet rs = stmt.executeQuery(SELECT_WORKER_LIST);
//
//
//            while (rs.next()) {
//                Worker worker = new Worker();
//                worker.setWorkerId(rs.getInt("worker_id"));
//                worker.setWorkerName(rs.getString("worker_name"));
//                worker.setWorkerSalary(rs.getInt("worker_salary"));
//                worker.setWorkerSurname(rs.getString("worker_surname"));
//                worker.setWorkerPhone(rs.getString("worker_phone"));
//                worker.setWorkerEmail(rs.getString("worker_email"));
//
//
//                result.add(worker);
//            }
//
//        }
//        return result;
//
//    }
//
//
//
//
//
//
//
//    public List<Customer> getCustomerList() throws SQLException {
//        List<Customer> result = new ArrayList<>();
//
//
//        try (Connection connection = getConnection();
//             Statement stmt = connection.createStatement()
//        ) {
//
//            ResultSet rs = stmt.executeQuery(SELECT_CUSTOMER_LIST);
//
//
//            while (rs.next()) {
//                Customer customer = new Customer();
//                customer.setCustomerId(rs.getInt("customer_id"));
//                customer.setCustomerFullName(rs.getString("customer_name"));
//                customer.setCustomerSurname(rs.getString("customer_surname"));
//                customer.setCustomerLogin(rs.getString("customer_login"));
//                customer.setCustomerPassword(rs.getString("customer_password"));
//                customer.setCustomerEmail(rs.getString("customer_email"));
//                customer.setCustomerPhone(rs.getString("customer_phone"));
//                customer.setCustomerAddress(rs.getString("customer_address"));
//
//
//                result.add(customer);
//            }
//
//        }
//        return result;
//
//    }
//
//    public Service getProduct(Integer productId) throws DaoException {
//        Service result = new Service();
//
//        try (Connection connection = getConnection();
//             PreparedStatement stmt = connection.prepareStatement(SELECT_PRODUCT)
//        ) {
//            stmt.setInt(1, productId);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Service product = new Service();
//                product.setProductId(rs.getInt("product_id"));
//                product.setProductName(rs.getString("product_name"));
//                product.setProductPrice(rs.getInt("product_price"));
//                ServiceType category = ServiceType.values()[rs.getInt("product_category") - 1];
//                product.setProductCategory(category);
//                Integer providerId = rs.getInt("product_provider");
//                Provider provider = getProvider(providerId);
//                product.setProductProvider(provider);
//                product.setProductQuantity(rs.getInt("product_quantity"));
//                product.setProductWeight(rs.getInt("product_weight"));
//                result = product;
//
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//
//        }
//
//
//        return result;
//    }
//
//
//
//
//
//
//    private void saveOrderToBuyList(Service pr, Map<Service, Integer> quantityMap, int orderId) throws SQLException {
//
//        try (Connection connection = getConnection();
//             PreparedStatement stmt = connection.prepareStatement(INSERT_ORDER_PROCEDURE)
//        ) {
//            stmt.setInt(1, orderId);
//            stmt.setInt(3, quantityMap.get(pr));
//            stmt.setInt(2, pr.getProductId());
//            stmt.executeUpdate();
//
//        }
//
//
//    }
//
//    private void setSetTotalOrderPrice(Integer orderId, Integer orderPrice) throws SQLException {
//        try (Connection connection = getConnection();
//             PreparedStatement stmt = connection.prepareStatement(SET_TOTAL_ORDER_PRICE)
//        ) {
//            stmt.setInt(1, orderPrice);
//            stmt.setInt(2, orderId);
//            stmt.executeUpdate();
//
//        }
//
//
//    }
//
//    public void saveProduct(Service product) throws SQLException {
//
//
//        try (Connection connection = getConnection();
//             PreparedStatement stmt = connection.prepareStatement(INSERT_PRODUCT)
//        ) {
//            stmt.setString(1, product.getProductName());
//            stmt.setInt(2, product.getProductPrice());
//            stmt.setInt(3, product.getProductWeight());
//            stmt.setInt(4, product.getProductCategory().ordinal() + 1);
//            stmt.setInt(5, product.getProductProvider().getProviderId());
//            stmt.setInt(6, product.getProductQuantity());
//            stmt.executeUpdate();
//
//
//        }
//
//    }
}






