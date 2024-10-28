package cn.gok.backstageManagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.gok.backstageManagement.entity.Orders;
import cn.gok.util.JDBCUtil;

/**
 * 订单的业务处理中
 * @author asus
 *
 */
public class OrdersService {
	/**1
	 * 查询所有订单信息：  
	 * 		将dao中查到的数据返回，
	 *  	控制层会调用此方法，从中拿到数据，并显示到浏览器页面
	 * @return  返回订单集合
	 */
	public List<Orders> getAllOrders() {
		Connection con  = null;
		PreparedStatement ps = null;
		String sql="select * from t_order";
		List<Orders> slist=new ArrayList<>();
		ResultSet rs=JDBCUtil.executeQuery(sql);
		try{
			while(rs.next()){
				Orders order = new Orders();
				order.setId(rs.getInt("id"));
				order.setNumber(rs.getString("o_number"));
				order.setUser(rs.getString("o_user"));
				order.setTime(rs.getString("o_time"));
				order.setName(rs.getString("o_name"));
				order.setSex(rs.getString("o_sex"));
				order.setAddress(rs.getString("o_address"));
				order.setPhone(rs.getString("o_phone"));
				order.setAddress_label(rs.getString("o_address_label"));
				order.setGoods_id(rs.getInt("o_goods_id"));
				order.setGoods_num(rs.getInt("o_goods_num"));
				order.setGoods_status(rs.getInt("o_goods_status"));
				slist.add(order);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		return slist;
	}
	
	/**2(注意！查询范围修改)
	 * 根据类别或者订单名，查出对应类别的订单信息：
	 * @param   parms 封装着 类别和订单名信息 
	 * @return   返回订单集合
	 */
	public List<Orders> getOrdersByName(Map<String, String> parms){
		Connection con  = null;
		PreparedStatement ps = null;
		String name = parms.get("name");
		String sql="select * from t_order where o_user like ?";
		List<Orders> slist=new ArrayList<>();
		String str = "%" + name + "%";
		ResultSet rs=JDBCUtil.executeQuery(sql, str);
		try{
			while(rs.next()){
				Orders order = new Orders();
				order.setId(rs.getInt("id"));
				order.setNumber(rs.getString("o_number"));
				order.setUser(rs.getString("o_user"));
				order.setTime(rs.getString("o_time"));
				order.setName(rs.getString("o_name"));
				order.setSex(rs.getString("o_sex"));
				order.setAddress(rs.getString("o_address"));
				order.setPhone(rs.getString("o_phone"));
				order.setAddress_label(rs.getString("o_address_label"));
				order.setGoods_id(rs.getInt("o_goods_id"));
				order.setGoods_num(rs.getInt("o_goods_num"));
				order.setGoods_status(rs.getInt("o_goods_status"));
				slist.add(order);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		return slist;
	}
	
	
	/**3
	 * 根据id修改订单的信息，但是id 和 创建订单的时间不修改 
	 * @param good 封装着要修改的信息的order对象
	 * @return 如果修改成功返回1，修改失败返回0
	 */
	public int updateOrdersById(Orders order) {
		String sql="update t_order set o_number=?,o_user=?,o_time=?,o_name=?,o_sex=?,o_address=?,o_phone=?,o_address_label=?,o_goods_id=?,o_goods_num=?,o_goods_status=? where id=?";
		int count=JDBCUtil.update(sql,order.getNumber(),order.getUser(),order.getTime(),order.getName(),order.getSex(),order.getAddress(),order.getPhone(),order.getAddress_label(),order.getGoods_id(),order.getGoods_num(),order.getGoods_status(),order.getId());
		if(count != 0) {
			return 1;
		}
		return 0;
	}
	
	
	/**4
	 * 根据订单id获取 订单的数据
	 * @param id
	 * @return 返回具体订单信息
	 */
	public Orders getOrdersInfoById(int id) {
		Connection con  = null;
		PreparedStatement ps = null;
		String sql = "select * from t_order where id =?";
		int i = JDBCUtil.queryForInt(sql, id);
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, id);
			try{
				while(rs.next()){
					Orders order = new Orders();
					order.setId(rs.getInt("id"));
					order.setNumber(rs.getString("o_number"));
					order.setUser(rs.getString("o_user"));
					order.setTime(rs.getString("o_time"));
					order.setName(rs.getString("o_name"));
					order.setSex(rs.getString("o_sex"));
					order.setAddress(rs.getString("o_address"));
					order.setPhone(rs.getString("o_phone"));
					order.setAddress_label(rs.getString("o_address_label"));
					order.setGoods_id(rs.getInt("o_goods_id"));
					order.setGoods_num(rs.getInt("o_goods_num"));
					order.setGoods_status(rs.getInt("o_goods_status"));
					return order;	
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
	
	
	/**5
	 * 根据id删除指定的订单
	 * @param id  被删除订单的id
	 * @return  如果删除成功返回1，删除失败返回0
	 */
	public int deleteOrdersById(int id) {
		String sql="delete from t_order where id=?";
		int count=JDBCUtil.update(sql, id);
		if(count != 0) {
			return 1;
		}
		return 0;
	}
	
	/**5
	 * 删除全部订单
	 * @return  如果删除成功返回1，删除失败返回0
	 */
	public int deleteAllOrders() {
		String sql="delete from t_order";
		int count=JDBCUtil.update(sql);
		if(count != 0) {
			return 1;
		}
		return 0;
	}
	
	/**6
	 * 添加订单的方法
	 * @param order 被添加的订单
	 * @return 添加成功返回1，否则返回0
	 */
	public int addOrder(Orders order) {
		String sql="insert into t_order(o_number,o_user,o_time,o_name,o_sex,o_address,o_phone,o_address_label,o_goods_id,o_goods_num,o_goods_status) values(?,?,?,?,?,?,?,?,?,?,?)";
		int count=JDBCUtil.update(sql,order.getNumber(),order.getUser(),order.getTime(),order.getName(),order.getSex(),order.getAddress(),order.getPhone(),order.getAddress_label(),order.getGoods_id(),order.getGoods_num(),order.getGoods_status());
		if(count != 0) {
			return 1;
		}
		return 0;
	}
}
