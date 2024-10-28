package cn.gok.backstageManagement.dao.implement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.gok.backstageManagement.dao.GoodsDao;
import cn.gok.backstageManagement.entity.Goods;
import cn.gok.backstageManagement.entity.User;
import cn.gok.util.JDBCUtil;

public class ImplementGoodsDao implements GoodsDao{

	@Override
	public List<Goods> getAllGoods() {
		String sql="select * from t_goods";
		List<Goods> slist=new ArrayList<>();
		
		ResultSet rs=JDBCUtil.executeQuery(sql);
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
				
				slist.add(good);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return slist;
	}

	@Override
	public List<Goods> getGoodsByName(Map<String, String> params) {
		// TODO 自动生成的方法存根
		return null;
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

	@Override
	public int addGoods(Goods good) {
		String sql="insert into t_goods(id,g_name,g_price,g_num,g_imgurl,g_description,g_type,g_create_date,g_color,g_memory) values(?,?,?,?,?,?,?,?,?,?)";
		int count=JDBCUtil.update(sql, good.getId(),good.getName(),good.getPrice(),good.getNum(),good.getImgurl(),good.getDescription(),good.getType(),good.getCreateDate(),good.getColor(),good.getMemory());
		if(count != 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int updateGoodsById(Goods good) {
		String sql="update t_goods set g_name=?,g_price=?,g_num=?,g_imgurl=?,g_description=?,g_type=?,g_color=?,g_memory=? where id=?";
		int count=JDBCUtil.update(sql, good.getName(),good.getPrice(),good.getNum(),good.getImgurl(),good.getDescription(),good.getType(),good.getColor(),good.getMemory(),good.getId());
		if(count != 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int updateGoodsImgById(String img, int id) {
		String sql="update t_goods set g_imgurl=? where id=?";
		int count=JDBCUtil.update(sql, img, id);
		if(count != 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteGoodsById(int id) {
		String sql="delete from t_goods where id=?";
		int count=JDBCUtil.update(sql, id);
		if(count != 0) {
			return 1;
		}
		return 0;
	}
	
}
