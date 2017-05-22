package com.ubl.jwp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <pre>
 *  com.ubl.jwp.service.UserService
 * </pre>
 *
 * @author edwin < edwinkun at gmail dot com >
 * Apr 17, 2017 1:40:40 PM
 *
 */
public class DosenService {

    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public String[][] getDosens() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/kampus?", "root", "");
            preparedStatement = connect.prepareStatement("select count(1) from dosen");
            resultSet = preparedStatement.executeQuery();
            
            int hasil = 0;
            if(resultSet.next()){
                hasil = resultSet.getInt(1);
            }
            
            String[][] data = new String[hasil][4];
            
            preparedStatement = connect.prepareStatement("select * from dosen");
            resultSet = preparedStatement.executeQuery();
            
            int i = 0;
            while(resultSet.next()){
                data[i][0] = resultSet.getString("iddosen");
                data[i][1] = resultSet.getString("namadosen");
                
                i++;
            }
            
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(connect != null)
                    connect.close();    
                if(preparedStatement != null)
                    preparedStatement.close();    
                if(resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return new String[][]{};
    }
    
    public String[] getDosen(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/kampus?", "root", "");
            
            String[] data = new String[4];
            preparedStatement = connect.prepareStatement("select * from dosen where iddosen = ?");
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                data[0] = resultSet.getString("iddosen");
                data[1] = resultSet.getString("namadosen");
            }
            
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(connect != null)
                    connect.close();    
                if(preparedStatement != null)
                    preparedStatement.close();    
                if(resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return new String[]{};
    }
    
    public void deleteDosen(String iddosen) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/kampus?", "root", "");
            
            preparedStatement = connect.prepareStatement("delete from dosen where iddosen = ?");
            preparedStatement.setString(1, iddosen);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(connect != null)
                    connect.close();    
                if(preparedStatement != null)
                    preparedStatement.close();    
                if(resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void saveDosen(String iddosen, String namadosen) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/kampus?", "root", "");
            
            preparedStatement = connect.prepareStatement("select count(1) from dosen where iddosen = ?");
            preparedStatement.setString(1, iddosen);
            resultSet = preparedStatement.executeQuery();
            
            resultSet = preparedStatement.executeQuery();
            int hasil = 0;
            if(resultSet.next()){
                hasil = resultSet.getInt(1);
            }
            
            if(hasil == 0) {
                preparedStatement = connect.prepareStatement("insert into dosen values (?, ?)");
                preparedStatement.setString(1, iddosen);
                preparedStatement.setString(2, namadosen);
                preparedStatement.executeUpdate();
            } else {
                preparedStatement = connect.prepareStatement("update dosen set namadosen = ? where iddosen = ?");
                preparedStatement.setString(1, namadosen);
                preparedStatement.setString(2, iddosen);
                preparedStatement.executeUpdate();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(connect != null)
                    connect.close();    
                if(preparedStatement != null)
                    preparedStatement.close();    
                if(resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
