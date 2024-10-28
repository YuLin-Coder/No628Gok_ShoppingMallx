package cn.gok.backstageManagement.dao;

import java.util.List;
import java.util.Map;


import cn.gok.backstageManagement.entity.Orders;



public interface OrderDao {
	/**1
	 * 查询所有的商品信息：  
	 * 		将dao中查到的数据返回，
	 *  	控制层会调用此方法，从中拿到数据，并显示到浏览器页面
	 * @return  返回商品集合
	 */
	public List<Orders> getAllOrders();
	/**2
	 * 根据类别或者商品名，查出对应类别的商品信息：
	 * @param   parms 封装着 类别和商品名信息 
	 * @return   返回商品集合
	 */
	public List<Orders> getOrdersByName(Map<String, String> parms);
	/**3
	 * 根据id修改订单的信息，但是id 和 创建商品的时间不修改 
	 * @param good 封装着要修改的信息的Goods对象
	 * @return 如果修改成功返回1，修改失败返回0
	 */
	public int updateOrdersById(Orders order);
	
	/**4
	 * 根据订单id获取 商品的数据
	 * @param id
	 * @return 返回具体商品信息
	 */
	
	public Orders getOrdersInfoById(int id);
	
	/**5
	 * 根据id删除指定的商品
	 * @param id  被删除商品的id
	 * @return  如果删除成功返回1，删除失败返回0
	 */
	public int deleteOrdersById(int id);
	/**6
	 * 添加订单的方法
	 * @param order 被添加的商品
	 * @return 添加成功返回1，否则返回0
	 */
	public int addOrder(Orders order);

}
