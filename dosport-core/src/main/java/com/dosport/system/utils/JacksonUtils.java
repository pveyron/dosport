package com.dosport.system.utils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.ser.BeanSerializerFactory;
import org.codehaus.jackson.map.ser.BeanSerializerFactory.ConfigImpl;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;

/**
 * jackson工具类.
 * 
 * @author pwl
 * 
 */
public class JacksonUtils {
	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性.
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		// 所有日期格式都统一为以下样式.
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
	}

	public static String obj2Json(Object obj) {

		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			LogUtils.error(e, "对象转换成json字符串失败");
			return null;
		}
	}

	public <T> T json2Bean(String json, Class<T> clazz) {

		try {
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			LogUtils.error(e, "json字符串转换成指定对象失败");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> json2List(String json) {

		try {
			return mapper.readValue(json, List.class);
		} catch (Exception e) {
			LogUtils.error(e, "json字符串转换成list失败");
			return null;
		}
	}

	public static String filterObj2Json(Object obj, String[] filterProperties) {

		try {
			SimpleFilterProvider fileter = new SimpleFilterProvider();
			fileter.addFilter("executeFilter", SimpleBeanPropertyFilter.serializeAllExcept(filterProperties));
			mapper.setFilters(fileter);
			mapper.setSerializerFactory(new BeanSerializerFactory(new ConfigImpl()) {
				protected Object findFilterId(SerializationConfig config, BasicBeanDescription beanDesc) {
					return "executeFilter";
				}
			});
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			LogUtils.error(e, "对象转换成json字符串失败");
			return null;
		}
	}

}
