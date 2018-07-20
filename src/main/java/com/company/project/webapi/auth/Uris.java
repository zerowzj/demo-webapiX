package com.company.project.webapi.auth;

import com.google.common.collect.Sets;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * URIs
 *
 * @author wangzhj
 */
public final class Uris {

    private static final Logger LOGGER = LoggerFactory.getLogger(Uris.class);

    /* 文件 */
    private static final String FILE = "uri.txt";
    /* 字符集 */
    private static final Charset CHARSET = Charset.forName("UTF-8");
    /* 加载周期 */
    private static final Long TIME_PERIOD = 1 * 5L;
    /* uri集合 */
    private static Set<String> LEGAL_URI_SET;

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
        load();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(TIME_PERIOD);
                        LOGGER.info("reload legal uri......");
                        load();
                    } catch (Exception ex) {
                    }
                }
            }
        }).start();
    }

    private static void load() {
        try {
            URL url = Resources.getResource(FILE);
            List<String> uriLt = Resources.readLines(url, CHARSET);
            LEGAL_URI_SET = Sets.newHashSet(uriLt);
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

    public static void main(String[] args) {
        System.out.println(LEGAL_URI_SET);
    }
}
