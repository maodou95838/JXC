package cn.joymates.jxc.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.jxc.dao.CategoryDao;
import cn.joymates.jxc.dao.MemberDao;
import cn.joymates.jxc.dao.MemberPackDao;
import cn.joymates.jxc.domain.Member;
import cn.joymates.jxc.domain.MemberPack;
import cn.joymates.jxc.domain.PackAwayDetail;

import com.future.existabc.util.UUIDGener;

public class MemberPackService {
	public List findByCond(String ecRd, HttpServletRequest req, Member m, MemberPack p) {
		return dao.findByCond(ecRd, req, m, p);
	}
	
	public Map<String, Object> showSaveUISrv(Member member) {
		MemberDao mdao = new MemberDao();
		CategoryDao cdao = new CategoryDao();
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("member", mdao.findById(member));
		
		String cateId = cdao.findCanPackedOne();
		retMap.put("packCateId", cateId);
		return retMap;
	}
	
	/**
	 * 保存用户存单
	 * 约定：一个会员、同一种商品，在数据库中仅存在一条存单信息，同时记录用户的存取单流水信息
	 * @param p
	 */
	public void saveMemberPack(MemberPack p) {
		//1.查询给会员是否曾经存过此商品
		List<MemberPack> list = dao.findByGoodsIdAndMemberId(p);
		MemberPack pack1 = (list != null && !list.isEmpty()) ? list.get(0) : null;
		p.setPackUuid(UUIDGener.getUUID());
		p.setLeftCount1(p.getLeftCount());
		p.setRegTime(new Date());
		
		//2.存在存单信息，就修改，不存在做保存
		if (pack1 != null) {
			p.setPackUuid(pack1.getPackUuid());
			p.setLeftCount1(pack1.getLeftCount1() + p.getLeftCount());
			p.setPurOrGet(MemberPack.PUT);
			dao.updateMemberPack(p);
		} else {
			dao.insertMemberPack(p);
		}
		
		//3.保存存取流水
		PackAwayDetail detail = new PackAwayDetail();
		detail.setAwayUuid(UUIDGener.getUUID());
		detail.setPackUuid(p.getPackUuid());
		detail.setPutGet(MemberPack.PUT);
		
		detail.setPutGetTime(new Date());
		detail.setPutGetCount(p.getLeftCount());
		detail.setRePerson(p.getRegPerson());
		detail.setRemark(p.getRemark());
		dao.insertDetail(detail);
		
	}
	
	public Map<String, Object> showModifyUISrv(Member member, MemberPack pack) {
		MemberDao mdao = new MemberDao();
		CategoryDao cdao = new CategoryDao();
		
		//member
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("member", mdao.findById(member));
		
		//goods list
		String cateId = cdao.findCanPackedOne();
		retMap.put("packCateId", cateId);
		
		pack = dao.findMemberPackById(pack);
		retMap.put("memberPack", pack);
		return retMap;
	}
	
	
	public void modify(MemberPack pack) {
		//1.存单维护
		//1.1 修改存单表
		//1.2 修改存取流水表
		//存单维护，不可更改商品名称
		PackAwayDetail detail = new PackAwayDetail();
		detail.setPackUuid(pack.getPackUuid());
		detail.setPutGet(pack.getPurOrGet());
		Map<String, Object> retMap = dao.findPackDetailByPackId(detail);
		
		String detailUUid = (String)retMap.get("away_uuid");
		BigDecimal leftCount = (BigDecimal)retMap.get("left_count");
		BigDecimal leftCount1 = (BigDecimal)retMap.get("left_count1");
		BigDecimal lastCount = (BigDecimal)retMap.get("last_away_count");
		BigDecimal result = null;
		
		if (pack.getPurOrGet().equals(MemberPack.PUT)) {
			detail.setPutGet(MemberPack.PUT);
			
			//计算剩余量  (剩余量 - 存入量) + 用户修改量
			result = (leftCount1.subtract(leftCount)).add(BigDecimal.valueOf(pack.getLeftCount()));
			detail.setPutGetCount(pack.getLeftCount());
		}
		
		if (pack.getPurOrGet().equals(MemberPack.GET)) {
			detail.setPutGet(MemberPack.GET);
			
			// (剩余量 + 上次取走两) - 用户输入量
			result = (leftCount1.add(lastCount)).subtract(BigDecimal.valueOf(pack.getLastAwayCount()));
			detail.setPutGetCount(pack.getLastAwayCount());
		}
		
		pack.setLeftCount1(result.intValue());
		dao.updateMemberPack(pack);
		
		detail.setAwayUuid(detailUUid);
		detail.setRemark(pack.getRemark());
		
		dao.updateDetail(detail);
//		//合并重复数据
//		List<MemberPack> list = dao.findByGoodsIdAndMemberId(pack);
//		MemberPack pack1 = (list != null && !list.isEmpty()) ? list.get(0) : null;
//		
//		//pack1是数据库中的老数据
//		if (pack1 != null) {
//			pack.setLastAwayCount(pack1.getLastAwayCount());
//			pack.setLastAwayTime(pack1.getLastAwayTime());
//			pack.setLeftCount1(pack1.getLeftCount1() + pack.getLeftCount());
//			
//			dao.deleteMemberPack(pack1);
//			
//			PackAwayDetail pad = new PackAwayDetail();
//			pad.setRemark(pack.getPackUuid());
//			pad.setRemark2(pack1.getPackUuid());
//			dao.updateFk4Batch(detail);
//		}
	}
	
	public void getPackAway(MemberPack pack, PackAwayDetail detail) {
		//1.insert pack_detail table
		detail.setAwayUuid(UUIDGener.getUUID());
		detail.setPackUuid(pack.getPackUuid());
		detail.setPutGetTime(new Date());
		
		detail.setPutGetCount(pack.getLastAwayCount());
		detail.setRemark(pack.getLastRemark());
		detail.setPutGet(MemberPack.GET);
		dao.insertDetail(detail);
		
		//2.update pack table 
		pack.setLeftCount1((pack.getLeftCount1() - pack.getLastAwayCount()));
		pack.setLastAwayTime(new Date());
		dao.updateMemberPack(pack);
	}
	
	private MemberPackDao dao = new MemberPackDao();

}
