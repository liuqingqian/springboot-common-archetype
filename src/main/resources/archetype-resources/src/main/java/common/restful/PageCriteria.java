package ${package}.common.restful;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by liuqingqian on 2019-07-12.
 */
@Data
public class PageCriteria {

    /**
     * 当前页数
     */
    @NotNull(message = "pageNum不能为空")
    protected Integer pageNum;

    /**
     * 页面显示数量
     */
    @NotNull(message = "pageSize不能为空")
    protected Integer pageSize;
}
