package mars.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("mars.bookstore.mapper")
public class StartBookStore {
	public static void main(String[] args) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		SpringApplication.run(StartBookStore.class, args);
	}
}
