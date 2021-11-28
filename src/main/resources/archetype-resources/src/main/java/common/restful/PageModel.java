package ${package}.common.restful;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by liuqingqian on 2018/11/30.
 */
@ToString
public class PageModel {
    /**
     * 当前页数
     */
    @Setter
    private Integer pageNum;
    /**
     * 页面显示数量
     */
    @Setter
    private Integer pageSize;
    /**
     * 总数量
     */
    @Getter
    @Setter
    private Integer totalCount;
    /**
     * 总页数
     */
    @Getter
    @Setter
    private Integer totalPage;


    public Integer getPageSize() {
        if (pageSize == null) {
            return Config.DEFAULT_SIZE;
        } else if (pageSize < Config.MIN_SIZE) {
            return Config.MIN_SIZE;
        } else if (pageSize > Config.MAX_SIZE) {
            return Config.MAX_SIZE;
        }
        return pageSize;
    }

    public Integer getPageNum() {
        if (pageNum == null || pageNum < Config.MIN_NUM) {
            return Config.MIN_NUM;
        }
        return pageNum;
    }

    public int getStart() {
        return (getPageNum() - 1) * getPageSize();
    }

    static class Config {
        public static final int MAX_SIZE = 50000;

        public static final int MIN_SIZE = 1;

        public static final int DEFAULT_SIZE = 20;

        public static final int MIN_NUM = 1;
    }
}



