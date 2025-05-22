package imgr.com.iManager_App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class IManagerAppApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(IManagerAppApplication.class, args);
	}

}
