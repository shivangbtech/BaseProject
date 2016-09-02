package com.example.baseproject.models.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivang Goel on 21/7/16.
 */


public class BaseListResponse<T extends BaseResponse> extends BaseResponse {

    private List<T> mList;

    public BaseListResponse() {
        mList = new ArrayList<T>();
    }

    public List getList() {
        return mList;
    }

    public void setList(List<T> dataList) {
        mList = dataList;
    }
}
