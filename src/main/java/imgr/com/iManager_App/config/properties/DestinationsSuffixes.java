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
    public TY_DestinationsSuffix Destinations(@Value("${baseurl}") final String baseurl,
            @Value("${watchlistdburl}") final String watchlistdburl,
            @Value("${watchlistcmpurl}") final String watchlistcmpurl,
            @Value("${watchlistfundamentals}") final String watchlistfundamentals,
            @Value("${screenertoken}") final String screenertoken,
            @Value("${watchlistthesis}") final String watchlistthesis, @Value("${csrfsess}") final String csrfsess,
            @Value("${authenticate}") final String authurl, @Value("${watchlistplain}") final String watchlistplain,
            @Value("${pf}") final String pf, @Value("${pfUpload}") final String pfUpload,
            @Value("${watchlistaddscrip}") final String watchlistaddscrip)
    {
        TY_DestinationsSuffix destinationsSuffixes = new TY_DestinationsSuffix(baseurl, watchlistdburl, watchlistcmpurl,
                watchlistfundamentals, screenertoken, watchlistthesis, csrfsess, authurl, watchlistplain, pf, pfUpload,
                watchlistaddscrip);
        return destinationsSuffixes;
    }
}
