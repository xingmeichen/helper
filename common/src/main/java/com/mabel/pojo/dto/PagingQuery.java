package com.mabel.pojo.dto;

import lombok.Data;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2022-05-22
 **/
@Data
public class PagingQuery {

    private int pageSize;
    private int pageNumber;
    private String orderBy;
    private boolean desc;
}