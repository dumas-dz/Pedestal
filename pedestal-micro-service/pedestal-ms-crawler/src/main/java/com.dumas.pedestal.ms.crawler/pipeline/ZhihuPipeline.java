package com.dumas.pedestal.ms.crawler.pipeline;

import com.dumas.pedestal.common.util.security.UUID;
import com.dumas.pedestal.ms.crawler.orm.entity.CmsContentDO;
import com.dumas.pedestal.ms.crawler.orm.mapper.CmsContentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 数据入库
 *
 * @author dumas
 * @date 2022/01/10 2:39 PM
 */
@Component
public class ZhihuPipeline implements Pipeline {
    private static final Logger logger = LoggerFactory.getLogger(ZhihuPipeline.class);

    @Resource
    private CmsContentDAO cmsContentDAO;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        String answer = resultItems.get("answer");

        CmsContentDO cmsContentDO = new CmsContentDO();
        cmsContentDO.setContentId(UUID.randomUUID().toString());
        cmsContentDO.setTitle(title);
        cmsContentDO.setContent(answer);
        cmsContentDO.setReleaseDate(LocalDateTime.now());

        try {
            cmsContentDAO.insert(cmsContentDO);
            logger.info("保存知乎文章成功,{}", title);
        } catch (Exception e) {
            logger.info("保存知乎文章失败,{}", e.getMessage());
        }
    }
}
