package cn.joymates.jxc.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.dao.ecside.DBOperationDAO;
import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Member;
import cn.joymates.jxc.domain.MemberPack;
import cn.joymates.jxc.domain.PackAwayDetail;

public class MemberPackDao {
	/**
	 * 条件查询
	 * @return
	 */
	public List findByCond(String ecRd, HttpServletRequest req, Member m, MemberPack p) {
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder sb = new StringBuilder();
		
		if (m != null) {
			if (StringUtils.isNotEmpty(m.getCellPhone())) {
				sb.append(" and cell_phone='" + m.getCellPhone() + "' ");
			}
			
			if (StringUtils.isNotEmpty(m.getName())) {
				sb.append(" and name='" + m.getName() + "' ");
			}
			
			if (StringUtils.isNotEmpty(m.getGradeId())) {
				sb.append(" and grade_id='" + m.getGradeId() + "' ");
			}
			
			if (StringUtils.isNotEmpty(m.getMemberCode())) {
				sb.append(" and member_code='" + m.getMemberCode() + "' ");
			}
		}
		
		if (p != null) {
			if (StringUtils.isNotEmpty(p.getGoodsId()) && !"-1".equals(p.getGoodsId())) {
				sb.append(" and goods_id=" + p.getGoodsId() + "' ");
			}
			
			if (p.getLeftDate() != null) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				sb.append(" and left_date='" + df.format(p.getLeftDate()) + "' ");
			}
			
			//0:用户没有选择
			if (p.getLeftCount1() != 0) {
				sb.append(" and left_count1 != 0");
			}
		}
		
		String searchsql = "select count(1) from goods_tb g right join (" +
				   " select m.grade_id, m.name, m.cell_phone,m.member_uuid, p.pack_uuid, p.goods_id," +
				   " p.left_date, p.left_count, p.left_count1,p.last_away_time,p.last_away_count " +
				   " from member_tb m left join member_pack_tb p  on p.member_uuid = m.member_uuid) t " +
				   " on g.goods_id = t.goods_id where 1=1 "  + sb.toString();
		
		String resultsql = "select g.goods_id,g.goods_name, t.* from goods_tb g right join (" +
						   " select m.grade_id, m.name, m.cell_phone, m.member_uuid, p.pack_uuid, p.goods_id," +
						   " p.left_date, p.left_count, p.left_count1,p.last_away_time,p.last_away_count " +
						   " from member_tb m left join member_pack_tb p  on p.member_uuid = m.member_uuid) t " +
						   " on g.goods_id = t.goods_id where 1=1 " + 
						   sb.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	
	public List<MemberPack> findByGoodsIdAndMemberId(MemberPack p) {
		return sess.selectList("selectByMemberIdAndGoodsId", p);
	}
	
	public void insertMemberPack(MemberPack p) {
		sess.insert("insertMemberPack", p);
	}
	
	public MemberPack findMemberPackById(MemberPack p) {
		return sess.selectOne("selectMemberPackById", p);
	}
	
	public void updateMemberPack(MemberPack p) {
		//存单
		if (p.getPurOrGet().equals(MemberPack.PUT)) {
			sess.update("updateMemberPack1", p);
		//取单	
		} else {
			sess.update("updateMemberPack2", p);
		}
	}
	
	/**
	 * 插入存取流水
	 * @param detail
	 */
	public void insertDetail(PackAwayDetail detail) {
		sess.insert("insertPackDetail", detail);
	}
	
	/**
	 * 查询存取单详情
	 * @param detail
	 * @return
	 */
	public Map<String, Object> findPackDetailByPackId(PackAwayDetail detail) {
		List<Map<String, Object>> retList = sess.selectList("selectDetailByPackId", detail);
		if (retList != null && !retList.isEmpty()) {
			return retList.get(0);
		}
		
		return null;
	}
	
	public void updateDetail(PackAwayDetail detail) {
		sess.update("updateDetail", detail);
	}
	
	public void deleteMemberPack(MemberPack pack) {
		sess.delete("deleteMemberPack", pack.getPackUuid());
	}
	
	public void updateFk4Batch(PackAwayDetail detail) {
		sess.update("updatePackDetailBatch", detail);
	}
	
	private SqlSession sess = SessionFactoryUtil.getSession();
}
