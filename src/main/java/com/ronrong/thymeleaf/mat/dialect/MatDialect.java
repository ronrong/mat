package com.ronrong.thymeleaf.mat.dialect;

import com.ronrong.thymeleaf.mat.decoration.IDecorationAgent;
import com.ronrong.thymeleaf.mat.processor.element.*;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.LinkedHashSet;
import java.util.Set;

public class MatDialect extends AbstractProcessorDialect {
    private static final String NAME = "Mat";
    private static final String PREFIX = "mat";

    private final IDecorationAgent decorationAgent;

    public MatDialect(IDecorationAgent decorationAgent) {
        super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);

        this.decorationAgent = decorationAgent;
    }

    public Set<IProcessor> getProcessors(String dialectPrefix) {
        return createStandardProcessorsSet(dialectPrefix);
    }

    private static Set<IProcessor> createStandardProcessorsSet(String dialectPrefix) {
        LinkedHashSet<IProcessor> processors = new LinkedHashSet<IProcessor>();
        processors.add(new HeaderElementProcessor(dialectPrefix));
        processors.add(new FooterElementProcessor(dialectPrefix));
        processors.add(new SlideElementProcessor(dialectPrefix));
        processors.add(new TitleElementProcessor(dialectPrefix));
        processors.add(new TitleHeaderElementProcessor(dialectPrefix));
        processors.add(new TitleNavElementProcessor(dialectPrefix));
        processors.add(new ProductElementProcessor(dialectPrefix));
        processors.add(new BottomNavBarElementProcessor(dialectPrefix));

        return processors;
    }
}
