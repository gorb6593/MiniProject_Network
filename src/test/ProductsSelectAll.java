package test;

import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Service;
import com.product.ProductBiz;
import com.vo.Product;



public class ProductsSelectAll {

	public static void main(String[] args) {
		// Start Spring Container
		AbstractApplicationContext factory
		= new GenericXmlApplicationContext("myspring.xml");
		
		Service<String,Product> biz = 
			(Service)factory.getBean("pbiz");
		ArrayList<Product> list = null;
		try {
			list = biz.get();
			for(Product p: list) {
				System.out.println(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		factory.close();
	}

}



