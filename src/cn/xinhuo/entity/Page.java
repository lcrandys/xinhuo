package cn.xinhuo.entity;

public class Page {

    private int currentPage;
    private int totlePage;
    private int pageSize = 30;
    private int totleCount;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage == 0) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    public int getTotlePage() {
        return totlePage;
    }

    public void setTotlePage(int totlePage) {
        this.totlePage = totlePage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotleCount() {
        return totleCount;
    }

    public void setTotleCount(int totleCount) {
        this.totleCount = totleCount;
        int TotlePage = (this.totleCount % this.pageSize) > 0 ? this.totleCount / this.pageSize
                : (this.totleCount / this.pageSize) + 1;
        setTotlePage(TotlePage);
    }
}
