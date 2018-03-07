package com.ronrong.thymeleaf.mat.processor.element;

import com.ronrong.thymeleaf.mat.entity.CommonEnum;
import com.ronrong.thymeleaf.mat.exception.MatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * @author:rongshaolin
 */
public class TitleHeaderElementProcessor extends AbstractMatProcessor {

    private static final String ELEMENT_NAME = "titleHeader";//标签名
    private static final int PRECEDENCE = 300;//优先级
    private static final Logger logger = LoggerFactory.getLogger(SlideElementProcessor.class);

    public TitleHeaderElementProcessor(String dialectPrefix) {
        super(
                TemplateMode.HTML, // 此处理器将仅应用于HTML模式
                dialectPrefix, // 要应用于名称的匹配前缀
                ELEMENT_NAME, // 标签名称：匹配此名称的特定标签
                true, // 将标签前缀应用于标签名称
                null, // 无属性名称：将通过标签名称匹配
                false, // 没有要应用于属性名称的前缀
                PRECEDENCE); // 优先(内部方言自己的优先)
    }

    /**
     *
     * @param iTemplateContext 页面上下文
     * @param iProcessableElementTag 标签
     * @param iElementTagStructureHandler
     */
    @Override
    protected void doProcess(ITemplateContext iTemplateContext, IProcessableElementTag iProcessableElementTag, IElementTagStructureHandler iElementTagStructureHandler) {
        try {
            super.doProcess(iTemplateContext, iProcessableElementTag, iElementTagStructureHandler);

        } catch (MatException mate) { // 单个模块、模板解析失败不影响整体页面渲染
            logger.error("模块解析失败：{}，DecoratorType：{}", mate.getMessage(), getDecoratorType());
        } catch (Exception e) {
            logger.error("模块解析失败：", e);
        }

    }

    @Override
    public CommonEnum.DecoratorTypeEnum getDecoratorType() {
        return CommonEnum.DecoratorTypeEnum.TITLEHEADER;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

}