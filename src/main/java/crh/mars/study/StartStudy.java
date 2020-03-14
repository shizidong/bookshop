package crh.mars.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("crh.mars.study.mapper")
public class StartStudy {
	public static void main(String[] args) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		SpringApplication.run(StartStudy.class, args);
	}
}
