
package com.lesnyg.test2movieinfoproject.models;

import java.util.List;

public class Latest {

    private List<LatestResult> results = null;
    private int page;
    private int total_results;
    private Dates dates;
    private int total_pages;

    public List<LatestResult> getResults() {
        return results;
    }

    public void setResults(List<LatestResult> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
