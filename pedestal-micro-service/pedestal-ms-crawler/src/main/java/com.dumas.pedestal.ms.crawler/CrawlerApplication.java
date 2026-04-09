package com.dumas.pedestal.ms.crawler;

import com.dumas.pedestal.ms.crawler.scheduler.ZhihuTask;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import jakarta.annotation.Resource;

/**
 * 爬虫业务
 *
 * @author dumas
 * @date 2021/12/09 2:16 PM
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.dumas.pedestal"})
public class CrawlerApplication implements CommandLineRunner {

    @Resource
    private ZhihuTask zhihuTask;

    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        zhihuTask.crawl();
    }
}
