package io.geekidea.springboot.assembly.demo.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;


public class ObjectUtil {

    /**
     * 至少有一个为空
     *
     * @param datas
     * @return
     */
    public static boolean isEmptyAtLeast(Object... datas) {

        if (datas == null) {
            return true;
        }

        for (int i = 0; i < datas.length; i++) {
            if (isEmpty(datas[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null || StringUtils.EMPTY.equals(obj.toString().trim())) {
            return true;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).size() == 0;
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (Object o : object) {
                if (!isEmpty(o)) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    // Identity ToString
    //-----------------------------------------------------------------------

    /**
     * <p>Gets the toString that would be produced by <code>Object</code>
     * if a class did not override toString itself. <code>null</code>
     * will return <code>null</code>.</p>
     * <p>
     * <pre>
     * ObjectUtils.identityToString(null)         = null
     * ObjectUtils.identityToString("")           = "java.lang.String@1e23"
     * ObjectUtils.identityToString(Boolean.TRUE) = "java.lang.Boolean@7fa"
     * </pre>
     *
     * @param object the object to create a toString for, may be
     *               <code>null</code>
     * @return the default toString text, or <code>null</code> if
     * <code>null</code> passed in
     */
    public static String identityToString(Object object) {
        if (object == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        identityToString(buffer, object);
        return buffer.toString();
    }

    /**
     * <p>Appends the toString that would be produced by <code>Object</code>
     * if a class did not override toString itself. <code>null</code>
     * will throw a NullPointerException for either of the two parameters. </p>
     * <p>
     * <pre>
     * ObjectUtils.identityToString(buf, "")            = buf.append("java.lang.String@1e23"
     * ObjectUtils.identityToString(buf, Boolean.TRUE)  = buf.append("java.lang.Boolean@7fa"
     * ObjectUtils.identityToString(buf, Boolean.TRUE)  = buf.append("java.lang.Boolean@7fa")
     * </pre>
     *
     * @param buffer the buffer to append to
     * @param object the object to create a toString for
     * @since 2.4
     */
    public static void identityToString(StringBuffer buffer, Object object) {
        if (object == null) {
            throw new NullPointerException("Cannot get the toString of a null identity");
        }
        buffer.append(object.getClass().getName())
                .append('@')
                .append(Integer.toHexString(System.identityHashCode(object)));
    }

    /**
     * 获取第一个不为空的值,所有的值为空，则返回默认值
     *
     * @param defaultValue 默认值
     * @param values       待判断的值
     * @return
     */
    public static Object getFirstIfPresent(Object defaultValue, Object... values) {

        if (values == null) {
            return defaultValue;
        }

        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                return values[i];
            }
        }

        return defaultValue;

    }

}
