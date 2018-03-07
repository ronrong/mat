package com.ronrong.thymeleaf.mat.context;

import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.entity.Module;
import com.ronrong.thymeleaf.mat.entity.Page;
import com.ronrong.thymeleaf.mat.entity.Shop;
import com.ronrong.thymeleaf.mat.entity.Template;
import com.ronrong.thymeleaf.mat.util.Validate;
import org.thymeleaf.context.IContext;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class StandardDecorationContextFactory implements IDecorationContextFactory {


    public StandardDecorationContextFactory() {
        super();
    }


    @Override
    public IDecorationContext createDecorationContext(
                final IDecorationConfiguration decorationConfiguration,
                final Shop shop,
                final Page page,
                final Module module,
                final Template template,
                final IContext context) {

        final Set<String> variableNames = context.getVariableNames();
        final Map<String,Object> variables = new LinkedHashMap<String, Object>(variableNames.size() + 1, 1.0f);
        for (final String variableName : variableNames) {
            variables.put(variableName, context.getVariable(variableName));
        }


        IDecorationContext decorationContext = new DecorationContext(decorationConfiguration, shop, page, module, template, variables);

        return decorationContext;
    }
}
