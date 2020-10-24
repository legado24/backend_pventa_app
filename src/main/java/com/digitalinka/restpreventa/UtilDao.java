package com.digitalinka.restpreventa;

import oracle.jdbc.driver.OracleConnection;

import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class UtilDao {
    public static Struct[] getStructsString(String structName, OracleConnection connection,
                                              List<Object[]> parametrosString) {
        try {
            parametrosString = parametrosString != null ? parametrosString : new ArrayList<>();

            Struct[] structs = new Struct[parametrosString.size()];
            int idx = 0;
            for (Object[] param : parametrosString) {
                structs[idx] = connection.createStruct(structName,
                        new Object[] { param[0].toString(), param[1].toString() });
                idx++;
            }

            return structs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
