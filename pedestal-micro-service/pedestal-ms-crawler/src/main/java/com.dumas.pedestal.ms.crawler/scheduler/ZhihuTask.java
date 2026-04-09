package com.dumas.pedestal.ms.crawler.scheduler;

import com.dumas.pedestal.ms.crawler.pipeline.ZhihuPipeline;
import com.dumas.pedestal.ms.crawler.processor.ZhihuPageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import jakarta.annotation.Resource;
import java.util.concurrent.*;

/**
 * 爬虫定时任务
 *
 * @author dumas
 * @date 2022/01/10 2:47 PM
 */
@Component
public class ZhihuTask {
    private static final Logger logger = LoggerFactory.getLogger(ZhihuTask.class);

    @Resource
    private ZhihuPipeline zhihuPipeline;

    @Resource
    private ZhihuPageProcessor zhihuPageProcessor;

    private ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

    public void crawl() {
        // 定时任务，每十分钟爬取一次
        timer.scheduleAtFixedRate(() -> {
            Thread.currentThread().setName("zhihuCrawlerThread");

            try {
                Spider.create(zhihuPageProcessor)
                        // 从https://www.zhihu.com/explore开始抓
                        .addUrl("https://www.zhihu.com/explore")
                        // 抓取到的数据存数据库
                        .addPipeline(zhihuPipeline)
                        // 开启2个线程抓取
                        .thread(2)
                        // 异步启动爬虫
                        .start();
            } catch (Exception e) {
                logger.info("定时抓取知乎数据线程执行异常,{}", e.getMessage());
            }
        }, 0, 10, TimeUnit.MINUTES);
    }
}
