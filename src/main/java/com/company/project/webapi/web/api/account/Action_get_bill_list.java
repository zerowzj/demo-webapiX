package com.company.project.webapi.web.api.account;

import com.company.exception.entity.param.EmptyValueException;
import com.company.project.dao.accounttransferlog.AccountTransferLogEO;
import com.company.project.service.account.AccountService;
import com.company.project.webapi.support.action.BaseAction;
import com.company.project.webapi.support.context.RequestContext;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 获取账单列表
 *
 * @author wangzhj
 */
@Component
public class Action_get_bill_list extends BaseAction {

    @Autowired
    private AccountService accountService;

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

        PageList<AccountTransferLogEO> atlEOLt = accountService.getBillPageLt(cxt.getUbId(), page_no, page_size);

        Map<String, Object> data = Maps.newHashMap();
        data.put("page_no", atlEOLt.getPaginator().getPage());
        data.put("page_size", atlEOLt.getPaginator().getLimit());
        data.put("total_page", atlEOLt.getPaginator().getTotalPages());
        data.put("page_data", makeData(atlEOLt));
        return data;
    }

    private List<Map<String, Object>> makeData(List<AccountTransferLogEO> atlEOLt) {
        List<Map<String, Object>> pageData = Lists.newArrayList();
        Map<String, Object> data;
        for (AccountTransferLogEO atlEO : atlEOLt) {
            data = Maps.newHashMap();
            String name;
            if (atlEO.getAtlFromUbId() == 1000004) {
                name = "-";
            } else {
                name = "+";
            }
            data.put("bill_name", name);
            data.put("bill_type", atlEO.getAtlType());
            //
            data.put("bill_amt", atlEO.getAtlAmt());
            //
            DateTime dt = new DateTime(atlEO.getAtlTime());
            data.put("bill_time", dt.toString("MM-dd"));
            data.put("day_of_week", "周" + dt.dayOfWeek().getAsString());

            pageData.add(data);
        }
        return pageData;
    }
}
