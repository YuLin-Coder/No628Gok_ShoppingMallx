package cn.gok.backstageManagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.gok.backstageManagement.entity.Goods;
import cn.gok.backstageManagement.entity.Orders;
import cn.gok.util.JDBCUtil;

/**
 * 商品的业务处理中
 * @author asus
 *
 */
public class GoodsService {
	/**
	 * 查询所有的商品信息：  
	 * 		将dao中查到的数据返回，
	 *  	控制层会调用此方法，从中拿到数据，并显示到浏览器页面
	 * @return  返回商品集合
	 */
	public List<Goods> getAllGoods() {
		Connection con  = null;
		PreparedStatement ps = null;
		String sql="select * from t_goods";
		List<Goods> slist=new ArrayList<>();
		ResultSet rs=JDBCUtil.executeQuery(sql);
		try{
			while(rs.next()){
				Goods good = new Goods();
				good.setId(rs.getInt("id"));
				good.setName(rs.getString("g_name"));
				good.setPrice(rs.getDouble("g_price"));
				good.setNum(rs.getInt("g_num"));
				good.setColor(rs.getString("g_color"));
				good.setCreateDate(rs.getString("g_create_date"));
				good.setDescription(rs.getString("g_description"));
				good.setImgurl(rs.getString("g_imgurl"));
				good.setMemory(rs.getString("g_memory"));
				good.setType(rs.getString("g_type"));
				slist.add(good);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		return slist;
	}
	
	/**
	 * 根据类别或者商品名，查出对应类别的商品信息：
	 * @param   parms 封装着 类别和商品名信息 
	 * @return   返回商品集合
	 */
	public List<Goods> getGoodsByName(Map<String, String> parms){
		Connection con  = null;
		PreparedStatement ps = null;
		String name = parms.get("name");
		String sql="select * from t_goods where g_name like ?";
		List<Goods> slist=new ArrayList<>();
		String str = "%" + name + "%";
		ResultSet rs=JDBCUtil.executeQuery(sql, str);
		try{
			while(rs.next()){
				Goods good = new Goods();
				good.setId(rs.getInt("id"));
				good.setName(rs.getString("g_name"));
				good.setPrice(rs.getDouble("g_price"));
				good.setNum(rs.getInt("g_num"));
				good.setColor(rs.getString("g_color"));
				good.setCreateDate(rs.getString("g_create_date"));
				good.setDescription(rs.getString("g_description"));
				good.setImgurl(rs.getString("g_imgurl"));
				good.setMemory(rs.getString("g_memory"));
				good.setType(rs.getString("g_type"));
				slist.add(good);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		return slist;
	}
	
	/**
	 * 添加商品的方法
	 * @param good 被添加的商品
	 * @return 添加成功返回1，否则返回0
	 */
	public int addGoods(Goods good) {
		String sql="insert into t_goods(id,g_name,g_price,g_num,g_imgurl,g_description,g_type,g_create_date,g_color,g_memory) values(?,?,?,?,?,?,?,?,?,?)";
		int count=JDBCUtil.update(sql, good.getId(),good.getName(),good.getPrice(),good.getNum(),good.getImgurl(),good.getDescription(),good.getType(),good.getCreateDate(),good.getColor(),good.getMemory());
		if(count != 0) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * 根据id修改商品的信息，但是id 和 创建商品的时间不修改 
	 * @param good 封装着要修改的信息的Goods对象
	 * @return 如果修改成功返回1，修改失败返回0
	 */
	public int updateGoodsById(Goods good) {
		String sql="update t_goods set g_name=?,g_price=?,g_num=?,g_description=?,g_type=?,g_color=?,g_memory=? where id=?";
		int count=JDBCUtil.update(sql, good.getName(),good.getPrice(),good.getNum(),good.getDescription(),good.getType(),good.getColor(),good.getMemory(),good.getId());
		if(count != 0) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * 修改商品的图片，
	 * 修改商品时，如果用户有修改新图片，先将图片上传修改
	 * @param good
	 * @return  如果修改成功返回1，删除失败返回0
	 */
	public int updateGoodsImgById(String img, int id) {
		String sql="update t_goods set g_imgurl=? where id=?";
		int count=JDBCUtil.update(sql, img, id);
		if(count != 0) {
			return 1;
		}
		return 0;
	}
	
	
	/**
	 * 根据商品id获取 商品的数据
	 * @param id
	 * @return 返回具体商品信息
	 */
	public Goods getGoodsInfoById(int id) {
		Connection con  = null;
		PreparedStatement ps = null;
		String sql = "select * from t_goods where id =?";
		int i = JDBCUtil.queryForInt(sql, id);
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, id);
			try{
				while(rs.next()){
					Goods good = new Goods();
					good.setId(rs.getInt("id"));
					good.setName(rs.getString("g_name"));
					good.setPrice(rs.getDouble("g_price"));
					good.setNum(rs.getInt("g_num"));
					good.setColor(rs.getString("g_color"));
					good.setCreateDate(rs.getString("g_create_date"));
					good.setDescription(rs.getString("g_description"));
					good.setImgurl(rs.getString("g_imgurl"));
					good.setMemory(rs.getString("g_memory"));
					good.setType(rs.getString("g_type"));
					return good;	
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
	 * 根据id删除指定的商品
	 * @param id  被删除商品的id
	 * @return  如果删除成功返回1，删除失败返回0
	 */
	public int deleteGoodsById(int id) {
		String sql="delete from t_goods where id=?";
		int count=JDBCUtil.update(sql, id);
		if(count != 0) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * 添加订单的方法
	 * @param order 被添加的商品
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
