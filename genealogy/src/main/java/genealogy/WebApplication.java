package genealogy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan("genealogy.*")
@ImportResource("classpath:application.xml")
public class WebApplication {

	public static void main(String[] args) {
	    System.setProperty("server.servlet.context-path", "/genealogyapp");
		SpringApplication.run(WebApplication.class, args);
	}

}
