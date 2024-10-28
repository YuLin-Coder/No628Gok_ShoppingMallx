package cn.gok.frontstageManagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.gok.frontstageManagement.entity.User;
import cn.gok.util.JDBCUtil;

public class UserService {
	
	/**
	 * 注册用户时使用，添加一条用户数据
	 * @param user
	 * @return  添加成功，返回1，否则返回0
	 */
	public int addUser(User user) {
		String sql="insert into t_user(u_name,u_password,u_role,u_phone,u_email) values(?,?,?,?,?)";
		int count=JDBCUtil.update(sql,user.getUserName(),user.getPassword(),user.getRole(),user.getPhone(),user.getEmail());
		if(count != 0) {
			return 1;
		}
		return 0;
	}
	
	
	/**
	 * 检查用户名是否重复，
	 * @param userName
	 * @return  根据用户查出用否有对应的id存在，如果存在，
	 * 			则返回相应的id值 ，否则返回0
	 */
	public int checkUserNameRepeat(String userName) {
		String sql = "select id from t_user where u_name =?";
		int i = JDBCUtil.queryForInt(sql, userName);
		if(i != 0) {
			return i;
		}
		// TODO 自动生成的方法存根
		return 0;
	}
	

	/**
	 * 用登录时使用
	 * @param userName
	 * @param password
	 * @return   根据用户名和密码查询到此用户信息， 
	 * 			  如果能够查询出，则将查询到信息封装到一个User对象中，并返回，否则返回null
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
	 * 修改用户信息
	 * @param user  参数user中封装着前台页面传过来的所有要修改的数据信息
	 * @return       根据id修改对应的用户数据， 成功返回1，否则返回0
	 */
	public int updateUserById(User user) {
		String sql="update t_user set u_name=?,u_password=?,u_role=?,u_phone=?,u_email=? where id=?";
		int count=JDBCUtil.update(sql, user.getUserName(),user.getPassword(),user.getRole(),user.getPhone(),user.getEmail(),user.getId());
		if(count != 0) {
			return 1;
		}
		return 0;
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
		ResultSet rs=JDBCUtil.executeQuery(sql, id);
			try{
				while(rs.next()){
					String password = rs.getString("u_password");
					System.out.println(password);
					return password;	
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				JDBCUtil.close(con, ps, rs);
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
