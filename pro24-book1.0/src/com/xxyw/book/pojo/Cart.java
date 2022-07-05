package com.xxyw.book.pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Integer, CartItem> cartItemMap;
    private Double totalMoney;
    private Integer totalCount;
    private Integer totalBookCount;

    public Cart() {
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        totalMoney = 0.0;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer, CartItem> entry : entries) {
                BigDecimal bCount = new BigDecimal(entry.getValue().getBuyCount());
                BigDecimal bPrice = BigDecimal.valueOf(entry.getValue().getBook().getPrice());
                totalMoney += bPrice.multiply(bCount).doubleValue();
            }
        }
        return totalMoney;
    }

    public Integer getTotalCount() {
        totalCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }

    public Integer getTotalBookCount() {
        totalBookCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer, CartItem> entry : entries) {
                totalBookCount += entry.getValue().getBuyCount();
            }
        }
        return totalBookCount;
    }
}
