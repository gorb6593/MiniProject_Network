package com.btn;

import java.util.ArrayList;

import javax.annotation.Resource;

import com.frame.Dao;
import com.frame.Service;
import com.vo.BtnVO;

@org.springframework.stereotype.Service("btnservice")
public class BtnService implements Service<String, BtnVO>{

	@Resource(name="bdao")
	Dao<String, BtnVO> dao;
	
	@Override
	public void register(BtnVO v) throws Exception {
		System.out.println("서비스단 오는거 체크");
		dao.insert(v);
	}

	@Override
	public void remove(String k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(BtnVO v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BtnVO get(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BtnVO> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
