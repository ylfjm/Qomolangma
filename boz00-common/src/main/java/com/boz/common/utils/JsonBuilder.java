package com.boz.common.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * JSON 构造器类，可以将Java对象映射为JSON对象
 */
public class JsonBuilder {

    private static final Object EMPTY_OBJECT_ARRAY[] = new Object[0];
    //排除的字段
    private Set<String> excludeFields = new HashSet<String>();

    public JsonBuilder() {
        excludeFields.add("class");
        excludeFields.add("declaringClass");
        excludeFields.add("metaClass");
    }

    /**
     * 转换成JSON时需要排除的字段
     *
     * @param excludes
     */
    public void setExcludeFields(String... excludes) {
        for (String exclude : excludes) {
            excludeFields.add(exclude);
        }
    }

    /**
     * 将Java对象转化为JSON对象
     *
     * @param obj java对象
     * @return js对象
     */
    public String encode(Object object) {
        return encodeBasic(object);
    }

    /**
     * 将Java对象转化为JSON对象
     *
     * @param obj java对象
     * @return js对象
     */
    @SuppressWarnings("rawtypes")
    private String encodeBasic(Object object) {
        // basic   
        if (object == null) {
            return encodeNULL();
        } else if (object instanceof String) {
            return encodeString((String) object);
        } else if (object instanceof Boolean) {
            return encodeBoolean((Boolean) object);
        } else if (object instanceof Number) {
            return encodeNumber((Number) object);
        } else if (object instanceof Date) {
            return encodeDate((Date) object);
        } else if (object instanceof Map) {
            return encodeMap((Map) object);
        } else if (object instanceof Iterable) {
            return encodeIterable((Iterable) object);
        } else if (object instanceof Object[]) {// object.getClass().isArray()   
            return encodeArray((Object[]) object);
        } else if (object instanceof Date) {
            return encodeDate((Date) object);
        } else {
            Class clazz = object.getClass();

            if (clazz.isInterface()) {
                return encodeEmpty();
            }

            if (clazz.isEnum()) {
                return encodeEnum((Enum) object);
            } else {
                return encodeAdapter(object);
            }
        }
    }

    /**
     * 返回一个NULL对象
     *
     * @return javascript null对象
     */
    private String encodeNULL() {
        return "null";
    }

    /**
     * 将Java-String对象转化为JSON对象
     *
     * @param string 字符串对象
     * @return javascript string对象
     */
    private String encodeString(String string) {
        StringBuilder sbr = new StringBuilder(string.length() * 4);
        sbr.append("\"");
        for (int i = 0, sz = string.length(); i < sz; i++) {
            char ch = string.charAt(i);
            // handle unicode
            if (ch > 0xfff) {
                sbr.append("\\u");
                sbr.append(hex(ch));
            } else if (ch > 0xff) {
                sbr.append("\\u0");
                sbr.append(hex(ch));
            } else if (ch > 0x7f) {
                sbr.append("\\u00");
                sbr.append(hex(ch));
            } else if (ch < 32) {
                switch (ch) {
                    case '\b':
                        sbr.append('\\');
                        sbr.append('b');
                        break;
                    case '\n':
                        sbr.append('\\');
                        sbr.append('n');
                        break;
                    case '\t':
                        sbr.append('\\');
                        sbr.append('t');
                        break;
                    case '\f':
                        sbr.append('\\');
                        sbr.append('f');
                        break;
                    case '\r':
                        sbr.append('\\');
                        sbr.append('r');
                        break;
                    default:
                        if (ch > 0xf) {
                            sbr.append("\\u00");
                            sbr.append(hex(ch));
                        } else {
                            sbr.append("\\u000");
                            sbr.append(hex(ch));
                        }
                        break;
                }
            } else {
                // line.
                switch (ch) {
                    case '\'':
                        sbr.append('\\');
                        sbr.append('\'');
                        break;
                    case '"':
                        sbr.append('\\');
                        sbr.append('"');
                        break;
                    case '\\':
                        sbr.append('\\');
                        sbr.append('\\');
                        break;
                    default:
                        sbr.append(ch);
                        break;
                }
            }
        }
        sbr.append("\"");
        return sbr.toString();
    }

    private String hex(char ch) {
        return Integer.toHexString(ch).toUpperCase(Locale.ENGLISH);
    }

