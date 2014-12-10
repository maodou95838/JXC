package cn.joymates.jxc.utils;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 * BigDecimal类型转换器
 * 
 * @author Jackie Hou
 *
 */
public class BigDecimalConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class toClass) {
		
		if(BigDecimal.class == toClass){
			String bdStr = arg1[0];
			
			if(StringUtils.isNotEmpty(bdStr) || StringUtils.isNotBlank(bdStr)){
				return new BigDecimal(bdStr);
			}
			
		}		
		return BigDecimal.ZERO;
	}

	@Override
	public String convertToString(Map arg0, Object o) {
		if(o instanceof BigDecimal){
			BigDecimal b = new BigDecimal(o.toString()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
			return b.toString();
		}		
		return o.toString();
	}

}
