package com.spring;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

public class ResourceTest {
    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("buy.lua");
        System.out.println(classPathResource.exists());

        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource1 =  defaultResourceLoader.getResource("classpath:buy.lua");
        System.out.println(resource1.getFilename());

        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = pathMatchingResourcePatternResolver.getResources("classpath*:*.lua");
        System.out.println(resources.length);


    }
}
