package bsuir.chernikov.main.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapper<T> {
    private List<T> objects;
    private Integer foundAll;
    private Integer pagesAll;
}