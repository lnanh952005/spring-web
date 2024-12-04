package com.javaweb.buildingproject.domain.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationDTO {
    private Meta meta;
    private Object result;

    @Getter
    @Setter
    @JsonPropertyOrder({"page", "pageSize", "pages", "total"})
    public static class Meta {
        private Integer page; //page : trang hiện tại (bắt đầu từ 0)
        private Integer PageSize; //limit : số lượng bản ghi trên mỗi trang (số lượng phần tử)
        private Integer pages;//totalPages : tông số trang với điều kiện query
        private Long total;//totalItem : tổng phần tử có trong DB
    }
}
