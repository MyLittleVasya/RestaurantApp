package com.example.chatrebuild.RequestBodies;

public class DishFilterRequest {
    int page;
    int low;
    int high;
    boolean sorting;

    public DishFilterRequest(int page, int low, int high, boolean sorting) {
        this.page = page;
        this.low = low;
        this.high = high;
        this.sorting = sorting;
    }

    public DishFilterRequest() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public boolean isSorting() {
        return sorting;
    }

    public void setSorting(boolean sorting) {
        this.sorting = sorting;
    }

}