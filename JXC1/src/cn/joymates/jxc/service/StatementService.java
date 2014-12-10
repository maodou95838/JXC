package cn.joymates.jxc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.jxc.dao.StatementDao;
import cn.joymates.jxc.domain.Sellbill;

public class StatementService {
	private StatementDao statementdao = new StatementDao();
	public List  findAll(String ecRd, HttpServletRequest req,Sellbill sellbill){
		return statementdao.findAll(ecRd, req, sellbill);
	}
}
