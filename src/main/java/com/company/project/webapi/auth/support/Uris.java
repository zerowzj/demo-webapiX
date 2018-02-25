package com.company.project.webapi.auth.support;

import com.company.project.webapi.support.context.SpringContext;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

/**
 * URIs
 *
 * @author wangzhj
 */
public final class Uris {

    private static final Logger LOGGER = LoggerFactory.getLogger(Uris.class);

    private static Set<String> LEGAL_URI_SET = Sets.newHashSet();

    static {
        RequestMappingHandlerMapping handlerMapping = SpringContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> methodMap = handlerMapping.getHandlerMethods();
        for (RequestMappingInfo mappingInfo : methodMap.keySet()) {
            PatternsRequestCondition requestCond = mappingInfo.getPatternsCondition();
            Set<String> set = requestCond.getPatterns();
            LEGAL_URI_SET.addAll(set);
        }
        LOGGER.info("========== 共[ {} ]个接口 ==========", LEGAL_URI_SET.size());
        for (String uri : LEGAL_URI_SET) {
            LOGGER.info("[{}]", uri);
        }
    }

    /**
     * Uri是否合法
     *
     * @param uri
     * @return boolean
     */
    public static boolean isLegal(String uri) {
        return LEGAL_URI_SET.contains(uri);
    }
}
