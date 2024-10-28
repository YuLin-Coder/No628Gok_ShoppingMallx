package cn.gok.backstageManagement.dao;

import cn.gok.backstageManagement.entity.User;

/**
* @author CJY
* @version 创建时间：2019年7月22日 下午1:04:27
* @ClassName 类名称
* @Description 类描述
*/
public interface UserDao {
	/**
	 * 用登录时使用
	 * 	@param userName  用户名
	 * 	@param password  密码
	 * 	@return   根据用户名和密码查询到此用户信息， 
	 * 			    并将查询到基础信息（密码等敏感信息不要）封装到一个User对象中并返回，
	 * 			    如果没有查到，直接返回null
	 */
	public User getUserByNameAndPassword(String userName,String password);
	
	/**
	 *管理人员登录时调用： 
	 * 	根据用户名获取用户的角色信息
	 * 	@param userName
	 * 	@return  返回用户的角色信息
	 */
	public String getUserRoleByName(String userName);
	
	/**
	 *用户修改密码使用， 用户输入的原始密码 必须是对的，才可以进行密码修改
	 * 	@param id 用户id
	 * 	@return  返回查到的此用户的密码
	 */
	public String getPasswordById(int id);
	
	/**
	 *修改密码时使用： 
	 * 	根据用户id修改用户的密码
	 * 	@param id           	用户id
	 * 	@param newPassword  	前端 传来的新密码
	 * 	@return  修改成功返回1，否则返回0
	 */
	public int updatePasswordById(int id,String newPassword);
}
	
