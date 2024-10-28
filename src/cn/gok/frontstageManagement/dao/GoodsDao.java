package cn.gok.frontstageManagement.dao;

import java.util.List;
import java.util.Map;

import cn.gok.frontstageManagement.entity.Goods;
import cn.gok.frontstageManagement.entity.PageBean;

/**
* @author CJY
* @version 创建时间：2019年7月22日 下午1:05:48
* @ClassName 类名称
* @Description 类描述
*/
public interface GoodsDao {
	/**
	 * 获取数据库中每种商品按时间排序的前4个
	 * 将拿到的数据根据商品的类别 存入map中，
	 * 其中map的key为类别，value为该类型商品的list集合
	 * @return
	 */
	public Map<String, List<Goods>> getAllGoods();
	
	/**
	 * 
	 * @param type        	类别
	 * @param pageSize    	每页显示数据量
	 * @param currentPage 	当前第几页
	 * @return
	 * 
	 *   分页公式：  limit (currentPage-1)*pageSize,pageSize
	 */
	public PageBean getGoodsByType(String type, int pageSize, int currentPage);
	
	/**
	 * 根据类别进行查询时，  分页时使用
	 * 	获取当前类别商品的总数量
	 * @return
	 */
	public int getTotalCount(String type);
	
	/**
	 * 根据商品id获取某个商品的详细信息
	 * @param id
	 * @return
	 */
	public Goods getGoodsInfoById(int id);
}
