package cn.gok.frontstageManagement.dao.implement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.gok.frontstageManagement.dao.GoodsDao;
import cn.gok.frontstageManagement.entity.Goods;
import cn.gok.frontstageManagement.entity.PageBean;
import cn.gok.util.JDBCUtil;

public class ImplementGoodsDao implements GoodsDao{

	@Override
	public Map<String, List<Goods>> getAllGoods() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public PageBean getGoodsByType(String type, int pageSize, int currentPage) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public int getTotalCount(String type) {
		String sql = "select * from t_goods where g_type =?";
		int i = JDBCUtil.queryForInt(sql, type);
		int count = 0;
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, type);
			try{
				while(rs.next()){
					count++;					
				}
				return count;	
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public Goods getGoodsInfoById(int id) {
		String sql = "select * from t_goods where id =?";
		int i = JDBCUtil.queryForInt(sql, id);
		
		if(i != 0) {
			ResultSet rs=JDBCUtil.executeQuery(sql, id);
			try{
				while(rs.next()){
					Goods good = new Goods();
					good.setName(rs.getString("name"));
					good.setPrice(rs.getDouble("price"));
					good.setNum(rs.getInt("num"));
					good.setColor(rs.getString("color"));
					good.setCreateDate(rs.getString("createDate"));
					good.setDescription(rs.getString("description"));
					good.setImgurl(rs.getString("imgurl"));
					good.setMemory(rs.getString("memory"));
					good.setType(rs.getString("type"));
					
					return good;	
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		// TODO 自动生成的方法存根
		return null;
	}

}
