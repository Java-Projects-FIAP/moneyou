package br.com.fiap.moneyou;
import br.com.fiap.moneyou.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*"); // Define para quais URLs o filtro será aplicado
        registrationBean.setOrder(0); // Prioridade do filtro (0 = mais alta)
        return registrationBean;
    }
}