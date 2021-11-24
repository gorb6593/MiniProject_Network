package com.led;

import java.util.ArrayList;

import javax.annotation.Resource;

import com.frame.Dao;
import com.frame.Service;
import com.vo.LedVO;
@org.springframework.stereotype.Service("ledservice")
public class LedService implements Service<String, LedVO> {
	
	@Resource(name="ldao")
	Dao<String, LedVO> dao;
	
	@Override
	public void register(LedVO v) throws Exception {
		System.out.println("서비스레지스터 들어왔니?");
		dao.insert(v);
	}

	@Override
	public void remove(String k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(LedVO v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LedVO get(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<LedVO> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
