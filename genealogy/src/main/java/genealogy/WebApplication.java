package genealogy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("genealogy.*")
public class WebApplication {

	public static void main(String[] args) {
	    System.setProperty("server.servlet.context-path", "/genealogyapp");
		SpringApplication.run(WebApplication.class, args);
	}

}
