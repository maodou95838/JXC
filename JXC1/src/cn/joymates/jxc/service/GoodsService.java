package cn.joymates.jxc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.jxc.dao.GoodsDao;
import cn.joymates.jxc.domain.Goods;

import com.future.existabc.util.UUIDGener;


public class GoodsService {
private GoodsDao goodsdao = new GoodsDao();
	
	public void save(Goods goods){
		goods.setGoodsId(UUIDGener.getUUID());
		goodsdao.insert(goods);
	}
	public void delete(String goods_id){
		goodsdao.delete(goods_id);
	}
	
	//该方法名更改，会影响缓存，请注意！
	public void update(Goods goods){
		goodsdao.update(goods);
	}
	public List<Goods> findAll(String ecRd, HttpServletRequest req,Goods goods ){
		return goodsdao.findAll(ecRd, req, goods);
	}
	public Goods findById(String goods_id){
		return goodsdao.findById(goods_id);
	}
	public List<Goods> find(){
		return goodsdao.find();
	}
	public List findByGoodsName(String cate_id){
		return goodsdao.findByGoodsName(cate_id);
	}
	/*根据goods_id查询库存量*/
	public Integer findCount(String goods_id){
		return goodsdao.findCount(goods_id);
	}
	/*修改库存量*/
	public void updateCount(Goods goods){
		goodsdao.updateCount(goods);
	}
	//根据goods_Id查询cate_Id和商品名称
	public Goods findByGoodsId(String goods_id){
		return goodsdao.findBygoodId(goods_id);
	}
}
