import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import java.util.Locale;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.exist.ecc") 
public class MvcConfig extends WebMvcConfigurerAdapter {

   	@Bean  
    public InternalResourceViewResolver viewResolver() {  
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();  
            resolver.setPrefix("/WEB-INF/views/");  
            resolver.setSuffix(".jsp");
            return resolver;  
    }
 
   @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

   @Bean
   public LocaleResolver localeResolver(){
      CookieLocaleResolver resolver = new CookieLocaleResolver();
      resolver.setDefaultLocale(new Locale("en"));
      return resolver;
   }

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
      interceptor.setParamName("lang");
      registry.addInterceptor(interceptor);
   }

   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
   } 
}