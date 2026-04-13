package com.example.LibraryAPI.dto;

import java.util.List;

public class BookV2Wrapper {
    private List<BookResponseV2> data;
    private String version;

    public BookV2Wrapper(List<BookResponseV2> data, String version) {
        this.data = data;
        this.version = version;
    }

    public List<BookResponseV2> getData(){
        return data;
    }

    public String getVersion(){
        return version;
    }
}
