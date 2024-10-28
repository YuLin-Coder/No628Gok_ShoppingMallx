package cn.gok.backstageManagement.dao;

import java.util.List;
import java.util.Map;

import cn.gok.backstageManagement.entity.Goods;

/**
* @author CJY
* @version 创建时间：2019年7月22日 下午1:00:28
* @ClassName 类名称
* @Description 类描述
*/
public interface GoodsDao {
	/**
	 * 获取所有的商品列表
	 * @return
	 */
	public  List<Goods> getAllGoods();
	
	/**
	 * 根据商品的名字   或者 类型   获取商品列表，进行模糊查询
	 * @param params封装着 商品名 和 类型的map 
	 * @return
	 */
	public List<Goods> getGoodsByName(Map<String, String> params);
	
	/**
	 * 根据商品id获取某个商品的详细信息
	 * @param id
	 * @return
	 */
	public Goods getGoodsInfoById(int id);
	
	/**
	 * 添加商品的方法
	 * @param good 被添加的商品
	 * @return 添加成功返回1，否则返回0
	 */
	public int addGoods(Goods good);
	
	/**
	 * 根据id修改商品的信息，但是id 和 创建商品的时间不修改 
	 * @param good 封装着要修改的信息的Goods对象
	 * @return 如果修改成功返回1，修改失败返回0
	 */
	public int updateGoodsById(Goods good);
	
	/**
	 * 修改商品的图片，
	 * 修改商品时，如果用户有修改新图片，先将图片上传修改
	 * @param good
	 * @return
	 */
	public int updateGoodsImgById(String img,int id);
	
	/**
	 * 根据id删除指定的商品
	 * @param id  被删除商品的id
	 * @return  如果删除成功返回1，删除失败返回0
	 */
	public int deleteGoodsById(int id);

}
