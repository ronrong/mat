package com.ronrong.thymeleaf.mat.decoration.standard;

import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.context.IDecorationContext;
import com.ronrong.thymeleaf.mat.decoration.*;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.entity.Shop;
import com.ronrong.thymeleaf.mat.entity.CommonEnum;
import com.ronrong.thymeleaf.mat.entity.Module;
import com.ronrong.thymeleaf.mat.exception.MatExecuteException;
import com.ronrong.thymeleaf.mat.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 * @Description: 一个标准的装修公司
 * @author:rongshaolin
 */
public class StandardDecorationCompany extends AbstractDecorationCompany {


    private static final Logger logger = LoggerFactory.getLogger(StandardDecorationCompany.class);

    private DecorationManager decorationManager;


    private IDecorationAgent decorationAgent;

//    /**
//     * 为了快速找到装修工人；
//     * 一家店铺同一类模块只有一个装修工
//     */

    public StandardDecorationCompany(IDecorationAgent decorationAgent) {

        this.decorationAgent = decorationAgent;

    }

    @Override
    public DecorationManager getDecorationManager() {

        return this.decorationManager;


    }

    /**
     *
     * @param decorationStyle 装修风格
     * @param shop 装修店铺
     * @param module 装修模块
     * @param context 上下文
     * @return
     */
    @Override
    public AbstractDecorator createDecorator(DecorationStyle decorationStyle,Shop shop, Module module, IDecorationContext context) {

        AbstractDecorator decorator = null;
        // 检查是否存在此类型装修工

        CommonEnum.DecoratorTypeEnum decoratorType = module.getDecoratorTypeEnum();

        String moduleFlag = decoratorType.getModuleFlag();

        StringBuilder decoratorClassBuilder = new StringBuilder(this.getClass().getPackage().getName());
        decoratorClassBuilder.append(".");
        decoratorClassBuilder.append("Standard");
        decoratorClassBuilder.append(StringUtils.captureName(moduleFlag));
        decoratorClassBuilder.append("Decorator");

        Class clazz = null;
        try {
            clazz = Class.forName(decoratorClassBuilder.toString());
            Constructor clazzConstructor = clazz.getConstructor(IDecorationConfiguration.class, Shop.class, DecorationStyle.class);
            decorator = (AbstractDecorator) clazzConstructor.newInstance(this.getDecorationConfiguration(), shop, decorationStyle);

        } catch (Exception e) {
            logger.error(String.format("[createDecorator] Exception when create decorator \"%s\": %s", new Object[]{decoratorClassBuilder.toString(), e.getMessage()}), e);
            throw new MatExecuteException("Exception when create decorator", e);

        }
        return decorator;
    }


    public DecorationManager initManager() {

        /**
         * 初始化装修经理
         * 装修经理根据什么配置初始化
         * 在中介和装修公司配置好之后
         */
        this.decorationManager = new DecorationManager(this.getDecorationConfiguration());
        return this.decorationManager;
    }


    public IDecorationConfiguration getDecorationConfiguration() {
        return this.decorationAgent.getConfiguration();
    }

}