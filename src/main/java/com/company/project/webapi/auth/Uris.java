package com.company.project.webapi.auth;

import com.google.common.collect.Sets;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * URIs
 *
 * @author wangzhj
 */
public final class Uris {

    private static final Logger LOGGER = LoggerFactory.getLogger(Uris.class);

    private static final String FILE = "uri.txt";

    private static Set<String> LEGAL_URI_SET = Sets.newHashSet();

//    static {
//        RequestMappingHandlerMapping handlerMapping = SpringContext.getBean(RequestMappingHandlerMapping.class);
//        Map<RequestMappingInfo, HandlerMethod> methodMap = handlerMapping.getHandlerMethods();
//        for (RequestMappingInfo mappingInfo : methodMap.keySet()) {
//            PatternsRequestCondition requestCond = mappingInfo.getPatternsCondition();
//            Set<String> set = requestCond.getPatterns();
//            LEGAL_URI_SET.addAll(set);
//        }
//        LOGGER.info("========== 共[ {} ]个接口 ==========", LEGAL_URI_SET.size());
//        for (String uri : LEGAL_URI_SET) {
//            LOGGER.info("[{}]", uri);
//        }
//    }

    static {
        try {
            URL url = Resources.getResource(FILE);
            CharSource charSource = Resources.asCharSource(url, Charset.defaultCharset());
            Reader reader = charSource.openStream();
            Configuration config = new PropertiesConfiguration();
        } catch (Exception ex) {
            LOGGER.error("", ex);
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
