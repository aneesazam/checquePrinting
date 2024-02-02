package com.checquePrinting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.HashMap;

@AllArgsConstructor
@Data
public class Pagination {
    private int pageSize = 10;
    private int pageNo = 0;
    private String searchText;
    private HashMap<String, String> sort;

    Pagination() {
        sort = new HashMap<String, String>();
    }
}
