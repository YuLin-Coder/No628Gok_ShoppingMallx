package cn.gok.backstageManagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.gok.backstageManagement.entity.User;
import cn.gok.util.JDBCUtil;

public class UserService {
	
	
	
	
	/**
	 * 用登录时使用
	 * 	@param userName  用户名
	 * 	@param password  密码
	 * 	@return   根据用户名和密码查询到此用户信息， 
	 * 			    并将查询到基础信息（密码等敏感信息不要）封装到一个User对象中并返回，
	 * 			    如果没有查到，直接返回null
	 */
	public User getUserByNameAndPassword(String userName, String password) {
		Connection con  = null;
		PreparedStatement ps = null;
		String sql = "select * from t_user where u_name =? and u_password=?";
		int i = JDBCUtil.queryForInt(sql, userName, password);
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, userName, password);
			try{
				while(rs.next()){
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUserName(rs.getString("u_name"));
					user.setPassword(rs.getString("u_password"));
					user.setRole(rs.getString("u_role"));
					user.setPhone(rs.getString("u_phone"));
					user.setEmail(rs.getString("u_email"));
					System.out.println(rs.getString("u_role"));
					return user;	
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				JDBCUtil.close(con, ps, rs);
			}
		}
		// TODO 自动生成的方法存根
		return null;
	}
	
	/**
	 *管理人员登录时调用： 
	 * 	根据用户名获取用户的角色信息
	 * 	@param userName
	 * 	@return  返回用户的角色信息
	 */
	public String getUserRoleByName(String userName) {
		Connection con  = null;
		PreparedStatement ps = null;
		String sql = "select * from t_user where u_name =?";
		int i = JDBCUtil.queryForInt(sql, userName);
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, userName);
			try{
				while(rs.next()){
					String role= rs.getString("u_role");
					return role;	
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				JDBCUtil.close(con, ps, rs);
			}
		}
		// TODO 自动生成的方法存根
		return null;
	}
	
	
	/**
	 *用户修改密码使用， 用户输入的原始密码 必须是对的，
	 *才可以进行密码修改
	 * 	@param id 用户id
	 * 	@return  返回查到的此用户的密码
	 */
	public String getPasswordById(int id) {
		Connection con  = null;
		PreparedStatement ps = null;
		String sql = "select * from t_user where id =?";
		int i = JDBCUtil.queryForInt(sql, id);
		
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, id);
			try{
				while(rs.next()){
					String password = rs.getString("u_password");
					return password;	
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				JDBCUtil.close(con, ps, rs);
			}
		}
		// TODO 自动生成的方法存根
		return null;
	}
	
	
	/**
	 *修改密码时使用： 
	 * 	根据用户id修改用户的密码
	 * 	@param id           	用户id
	 * 	@param newPassword  	前端 传来的新密码
	 * 	@return  				修改成功返回1，否则返回0
	 */
	public int updatePasswordById(int id, String newPassword) {
		String sql = "update t_user set u_password=? where id=?";
		int i = JDBCUtil.update(sql, newPassword, id);
		if(i != 0) {
			return 1;
		}
	// TODO 自动生成的方法存根
		return 0;
	}
	
	
	
	
	
}
