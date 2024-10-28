package cn.gok.backstageManagement.dao.implement;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.gok.backstageManagement.dao.UserDao;
import cn.gok.backstageManagement.entity.User;
import cn.gok.util.JDBCUtil;

public class ImplementUserDao implements UserDao {

	
	
	@Override
	public User getUserByNameAndPassword(String userName, String password) {
		String sql = "select * from t_user where u_name =? and u_password=?";
		int i = JDBCUtil.queryForInt(sql, userName, password);
		
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, userName, password);
			try{
				while(rs.next()){
					User user = new User();
					user.setUserName(rs.getString("userName"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
					user.setPhone(rs.getString("phone"));
					user.setEmail(rs.getString("email"));
					
					return user;	
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getUserRoleByName(String userName) {
		String sql = "select * from t_user where u_name =?";
		int i = JDBCUtil.queryForInt(sql, userName);
		
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, userName);
			try{
				while(rs.next()){
					User user = new User();
					String role= user.getRole();
					
					return role;	
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getPasswordById(int id) {
		String sql = "select * from t_user where id =?";
		int i = JDBCUtil.queryForInt(sql, id);
		
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, id);
			try{
				while(rs.next()){
					User user = new User();
					String password = user.getPassword();
					
					return password;	
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public int updatePasswordById(int id, String newPassword) {
		String sql = "select * from t_user where id =?";
		int i = JDBCUtil.queryForInt(sql, id);
		
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, id);
			try{
				while(rs.next()){
					User user = new User();
					String password = user.getPassword();
					
					if(password.equals(newPassword)) {
						return 1;
					}	
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		// TODO 自动生成的方法存根
		return 0;
	}

}
