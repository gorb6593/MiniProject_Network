package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Service;
import com.vo.BtnVO;
import com.vo.LedVO;
import com.vo.UserVO;

@Controller
public class MainController {
	MyMqtt_Pub_client client;
	
	private Logger work_log = 
			Logger.getLogger("work"); 
	private Logger user_log = 
			Logger.getLogger("user"); 
	private Logger data_log = 
			Logger.getLogger("data"); 
	
	
	@Autowired
	@Qualifier("uservice")
	Service<String, UserVO> service;
	
	@Autowired
	@Qualifier("ledservice")
	Service<String, LedVO> service2;
	
	@Autowired
	@Qualifier("btnservice")
	Service<String, BtnVO> service3;
	
	@RequestMapping("/ledonadd.mc")
	public String ledonimpl(LedVO led) {			
		System.out.println("onadd컨트롤러?");
		try {
			service2.register(led);
			System.out.println("onadd컨트롤러에서넘어왔니?");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:led.mc?LED=on";
	}
	
	@RequestMapping("/ledoffadd.mc")
	public String ledoffimpl(LedVO led) {
		try {
		service2.register(led);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:led.mc?LED=off";
	}
	
	@RequestMapping("/main.mc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv;
	}
	public MainController() {
		client = new MyMqtt_Pub_client();
	}
	
	   @RequestMapping("/led.mc")
	   public ModelAndView led_show(HttpServletRequest request, HttpServletResponse response) throws IOException {
	      ModelAndView mv = new ModelAndView();
	      String LED = request.getParameter("LED");
	      System.out.println(LED);
	      data_log.debug(LED);
	      HttpSession session = request.getSession();
	      session.setAttribute("status", LED);
	      
	      mv.setViewName("led_check");
	      client.send("led","led_"+LED );
	      
	      //PrintWriter out = response.getWriter();
//	      String temp = request.getParameter("temp");
//	      double f_temp = Double.parseDouble(temp);
	      //System.out.println(f_temp);
	      //work_log.debug(f_temp);
//	      String btn = request.getParameter("btn");
//	      System.out.println("temp: "+temp+"btn: "+btn);
	      return mv;
	   }

	   @RequestMapping("/btn.mc")
		public ModelAndView btn(HttpServletRequest request) throws IOException{
		   	
//		   	service3.register(BTN);
			String btn = request.getParameter("btn");
			int i_btn = Integer.parseInt(btn);
			BtnVO btninfo = new BtnVO(i_btn);
			try {
				service3.register(btninfo);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println("Button Status : "+btn);
			String temp = request.getParameter("temp");
			double f_temp = Double.parseDouble(temp);
			System.out.println("Temp Status : "+temp+"ºC");
			ModelAndView mv = new ModelAndView();
			HttpSession session = request.getSession();
			session.setAttribute("btn", btn);
			mv.addObject("btn", btn);
			session.setAttribute("temp", temp);
			mv.addObject("temp", temp);
			mv.setViewName("ledtest/led_check");
			if(btn!=null) { 
				if(btn.equals(1+"")) { 
					try {
						FcmUtil_btn.sendServer(btn); 
						} catch (Exception e) {
							e.printStackTrace(); 
							}
						}else if(f_temp >= 28 ) { 
							try {
								FcmUtil_temp.sendServer(temp); 
								} catch (Exception e) {
									e.printStackTrace(); 
									}
								}  
				}
			return mv;
		}
	
	@RequestMapping("/iot1.mc")
	@ResponseBody
	public void iotdata(HttpServletRequest request) throws IOException {
		String temp = request.getParameter("temp");
		String humi = request.getParameter("humi");
		double f_temp = Double.parseDouble(temp);
		double f_humi = Double.parseDouble(humi);
		System.out.println(f_temp+" : "+f_humi);
		data_log.debug(f_temp+" : "+f_humi);
		
	}
	
	@RequestMapping("/iot2.mc")
	@ResponseBody
	public void iotdata2(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String temp = request.getParameter("temp");
		double f_temp = Double.parseDouble(temp);
		System.out.println(f_temp);
		work_log.debug(f_temp);
		
		if(f_temp >= 0 && f_temp <= 20 ) {
			out.print("0");	
		}else if(f_temp >= 21 && f_temp <= 30) {
			out.print("2");
		}else if(f_temp >= 31 && f_temp <= 40) {
			out.print("3");
		}
		
		out.close();
		
	}
	
	
	@RequestMapping("/uu.mc")
	@ResponseBody
	public void uu(HttpServletResponse response) throws IOException {
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ArrayList<UserVO> list = null;
		try {
			list = service.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray ja = new JSONArray();
		for(UserVO u:list) {
			JSONObject jo = new JSONObject();
			jo.put("id", u.getId());
			jo.put("pwd", u.getPwd());
			jo.put("name", u.getName());
			ja.add(jo);
		}
		out.print(ja.toJSONString());
		out.close();
	}
	
	@RequestMapping("/login.mc")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center", "login");
		mv.setViewName("main");
		return mv;
	}
	@RequestMapping("/register.mc")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center", "register");
		mv.setViewName("main");
		return mv;
	}
	@RequestMapping("/about.mc")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center", "about");
		mv.setViewName("main");
		return mv;
	}
	@RequestMapping("/logout.mc")
	public ModelAndView logout(
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = 
				request.getSession();
		if(session != null) {
			session.invalidate();
		}
		mv.setViewName("main");
		return mv;
	}
	
	
	@RequestMapping("/loginimpl.mc")
	public ModelAndView loginimpl(
			HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		ModelAndView mv = new ModelAndView();
		
		UserVO dbuser = null;
		try {
			dbuser = service.get(id);
			if(dbuser.getPwd().equals(pwd)) {
				mv.addObject("center", "ok");
				HttpSession session 
				= request.getSession();
				session.setAttribute("loginid", id);
			}else {
				mv.addObject("center", "fail");
			}
		} catch (Exception e) {
			mv.addObject("center", "fail");
			e.printStackTrace();
		}
		
		
		mv.setViewName("main");
		return mv;
	}
}





