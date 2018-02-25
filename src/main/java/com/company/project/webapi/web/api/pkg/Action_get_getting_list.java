package com.company.project.webapi.web.api.pkg;

import com.company.exception.entity.param.EmptyValueException;
import com.company.project.dao.packagebase.PackageBaseEO;
import com.company.project.service.pkg.PackageService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 获取待领取包裹列表
 *
 * @author wangzhj
 */
@Component
public class Action_get_getting_list extends BaseAction {

    @Autowired
    private PackageService packageService;

    //页码
    private Integer page_no;
    //页大小
    private Integer page_size;

    @Override
    public void checkData(RequestContext cxt, Map<String, Object> params) {
        page_no = getParam("page_no");
        if (page_no == null) {
            throw new EmptyValueException("page_no");
        }
        page_size = getParam("page_size");
        if (page_size == null || page_size == 0) {
            throw new EmptyValueException("page_size");
        }
    }

    @Override
    public Map<String, Object> execute(RequestContext cxt, Map<String, Object> params) {
        PageList<PackageBaseEO> pbEOLt = packageService.getPackagePageLt(cxt.getUbId(), null, "W", null, null, page_no, page_size);

        Map<String, Object> data = Maps.newHashMap();
        data.put("page_no", pbEOLt.getPaginator().getPage());
        data.put("page_size", pbEOLt.getPaginator().getLimit());
        data.put("total_page", pbEOLt.getPaginator().getTotalPages());
        data.put("page_data", makePageData(pbEOLt));
        return data;
    }

    private List<Map<String, Object>> makePageData(PageList<PackageBaseEO> pbEOLt) {
        List<Map<String, Object>> pageData = Lists.newArrayList();
        Map data;
        for (PackageBaseEO pbEO : pbEOLt) {
            data = Maps.newHashMap();

            data.put("package_id", pbEO.getPbId());
            data.put("receiver_phone", pbEO.getPbReceiverPhone());
            data.put("get_code", pbEO.getPbGetCode());

            pageData.add(data);
        }
        return pageData;
    }
}
