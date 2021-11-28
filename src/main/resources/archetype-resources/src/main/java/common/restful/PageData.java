package ${package}.common.restful;

public class PageData<T extends Iterable> {

    private T list;

    private int total;

    public static <T extends Iterable> PageData<T> builder(){
        return new PageData<>();
    }

    public PageData<T> list(T list){
        this.list = list;
        return this;
    }

    public PageData<T> total(int total){
        this.total = total;
        return this;
    }

    private PageData() {
    }

    public PageData(T list, int total) {
        this.list = list;
        this.total = total;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageData{" +
                "list=" + list +
                ", total=" + total +
                '}';
    }
}
