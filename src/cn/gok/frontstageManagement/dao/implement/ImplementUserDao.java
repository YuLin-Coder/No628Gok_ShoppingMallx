package cn.gok.frontstageManagement.dao.implement;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.gok.backstageManagement.entity.Goods;
import cn.gok.frontstageManagement.dao.UserDao;
import cn.gok.frontstageManagement.entity.User;
import cn.gok.util.JDBCUtil;

public class ImplementUserDao implements UserDao{

	@Override
	public int addUser(User user) {
		String sql="insert into t_user(id,u_name,u_password,u_role,u_phone,u_email) values(?,?,?,?,?,?)";
		int count=JDBCUtil.update(sql, user.getId(),user.getUserName(),user.getPassword(),user.getRole(),user.getPhone(),user.getEmail());
		if(count != 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int checkUserNameRepeat(String userName) {
		String sql = "select * from t_user where u_name =?";
		int i = JDBCUtil.queryForInt(sql, userName);
		
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, userName);
			try{
				while(rs.next()){
					User user = new User();
					return user.getId();	
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		// TODO 自动生成的方法存根
		return 0;
	}

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
	public int updateUserById(User user) {
		String sql="update t_user set u_name=?,u_password=?,u_role=?,u_phone=?,u_email=? where id=?";
		int count=JDBCUtil.update(sql, user.getUserName(),user.getPassword(),user.getRole(),user.getPhone(),user.getEmail(),user.getId());
		if(count != 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int updatePasswordById(int id, String password) {
		String sql = "select * from t_user where id =?";
		int i = JDBCUtil.queryForInt(sql, id);
		
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, id);
			try{
				while(rs.next()){
					User user = new User();
					String oldPassword = user.getPassword();
					
					if(oldPassword.equals(password)) {
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
