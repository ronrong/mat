package com.ronrong.thymeleaf.mat;

import com.ronrong.thymeleaf.mat.dialect.MatDialect;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressionParser;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.util.EvaluationUtils;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.thymeleaf.util.StringUtils.trim;
import static org.thymeleaf.util.Validate.notEmpty;
import static org.thymeleaf.util.Validate.notNull;

public final class ThymeleafFacade {

    private ThymeleafFacade() {
        throw new UnsupportedOperationException();
    }

    private static TemplateEngine templateEngine;

    static {
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setCacheable(false);
        templateResolver.setTemplateMode(TemplateMode.HTML);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        ThymeleafFacade.setTemplateEngine(templateEngine);
    }


    public static void setTemplateEngine(TemplateEngine templateEngine) {
        ThymeleafFacade.templateEngine = templateEngine;
    }

    public static String processThymeleafFile(String moduleName, IContext context) {
        return templateEngine.process(moduleName, context);
    }


}
