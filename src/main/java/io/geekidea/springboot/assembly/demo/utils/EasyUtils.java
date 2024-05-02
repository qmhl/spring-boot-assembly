package io.geekidea.springboot.assembly.demo.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.CollectionUtils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * @author changliang time:2013-3-27 19:48:00
 */
public class EasyUtils {
	private final static Log log = LogFactory.getLog(EasyUtils.class);
	private static final int MIN_WARE_ID = 100000; //商品编号大于100000
	private static String BASE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	/**
	 * 拼装字符串
	 * 
	 * @author changliang 2013-3-26 10:04
	 * @param args
	 * @return
	 */
	public static String getAssembleChars(Object... args) {
		StringBuilder builder = new StringBuilder();
		for (Object obj : args) {
			builder.append(obj);
		}
		return builder.toString();
	}
	/**
     * 按字节截取字符串。
     *
     * @param originalString 原始字符串
     * @param length  截取的字节数
     */
    public static String subStringByBytes(String originalString, int length) {
        return subStringByBytes(originalString, 0, length);
    }
	
    /**
     * 按字节截取字符串。
     *
	 * @author minsiqiang 2013-3-29 18:51
     * @param originalString 原始字符串
     * @param startIndex 起始索引
     * @param length  截取的字节数
     */
    public static String subStringByBytes(String originalString, int startIndex, int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length can not be minus!");
        }
        if (StringUtils.isEmpty(originalString)) {
            return originalString;
        }

        if (startIndex >= originalString.length() || startIndex < 0) {
            throw new IllegalArgumentException("startIndex must be between 0 and " + originalString.length());
        }

