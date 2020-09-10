package com.sun.demo.base.random;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Author by Sun, Date on 2020/9/7.
 * PS: Not easy to write code, please indicate.
 */
@AllArgsConstructor
@Data
public class PrizeEntity {
    private String id;
    private BigDecimal probability;
    private String prizeName;

    @Override
    public String toString() {
        return "DsCommonPrizeEntity{" +
                "prizeName='" + prizeName + '\'' +
                '}';
    }
}
