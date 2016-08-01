package com.boz.common.utils;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author bo
 *
 */
public class CommonResult {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private Integer status;

	private String msg;

	private Object data;

	/**
	 * empty constructor.
	 */
	private CommonResult() {
	}

	/**
	 * @param data
	 */
	private CommonResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	/**
	 * @param status
	 * @param msg
	 * @param data
	 */
	private CommonResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * @param status
	 * @param msg
	 * @param data
	 * @return
	 */
	public static CommonResult build(Integer status, String msg, Object data) {
		return new CommonResult(status, msg, data);
	}

	/**
	 * @param data
	 * @return
	 */
	public static CommonResult ok(Object data) {
		return new CommonResult(data);
	}

	/**
	 * @return
	 */
	public static CommonResult ok() {
		return new CommonResult(null);
	}

	/**
	 * @param status
	 * @param msg
	 * @return
	 */
	public static CommonResult build(Integer status, String msg) {
		return new CommonResult(status, msg, null);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 将json结果集转化为TaotaoResult对象
	 * 
	 * @param jsonData
	 *            json数据
	 * @param clazz
	 *            TaotaoResult中的object类型
	 * @return
	 */
	public static CommonResult formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, CommonResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isObject()) {
				obj = MAPPER.readValue(data.traverse(), clazz);
			} else if (data.isTextual()) {
				obj = MAPPER.readValue(data.asText(), clazz);
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 没有object对象的转化
	 * 
	 * @param json
	 * @return
	 */
	public static CommonResult format(String json) {
		try {
			return MAPPER.readValue(json, CommonResult.class);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Object是集合转化
	 * 
	 * @param jsonData
	 *            json数据
	 * @param clazz
	 *            集合中的类型
	 * @return
	 */
	public static CommonResult formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

}
