package cn.joymates.jxc.action.category;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.joymates.jxc.domain.Goods;
import cn.joymates.jxc.utils.CommonDataCache;

public class CascadeGoods {
	public List<Goods> getGoodsList(String id) {
		return CommonDataCache.GOODS.get(id);
	}
	
	/**
	 * 根据价格过滤商品
	 * @param id ： 商品分类id
	 * @param price : 商品价格
	 * @return
	 */
	public List<Goods> getGoodsListByPrice(String id, String price) {
		List<Goods> goodsList = getGoodsList(id);
		List<Goods> retList = new ArrayList<Goods>();
		for (Goods good : goodsList) {
			
			//包含小数点
			if (price.indexOf(".") != -1) {
				if (good.getSellPrice().equals(new BigDecimal(price))) {
					retList.add(good);
					continue;
				}
				
				//未找到相等的，截掉小数点后的数字
				price = price.substring(0, price.indexOf("."));
			}
			
			int gPrice = good.getSellPrice().intValue();
			if (gPrice == Integer.parseInt(price)) {
				retList.add(good);
			}
		}
		return retList;
	}
	
	/**
	 * 根据条形码获取商品信息
	 * @param barCode
	 * @return
	 */
	public Goods getGoodsByBarCode(String barCode) {
		if (StringUtils.isEmpty(barCode)) {
			return null;
		}
		
		for (Map.Entry<String, List<Goods>> entry : CommonDataCache.GOODS.entrySet()) {
			List<Goods> glist = entry.getValue();
			
			for (Goods g : glist) {
				if (barCode.equals(g.getBarCode())) {
					return g;
				}
			}
		}
		
		return null;
	}
	
}
