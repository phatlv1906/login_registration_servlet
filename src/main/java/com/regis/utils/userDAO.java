package com.regis.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import com.regis.model.User;

public class userDAO {
	public static final Connection conn = JDBCUtil.getConnection();
	public void saveUser(User user, HttpServletRequest request) {
		 
		try {
			String sql = "Insert into users(username, fullname ,password, email, phone) values (?,?,?,?,?)";
			
			PreparedStatement pr = conn.prepareStatement(sql);
			pr.setString(1, user.getUname());
			pr.setString(2, user.getName());
			pr.setString(3, user.getPass());
			pr.setString(4, user.getEmail());
			pr.setString(5, user.getPhone());
			int rowsAffected = pr.executeUpdate();
			if (rowsAffected > 0) {
				request.setAttribute("status", "success");
            } else {
            	request.setAttribute("status", "failed");
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean loginUser(String uname, String pass) {
		boolean flag = false;
		try {
			String sql = "Select * from users where username = ? and password = ?";
			PreparedStatement pr = conn.prepareStatement(sql);
			pr.setString(1, uname);
			pr.setString(2, pass);
			ResultSet rs = pr.executeQuery();
			if(rs.next()) {
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
