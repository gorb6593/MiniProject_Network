package com.btn;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.BtnMapper;
import com.vo.BtnVO;

@Repository("bdao")
public class BtnDao implements Dao<String, BtnVO> {
	
	@Autowired
	BtnMapper bm;
	
	@Override
	public void insert(BtnVO v) throws Exception {
		System.out.println("Dao오는지 체크");
		bm.insert(v);
	}

	@Override
	public void delete(String k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BtnVO v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BtnVO select(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BtnVO> select() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
