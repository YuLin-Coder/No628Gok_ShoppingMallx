/**
 * <p>Title: JDBCUtil.java</p>  
 * <p>Description: </p>   
 * <p>Company: www.goktech.cn</p>  
 * @author chenfan  
 * @version 1.0
 */
package cn.gok.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**  
 * @ClassName: JDBCUtil  
 * @Description: 简单封装一个jdbc的工具类
 * @author: chenfan  
 * @date: 2018年12月24日  
 *  
 */
public class JDBCUtil {
	/**
	 * 驱动名字
	 */
	public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	/**
	 * 连接的地址
	 */
	public static final String URL = "jdbc:mysql://localhost:3306/gok_shop?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
	/**
	 * 用户名
	 */
	public static final String USERNAME = "root";
	/**
	 * 密码
	 */
	public static final String PASSWORD = "yong0614";
	
	
	/**
	 * 构造方法私有化
	 */
	private JDBCUtil(){}
	
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection(){
		try {
			//加载驱动
			Class.forName(DRIVER_CLASS_NAME);	
			//2.获取连接
			return DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 关闭资源
	 * @param con
	 * @param st
	 * @param rs
	 */
	public static void close(Connection con,Statement ps,ResultSet rs){
		try {
			try{
				if(rs!=null){
					rs.close();
					rs = null;
				}
			}finally{
				try{
					if(ps!=null){
						ps.close();
						ps = null;
					}
				}finally{
					if(con!=null){
						con.close();
						con = null;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询，返回int
	 * @param sql
	 * @param args
	 * @return
	 */
	public static int queryForInt(String sql ,Object ... args){
		Connection con  = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			for (int i = 0;args!=null && i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
			rs = ps.executeQuery();
			while(rs.next()){
				//返回第一行第一列
				return rs.getInt(1);
			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(con,ps,rs);
		}
		return 0;
	}
	
	/**
	 * <p>增删改操作</p>
	 * <p>update(sql)</p>
	 * <p>update(sql,name,id)</p>
	 * <p>update(sql,new Object[]{name,id})</p>
	 * @param sql 需要执行的SQL语句
	 * @param args 动态参数，可以当作数组数组使用
	 * @return 受影响的行数；如果没有修改、删除、新增数据，返回0
	 */
	public static int update(String sql,Object ... args){
		Connection con = null;
		PreparedStatement ps = null;
		try{
			//获取连接
			con = getConnection();
			//创建statement对象
			ps = con.prepareStatement(sql);
			//设置参数
			for (int i = 0;args!=null && i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
			//执行SQL语句
			return ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps,null);
		}
		return 0;
	}
	
	public static ResultSet executeQuery(String sql,Object ...obj){
		Connection con  = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = getConnection();
			ps=con.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setObject(i+1, obj[i]);
			}
			rs=ps.executeQuery();
			return rs;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
}