    /**
     * 将Java-Boolean对象转化为JSON对象
     *
     * @param obj 字符串对象
     * @return javascript Boolean对象
     */
    private String encodeBoolean(Boolean b) {
        return b.toString();
    }

    /**
     * 将Java-Number对象转化为JSON对象
     *
     * @param n 数字对象
     * @return javascript Number对象
     */
    private String encodeNumber(Number n) {
        return n.toString();
    }

    /**
     * 将Java-Map对象转化为JSON对象
     *
     * @param map Map对象
     * @return javascript Map对象(简单对象)
     */
    @SuppressWarnings("rawtypes")
    private String encodeMap(Map map) {
        StringBuilder sbr = new StringBuilder();
        sbr.append("{");
        boolean isFirst = true;
        for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
            if (isFirst) {
                isFirst = false;
            } else {
                sbr.append(",");
            }
            Map.Entry entry = (Map.Entry) it.next();
            sbr.append(encodeBasic(entry.getKey())).append(":").append(encodeBasic(entry.getValue()));
        }
        sbr.append("}");
        return sbr.toString();
    }

    /**
     * 将Java-Iterable对象转化为JSON对象
     *
     * @param iterable Iterable对象
     * @return javascript Array对象
     */
    @SuppressWarnings("rawtypes")
    private String encodeIterable(Iterable iterable) {
        StringBuilder sbr = new StringBuilder();
        sbr.append("[");
        int index = 0;
        for (Iterator it = iterable.iterator(); it.hasNext(); ) {
            if ((index++) > 0) {
                sbr.append(",");
            }
            sbr.append(encodeBasic(it.next()));
        }
        sbr.append("]");
        return sbr.toString();
    }

    /**
     * 将Java-数组对象转化为JSON对象
     *
     * @param obj 数组对象
     * @return javascript Array对象
     */
    private String encodeArray(Object[] array) {
        StringBuilder sbr = new StringBuilder();
        sbr.append("[");
        for (int index = 0; index < array.length; index++) {
            if (index > 0) {
                sbr.append(",");
            }
            Object o = array[index];
            sbr.append(encodeBasic(o));
        }
        sbr.append("]");
        return sbr.toString();
    }

    /**
     * 将Java-Date对象转化为JSON对象
     *
     * @param date 日期对象
     * @return javascript Date对象
     */
    private String encodeDate(Date date) {
        return DateUtil.formatDate(date, "yyyyMMdd");
    }

    /**
     * 将Java-Enum对象转化为JSON对象
     *
     * @param e 枚举对象
     * @return javascript Date对象
     */
    @SuppressWarnings("rawtypes")
    private String encodeEnum(Enum e) {
        return "\"" + e.name() + "\"";
    }

    /**
     * 返回一个JSON简单对象
     *
     * @return javascript 简单对象
     */
    private String encodeEmpty() {
        return "{}";
    }

    /**
     * 将Java对象转化为JSON对象
     *
     * @param object Java对象
     * @return 如果适配器能解析, 则返回解析后的JSON对象，否则返回一个默认Empty JSON对象
     */
    @SuppressWarnings("rawtypes")
    private String encodeAdapter(Object object) {
        try {
            Map<String, Object> proxy = new HashMap<String, Object>();
            Class clazz = object.getClass();
            if (clazz == null) {
                throw new IllegalArgumentException("No bean class specified");
            }
            proxy.put("class", clazz.getName());
            PropertyDescriptor[] descriptors = null;
            try {
                descriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            } catch (IntrospectionException e) {
                descriptors = new PropertyDescriptor[0];
            }
            for (int i = 0, j = descriptors.length; i < j; i++) {
                PropertyDescriptor descriptor = descriptors[i];
                String key = descriptor.getName();
                //排除的字段   
                if (excludeFields.contains(key)) {
                    continue;
                }
                Method method = descriptor.getReadMethod();
                if (descriptor.getReadMethod() != null) {
                    Class<?> type = descriptor.getPropertyType();

                    if (type.isEnum()) {
                        continue;
                    }
                    Object value = method.invoke(object, EMPTY_OBJECT_ARRAY);
                    proxy.put(key, value);
                }
            }
            return encodeMap(proxy);
        } catch (Exception ex) {
            return encodeEmpty();
        }
    }
}  
