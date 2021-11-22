package test;

import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Service;
import com.vo.Product;
import com.vo.UserVO;



public class ProductsInserTest {

	public static void main(String[] args) {
		// Start Spring Container
		AbstractApplicationContext factory
		= new GenericXmlApplicationContext("myspring.xml");
		
		Service<String,Product> biz = 
			(Service)factory.getBean("pbiz");
		Product product = new Product(123, "¿Âµø∞«", 123, "abc.jpg");
		try {
			biz.register(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		factory.close();
	}

}



