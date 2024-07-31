package com.example.file.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackages = {"org.example.core", "com.example.file", "org.example.log"})
public class MvcConfig implements WebMvcConfigurer {

    private String connectPath = "/image/**";
    private String resourcePath = "file:///C:/image/";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOriginPatterns(
                    "http://localhost:21007",
                    "http://limepiece.com:21007",
                    "http://limepiece.com",
                    "https://limepiece.com",
                    "http://www.limepiece.com",
                    "https://www.limepiece.com",
                    "http://localhost:12007"
            )
            .allowCredentials(true)
            .allowedMethods("GET", "POST", "OPTIONS")
            .allowedHeaders("authorization", "X-Auth-Token", "X-Requested-With", "Content-Type", "Original");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(false).dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));

        converters.add(byteArrayHttpMessageConverter());
    }

    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
//        arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
        return arrayHttpMessageConverter;
    }

    private List<MediaType> getSupportedMediaTypes() {
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.IMAGE_JPEG);
        list.add(MediaType.IMAGE_PNG);
        list.add(MediaType.APPLICATION_OCTET_STREAM);
        list.add(MediaType.TEXT_HTML);
        return list;
    }
}
