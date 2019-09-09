package indi.tudan.uvnccall.sessionmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class UltravncSessionManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UltravncSessionManagerApplication.class, args);
    }

    /**
     * 视图解析配置（可以在配置文件中配置）
     *
     * @return InternalResourceViewResolver
     */
    @Bean
    public InternalResourceViewResolver htmlViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        return resolver;
    }

}