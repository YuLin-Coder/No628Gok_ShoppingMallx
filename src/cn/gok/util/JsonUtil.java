package cn.gok.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 *基于阿里的fastjson封装的json转换工具类 json转换的工具类
 */
public class JsonUtil {
	
	/**
	 * 将json数据转成对应的java对象 
	 * @param jsonData  前端页面发送过来的json数据
	 * @param clazz     要转换的类型
	 * @return
	 */
	public static <T> T jsonToBean(String jsonData, Class<T> clazz) {
        return JSON.parseObject(jsonData, clazz);
    }
	

	/**
     * 把前端传过来的JSON数据转换成指定的java 的List集合:
     * 		一般来说会转成：   
     * 				 List<String>
     * 				 List<javaBean类，如User>
     * 		         List<Map<String,Object>>
     *      这三种集合
     * @param jsonData 前端页面发送过来的json数据
     * @param clazz    要转换的类型
     * @return List<T>
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> clazz) {
        return JSON.parseArray(jsonData, clazz);
    }
    
    /**
     * 将json字符串转化为map
     * @param json字符串
     * @return  map
     */
    public static <K, V> Map<K, V>  jsonToMap(String jsonData) {
    	Map<K, V> m = (Map<K, V>) JSONObject.parseObject(jsonData);
        return m;
    }

    
    /**
     * json数据 转换为java数组
     * @param text
     * @param clazz
     * @return
     */
    public static <T> Object[] jsonToArray(String jsonData, Class<T> clazz) {
        return JSON.parseArray(jsonData, clazz).toArray();
    }
    
	/**
     * 把java对象转换成JSON字符串
     * @param object    要被转换的java对象
     * @return JSON数据
     */
    public static String javaObjectToJson(Object object) {
        return JSON.toJSONString(object);
    }
}
