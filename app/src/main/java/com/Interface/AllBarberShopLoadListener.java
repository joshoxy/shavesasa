package com.Interface;

import java.util.List;

public interface AllBarberShopLoadListener {
    void onAllBarberShopLoadSuccess(List<String> areaNameList);
    void onAllBarberShopLoadFailed(String message);
}
