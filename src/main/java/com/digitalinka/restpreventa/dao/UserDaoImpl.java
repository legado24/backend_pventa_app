package com.digitalinka.restpreventa.dao;

import com.digitalinka.restpreventa.UtilDao;
import com.digitalinka.restpreventa.model.*;
import com.digitalinka.restpreventa.model.response.StatusResponse;
import com.digitalinka.restpreventa.model.response.UserResponse;
import oracle.jdbc.driver.OracleConnection;
import oracle.jdbc.OracleTypes;
import oracle.net.aso.s;
import org.apache.commons.dbcp2.DelegatingConnection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${nombrePaquete}")
    protected String nombrePaquete;

    @Transactional(readOnly = false)
    @Override
    public UserResponse loginByUserEmail(String userEmail, String password) {
        return sessionFactory.getCurrentSession().doReturningWork(connection -> {
            UserResponse userResponse = new UserResponse();
            StatusResponse statusResponse = new StatusResponse();
            try {
                CallableStatement cs = connection.prepareCall(" { call " + nombrePaquete + ".LOGIN_USER_EMAIL(?,?,?,?,?)}");
                cs.setString(1, userEmail);
                cs.setString(2, password);
                cs.registerOutParameter(3, OracleTypes.NUMBER);
                cs.registerOutParameter(4, OracleTypes.VARCHAR);
                cs.registerOutParameter(5, OracleTypes.CURSOR);
                cs.execute();
                statusResponse.setStatusCode(cs.getInt(3));
                statusResponse.setStatusText(cs.getString(4));
                ResultSet rs = (ResultSet) cs.getObject(5);
                User user = new User();
                if (rs != null) {
                    while (rs.next()) {
                        user.setUserEmail(rs.getString(1));
                        user.setFullName(rs.getString(2));
                    }
                    rs.close();
                    cs.close();
                }
                userResponse.setUser(user);
                userResponse.setStatus(statusResponse);
            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                userResponse.setStatus(statusResponse);
            }
            return userResponse;
        });

    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse userByDni(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            UserResponse userResponse = new UserResponse();
            StatusResponse statusResponse = new StatusResponse();
            try {
                DelegatingConnection del = new DelegatingConnection(conn);
                OracleConnection connection = (OracleConnection) del.getInnermostDelegate();
                Array arrayStrinb = connection.createARRAY("LISTPARAMETR0STRING", UtilDao.getStructsString("PARAMETR0STRING", connection, parametrosString));
                CallableStatement cs = connection.prepareCall("call " + nombrePaquete + ".SP_LIU_USER(?,?,?,?)");
                cs.setArray(1, arrayStrinb);
                cs.registerOutParameter(2, OracleTypes.NUMBER);
                cs.registerOutParameter(3, OracleTypes.VARCHAR);
                cs.registerOutParameter(4, OracleTypes.CURSOR);
                cs.execute();
                statusResponse.setStatusCode(cs.getInt(2));
                statusResponse.setStatusText(cs.getString(3));
                ResultSet rs = (ResultSet) cs.getObject(4);
                User user = new User();
                if (rs != null) {
                    while (rs.next()) {
                        user.setUsername(rs.getString("USUARIO"));
                        user.setFullName(rs.getString("NOMBRE"));
                        user.setEmail(rs.getString("EMAIL"));
                    }
                    rs.close();
                    cs.close();
                }
                userResponse.setUser(user);
                userResponse.setStatus(statusResponse);
                return userResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                userResponse.setStatus(statusResponse);
                return userResponse;

            }
        });

    }

    @Transactional(readOnly = false)
    @Override
    public StatusResponse updateUserLogin(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
             StatusResponse statusResponse = new StatusResponse();
            try {
                DelegatingConnection del = new DelegatingConnection(conn);
                OracleConnection connection = (OracleConnection) del.getInnermostDelegate();
                Array arrayStrinb = connection.createARRAY("LISTPARAMETR0STRING", UtilDao.getStructsString("PARAMETR0STRING", connection, parametrosString));
                CallableStatement cs = connection.prepareCall("call " + nombrePaquete + ".SP_LIU_USER(?,?,?,?)");
                cs.setArray(1, arrayStrinb);
                cs.registerOutParameter(2, OracleTypes.NUMBER);
                cs.registerOutParameter(3, OracleTypes.VARCHAR);
                cs.registerOutParameter(4, OracleTypes.CURSOR);
                cs.execute();
                statusResponse.setStatusCode(cs.getInt(2));
                statusResponse.setStatusText(cs.getString(3));

                return statusResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                return statusResponse;

            }
        });
    }
}
