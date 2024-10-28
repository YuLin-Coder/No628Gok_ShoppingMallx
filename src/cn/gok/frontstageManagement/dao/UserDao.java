package cn.gok.frontstageManagement.dao;

import cn.gok.frontstageManagement.entity.User;

/**
* @author CJY
* @version 创建时间：2019年7月22日 下午1:08:11
* @ClassName 类名称
* @Description 类描述
*/
public interface UserDao {
	/**
	 * 注册用户时使用，添加一条用户数据
	 * @param user
	 * @return  添加成功，返回1，否则返回0
	 */
	public int addUser(User user);
	
	/**
	 * 检查用户名是否重复，
	 * @param userName
	 * @return  根据用户查出用否有对应的id存在，如果存在，
	 * 			则返回相应的id值 ，否则返回0
	 */
	public int checkUserNameRepeat(String userName);
	
	/**
	 * 用登录时使用
	 * @param userName
	 * @param password
	 * @return   根据用户名和密码查询到此用户信息， 
	 * 			  如果能够查询出，则将查询到信息封装到一个User对象中，并返回，否则返回null
	 */
	public User getUserByNameAndPassword(String userName,String password);
	
	/**
	 * 修改用户信息
	 * @param user  参数user中封装着前台页面传过来的所有要修改的数据信息
	 * @return  根据id修改对应的用户数据， 成功返回1，否则返回0
	 */
	public int updateUserById(User user);
	
	/**
	 * 根据用户id修改用户的密码
	 * @param id
	 * @param password
	 * @return  修改成功返回1，否则返回0
	 */
	public int updatePasswordById(int id,String password);
}
