package by.beregeiko.tuningshop.dao.impl.jdbc;

import java.sql.Connection;

/**
 * Created by Think on 23.12.2016.
 */
public class JdbcUtils {
    public static void closeQuietly(AutoCloseable resourse){
        if(resourse != null){
            try{
                resourse.close();
            } catch(Exception e) {
                //NOP
            }
        }
    }

    public static void rollbackQuietly(Connection connection){
        if(connection != null){
            try{
                connection.rollback();
            } catch(Exception e) {
                //NOP
            }
        }
    }
    public static void closeQuietly(AutoCloseable... resourses){
        for(AutoCloseable resourse : resourses){
            closeQuietly(resourse);
        }
    }
}