        String subString  = originalString.substring(startIndex);
        byte[] bytes = subString.getBytes();
        if (bytes.length < length) {
            return subString;
        }
        byte[] newBytes = new byte[length];
        for (int i = 0; i < length && i < bytes.length; i++) {
            newBytes[i] = bytes[i];
        }
        return new String(newBytes);
    }

    /**
     * 判断是否合法的商品编号
     * @param wareId  商品编号
     */
    public static boolean isLegalWareId(Object wareId) {
        boolean isLegal = false;
        String wareId1 = String.valueOf(wareId);
        if (wareId1.matches("^\\d{6,10}$") && parseInt(wareId) > MIN_WARE_ID) {
            isLegal = true;
        }
        return isLegal;
    }

    /**
     * 判断是否合法的图书商品编号
     * @param bookWareId  图书商品编号
     */
    public static boolean isLegalBookWareId(Object bookWareId) {
       boolean isLegal = false;
       String wareId1 = String.valueOf(bookWareId);
       if (wareId1.matches("^\\d{8}$")) {
          isLegal = true;
       }
       return isLegal;
    }

    /**
     * 过滤不合法的商品编号 add by liujin
     * @param wareId  1个或多个商品编号
     * return [过滤后商品编号]
     */
    public static String filterIllegalWareId(Object wareId) {
        if(wareId == null){
            return "";
        }
        StringBuilder legalWareId = new StringBuilder("");
        String wareId1 = String.valueOf(wareId);
        if (wareId1.indexOf(",") != -1) {   //多个商品编号
            String[] wareArr = wareId1.split(",");
            for (String wareIdTemp : wareArr) {
                if (isLegalWareId(wareIdTemp)) {
                    legalWareId.append(wareIdTemp.trim());
                    legalWareId.append(",");
                }
            }
            String legalWareIdStr = legalWareId.toString();
            if (legalWareIdStr.length() >= 1) {
                legalWareIdStr = legalWareIdStr.substring(0, legalWareIdStr.length() - 1);
            }
            return legalWareIdStr;
        }
        if (!isLegalWareId(wareId)) {  //1个商品编号
            wareId1= "";
        }
        return wareId1;
    }
    
    /**
     * 默认返回值为入参defaultValue
     * @author changliang
     * @param origin 要进行转型的对象
     * @param defaultValue 默认值，有异常情况时返回的值
     * @return 转型后的int值
     */
    public static int parseInt(Object origin, int defaultValue) {
    	int result = defaultValue;
    	if(origin == null) {
    		return result;
    	}
    	
    	String s = origin.toString();
    	try {
			result = Integer.parseInt(s);
		} catch (Exception e) {
			log.error("转型异常，参数：" + s);
		}
    	return result;
    }

    /**
     * 默认返回值为入参defaultValue
     * @param origin 要进行转型的对象
     * @param defaultValue 默认值，有异常情况时返回的值
     * @return 转型后的Integer值
     */
    public static Integer parseInteger(Object origin, Integer defaultValue) {
        Integer result = defaultValue;
        if(origin == null) {
            return result;
        }

        String s = origin.toString();
        try {
            result = Integer.valueOf(s);
        } catch (Exception e) {
            log.error("转型异常，参数：" + s);
        }
        return result;
    }

    
    /**
     * 调用此方法时，默认返回值为0
     * @author changliang
     * @param origin 要进行转型的对象
     * @return 转型后的int值
     */
    public static int parseInt(Object origin) {
    	return parseInt(origin, 0);
    }


    /**
     * 调用此方法时，默认返回值为null，origin不为null时，返回对应的Integer类型值
     * @param origin
     * @return
     */
    public static Integer parseInteger(Object origin) {
        return parseInteger(origin, null);
    }

	public static int parseIntIgnoreLog(Object origin) {
		int result = 0;
		if (origin == null) {
			return result;
		}
		String s = origin.toString();
		try {
			result = Integer.parseInt(s);
		} catch (Exception e) {
		}
		return result;
	}
	/**
	 * 默认返回值为入参defaultValue
	 * @author maowenhui
	 * @param origin 要进行转型的对象
	 * @param defaultValue 默认值，有异常情况时返回的值
	 * @return 转型后的long值
	 */
	public static long parseLong(Object origin, long defaultValue) {
		long result = defaultValue;
		if (origin == null) {
			return result;
		}

		String s = origin.toString();
		if (StringUtils.isEmpty(s)) {
			return result;
		}
		try {
			result = Long.parseLong(s);
		} catch (Exception e) {
			log.error("转型异常，参数：" + origin, e);
		}

		return result;
	}

	/**
	 * 调用此方法时，默认返回值为0
	 * @author maowenhui
	 * @param origin 要进行转型的对象
	 * @return 转型后的long值
	 */
	public static long parseLong(Object origin) {
		return parseLong(origin, 0);
	}
    
    public static double parseDouble(Object origin) {
    	return parseDouble(origin, 0.0);
    }
	public static double parseDouble(Object origin, double defaultValue) {
		double result = defaultValue;
		if(origin == null) {
			return result;
		}
		String s = origin.toString();
		try {
			result = Double.parseDouble(s);
		} catch (Exception e) {
			log.error("转型异常，参数：" + s);
		}
		return result;
	}
	
	public static String toStringTrim(Object o) {
		return toString(o).trim();
	}
	
    /**
     * 调用此方法时，默认返回值为""
     * @author liujinfeng
     * @param o 要进行转型的对象
     * @return 转型后的String值
     */
    public static String toString(Object o) {
        return toString(o , "");
    }
    /**
     * 默认返回值为入参defaultValue
     * @author liujinfeng
     * @param origin 要进行转型的对象
     * @param defaultValue 默认返回值
     * @return 转型后的String值
     */
    public static String toString(Object origin,String defaultValue) {
        if (origin == null) {
        	return defaultValue;
        }
        String temp = origin.toString();
        if (StringUtils.isNotBlank(temp)){
        	return origin.toString();
        } 
        return defaultValue;
    }
    /**
     * 调用此方法时，默认返回值为null
     * @author liujinfeng
     * @param o 要进行转型的对象
     * @return 转型后的Date值
     */
    public static Date toDate(Object o) {
        Date date = null;
        try {
            if (o != null) {
            	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	date = format.parse(toString(o));
            }
        } catch (Exception e) {
            log.error("toDate() error, " + e.getMessage());
        }
        return date;
    }
    
    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        if (date == null) {
        	return null;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            log.error("toXMLGregorianCalendar() error, " + e.getMessage());
        }
        return gc;
    }
    
    public static String convertXMLGregorianCalendarToString(XMLGregorianCalendar gc){
    	if (gc == null) {
    		return "";
    	}
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(gc.toGregorianCalendar().getTime());
    }


    /**
     * 颜色尺码 针对于 6 7 10位编码的商品
     * @author liujin
     */
    public static boolean isLegalWareIdForColor(Object wareId) {
        boolean isLegal = false;
        String wareId1 = String.valueOf(wareId);
        if (parseInt(wareId) > MIN_WARE_ID && (wareId1.length() == 6 || wareId1.length() == 7 || wareId1.length() == 10)) {
            isLegal = true;
        }
        return isLegal;
    }
    
    
    /**
     * 获取长度为length的随机字符串
     * @param length
     * @return
     */
	public static String getRandomString(int length) {
		int number = 0;
	    Random random = new Random();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < length; i++) {   
	        number = random.nextInt(BASE_STRING.length());   
	        sb.append(BASE_STRING.charAt(number));   
	    }
	    return sb.toString();   
	 }
	
	/**
	 * 获取拼接而成的List
	 * @param string
	 * @param sp
	 * @return
	 */
	public static List<String> getList(String string, String sp) {
		List<String> list = new ArrayList<String>();
		try {
			String test = string.endsWith(sp) ? string : (string + sp);
			
			int indexNum = test.indexOf(sp);
			while(-1 != indexNum) {
				list.add(test.substring(0, indexNum));
				test = test.substring(indexNum + 1);
				indexNum = test.indexOf(sp);
			}
		} catch (Exception e) {
			log.error("getList error,", e);
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static List<Long> stringList2LongList(List<String> list) {
		List<Long> longList = new ArrayList<Long>();
		try {
			for (String string : list) {
				longList.add(Long.parseLong(string));
			}
		} catch (NumberFormatException e) {
			log.error("trans2Long error,", e);
		}
		
		return longList;
	}
	
	public static Set<Long> stringList2LongSet(List<String> list) {
		Set<Long> longList = new HashSet<Long>();
		try {
			for (String string : list) {
				longList.add(Long.parseLong(string));
			}
		} catch (NumberFormatException e) {
			log.error("trans2LongSet error,", e);
		}
		
		return longList;
	}
	
	public static String getStringFromJsonp(String jsonpString) {
		if (StringUtils.isEmpty(jsonpString) || jsonpString.indexOf("(") == -1 || jsonpString.indexOf(")") == -1) {
			return jsonpString;
		}
		return jsonpString.substring(jsonpString.indexOf("(") + 1, jsonpString.lastIndexOf(")"));
	}
	
	/**
	 * 应产品经理赵树杰要求，规范前端价格显示要求，修改价格显示格式，并做四舍五入计算
	 * @param object 例如：<p>110.01-->100.01;<p>100.10-->100.1;<p>100.00-->100;<p>100-->100
	 * @return
	 */
	public static String formatPrice(Object object) {
		String price = EasyUtils.toString(object);
		if(StringUtils.isEmpty(price)) {
			return "";
		}
		
		try {
			BigDecimal bigDecimalPrice = new BigDecimal(price);
			price = bigDecimalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			while(-1 != price.indexOf(".") && price.endsWith("0")) {
				price = price.substring(0, price.length() - 1);
			}
			if(price.endsWith(".")) {
				return price.substring(0, price.length() - 1);
			}
		} catch (Exception e) {
			log.error("格式化价格异常，异常object：" + object, e);
		}
		return price;
	}

	
    public static int getUserType(String erpNo) {
        if(erpNo.startsWith("JD_B_")){
            return 1;
        }
        else {
        	return 0;
		}
    }

	public static String getOriginErpNo(String erpNo) {
		if(erpNo.startsWith("JD_B_")){
			return erpNo.replace("JD_B_","");
		}
		return erpNo;
	}
    


    
	/**
	 * 校验重复的ID
	 * @param allIdList
	 * @return
	 */
	public static List<String> checkRepeatId(List<String> allIdList) {
		if(CollectionUtils.isEmpty(allIdList)) {
			return null;
		}
		List<String> errorIdList = new ArrayList<String>();
		List<String> tempIdList = new ArrayList<String>();
		for (String id : allIdList) {
			if(tempIdList.contains(id)) {
				errorIdList.add(id);
			}
			tempIdList.add(id);
		}
		return errorIdList;
	}

	/**
	 * 数字进制转换
	 * @param num
	 * @param from
	 * @param to
	 * @return
	 */
	public static String numBaseConversion(String num, int from, int to){
		BigInteger i = new BigInteger(num.trim(), from);
		return i.toString(to);
	}

	/**
	 * 计算Double除法
	 * 结果四舍五入，默认保留2位小数，分母为0时返回0
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public static Double calDoubleDivision(double numerator, double denominator) {
		return calDoubleDivision(numerator, denominator, 2);
	}
	public static Double calDoubleDivision(double numerator, double denominator, int scale) {
		double cal = (numerator != 0 && denominator != 0) ? numerator / denominator : 0;
		BigDecimal ans = new BigDecimal(cal);
		return ans.setScale(scale, RoundingMode.HALF_UP).doubleValue();
	}

	/**
	 * 计算总页数
	 */
	public static int calTotalPage(int totalSize, int pageSize) {
		return (int) Math.ceil(1.0 * totalSize / pageSize);
	}

	public static boolean parseBoolean(Object bool) {
		return parseBoolean(bool,false);
	}

	public static boolean parseBoolean(Object origin,boolean defaultValue) {
		boolean res = defaultValue;
		if (origin == null) {
			return res;
		}
		String str = origin.toString();
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return res;
		}
		try {
			res = Boolean.parseBoolean(str);
		} catch (Exception e) {
			log.error("转型异常，参数：" + origin, e);
		}
		return res;
	}

	//下划线转驼峰
	public static String underline2Camel(String underline){
		Pattern pattern = Pattern.compile("[_]\\w");
		Matcher matcher = pattern.matcher(underline);
		while(matcher.find()){
			String w = matcher.group().trim();
			underline = underline.replace(w,w.toUpperCase().replace("_", ""));
		}
		return underline;
	}


	/**
	 * 获取拼接而成的Set
	 * @param string
	 * @param sp
	 * @return
	 */
	public static Set<String> getSet(String string, String sp) {
		Set<String> set = new HashSet<>();
		try {
			String test = string.endsWith(sp) ? string : (string + sp);

			int indexNum = test.indexOf(sp);
			while(-1 != indexNum) {
				set.add(test.substring(0, indexNum));
				test = test.substring(indexNum + 1);
				indexNum = test.indexOf(sp);
			}
		} catch (Exception e) {
			log.error("getSet error,", e);
		}

		return set;
	}

	public static <T> Map<String, Object> beanToMap(T bean) {
		Map<String, Object> map = new HashMap<>();
		if (bean != null) {
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				map.put(key + "", beanMap.get(key));
			}
		}
		return map;
	}
	/**
	 * 分割set成一个新的list列表
	 * @param set set
	 * @param pageSize  每段set的大小
	 */
	public static <T> List<Set<T>> splitSet(Set<T> set, int pageSize) {
		List<Set<T>> listArray = Lists.newArrayList(); // 创建list数组, 用来保存分割后的list
		if(CollectionUtils.isEmpty(set)){
			return listArray;
		}
		Set<T> tmpSet = new HashSet<>();
		for (T el : set) {
			tmpSet.add(el);
			if (tmpSet.size() >= pageSize) {
				listArray.add(tmpSet);
				tmpSet = new HashSet<>();
			}
		}

		if (tmpSet.size()>0) {
			listArray.add(tmpSet);
		}

		return listArray;
	}

}

