package imgr.com.iManager_App.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;

@Configuration
@PropertySources(
{ @PropertySource("classpath:DestinationSuffixes.properties") })
public class DestinationsSuffixes
{

    @Bean
    // @Autowired // For PropertySourcesPlaceholderConfigurer
    public TY_DestinationsSuffix Destinations(@Value("${baseurl}") final String baseurl,
            @Value("${watchlistdburl}") final String watchlistdburl, @Value("${csrfsess}") final String csrfsess,
            @Value("${authenticate}") final String authurl)
    {
        TY_DestinationsSuffix destinationsSuffixes = new TY_DestinationsSuffix(baseurl, watchlistdburl, csrfsess,
                authurl);
        return destinationsSuffixes;
    }
}
