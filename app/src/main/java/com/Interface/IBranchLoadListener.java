package com.Interface;

import com.Model.Barbershop;

import java.util.List;

public interface IBranchLoadListener {
    void onBranchShopLoadSuccess(List<Barbershop> barbershopList);
    void onBranchLoadFailed(String message);
}
