package com.manusiaikan.bootcamp.model;

import java.util.Map;

public class DataTableRequest {
    private Integer draw, start, length, sortCol;
    private Map<String, Object> extraParam;
    private String sortDir;


    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getSortCol() {
        return sortCol;
    }

    public void setSortCol(Integer sortCol) {
        this.sortCol = sortCol;
    }

    public Map<String, Object> getExtraParam() {
        return extraParam;
    }

    public void setExtraParam(Map<String, Object> extraParam) {
        this.extraParam = extraParam;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }
}
