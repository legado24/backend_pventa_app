package com.digitalinka.restpreventa.dao;


import com.digitalinka.restpreventa.model.Customer;
import com.digitalinka.restpreventa.model.response.CustomerListResponse;
import com.digitalinka.restpreventa.model.response.StatusResponse;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDaoImpl  implements CustomerDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${nombrePaquete}")
    protected String nombrePaquete;

    @Transactional(readOnly = true)
    @Override
    public CustomerListResponse getClienteList(String usuario) {
            return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<CustomerListResponse>() {
                @Override
                public CustomerListResponse execute(Connection connection) throws SQLException {
                    CustomerListResponse customerListResponse = new CustomerListResponse();
                    StatusResponse statusResponse=new StatusResponse();
                    try {
                        CallableStatement cs = connection.prepareCall(" { call "+nombrePaquete+".CLIENTES_BY_DIAV2(?,?,?)}");
                        cs.setString(
                                1,usuario);
                        cs.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                        cs.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                        cs.execute();
                     //   String ignoresec=cs.getString(2);
                        ResultSet rs = (ResultSet) cs.getObject(3);
                        List<Customer> customers=new ArrayList<>();
                        //int i=0;
                        if (rs!= null) {
                            while (rs.next()) {

                                Customer customer=new Customer();
                                customer.setCode(rs.getString(1));
                                customer.setDescription(rs.getString(2));
                                customer.setAddress(rs.getString(3));
                                customer.setStatus(rs.getString(12));


                                customers.add(customer);

                            }
                            rs.close();
                        }


                        statusResponse.setStatusCode(1);
                        statusResponse.setStatusText("OK");
                        customerListResponse.setCustomers(customers);
                        customerListResponse.setStatus(statusResponse);
                    } catch (Exception e) {
                        statusResponse.setStatusCode(-1);
                        statusResponse.setStatusText(e.getMessage());
                        customerListResponse.setStatus(statusResponse);

                    }
                    return customerListResponse;
                }

            });





    }
}
