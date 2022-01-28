package com.manusiaikan.bootcamp.model;

import java.util.List;

public class DataTableResponse <T> {

    private List<T> data;
    private Integer draw;
    private Long recordsFiltered, RecordsTotal;


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Long getRecordsTotal() {
        return RecordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        RecordsTotal = recordsTotal;
    }
}
