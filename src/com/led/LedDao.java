package com.led;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.LedMapper;
import com.vo.LedVO;

@Repository("ldao")
public class LedDao implements Dao<String, LedVO>{
	@Autowired
	LedMapper lm;

	@Override
	public void insert(LedVO v) throws Exception {
		System.out.println("dao인서트 들어왔니?");
		lm.insert(v);
	}

	@Override
	public void delete(String k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(LedVO v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LedVO select(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<LedVO> select() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
