package ${package}.common.restful;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuqingqian on 2019-07-12.
 */
@ToString
@Data
@NoArgsConstructor
public class PageDataModel<T> {

    protected List<T> list;

    protected PageModel page;

    public PageDataModel(int totalCount, int pageSize, int pageNum) {
        this.page = new PageModel();
        this.page.setPageNum(pageNum);
        this.page.setTotalCount(totalCount);
        this.page.setPageSize(pageSize);
    }

    public PageDataModel(PageCriteria pageCriteria) {
        this.page = new PageModel();
        this.page.setPageNum(pageCriteria.getPageNum());
        this.page.setPageSize(pageCriteria.getPageSize());
    }

    public void paging(List<T> totalList) {
        this.list = subList(totalList);
    }

    public void setList(List<T> pageList, int totalCount) {
        this.list = pageList;
        this.page.setTotalCount(totalCount);
        //计算总页数
        int pageSize = this.page.getPageSize();
        if (totalCount % pageSize == 0) {
            this.page.setTotalPage(totalCount / pageSize);
        } else {
            this.page.setTotalPage((totalCount / pageSize) + 1);
        }
    }

    private List<T> subList(List<T> totalList) {
        this.page.setTotalCount(totalList.size());

        int pageSize = this.page.getPageSize();
        int totalCount = this.page.getTotalCount();

        if (totalCount % pageSize == 0) {
            this.page.setTotalPage(totalCount / pageSize);
        } else {
            this.page.setTotalPage((totalCount / pageSize) + 1);
        }

        int from = this.page.getStart();
        int to = from + this.page.getPageSize();

        if (from >= totalList.size()) {
            return new ArrayList<>();
        }
        if (to > totalList.size()) {
            to = totalList.size();
        }
        return new ArrayList<>(totalList.subList(from, to));
    }
}
