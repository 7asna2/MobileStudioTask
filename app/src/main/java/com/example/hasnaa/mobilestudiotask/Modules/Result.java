package com.example.hasnaa.mobilestudiotask.Modules;

import java.util.List;

import com.example.hasnaa.mobilestudiotask.NewPackage.New;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Hasnaa on 28-10-2017.
 */


public class Result implements New<Movie> {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<Movie> results = null;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public void setResult(List<Movie> result) {
        this.results=result;
    }

    @Override
    public List<Movie> getResult() {
        return this.results;
    }
}
