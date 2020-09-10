package com.sun.demo.base.random;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Author: ZeRen.
 * @Date: 2020/7/14 20:07
 */
@Data
@AllArgsConstructor
public class LotteryProbabilityModel {
    // 奖品名称
    private String prizeName;
    // 奖品库存
    private Integer prizeCanUseNum;
    // 奖品ID
    private String prizeId;
    // 奖品实体
    @NonNull
    private PrizeEntity prizeEntity;

    private BigDecimal startProbability;
    private BigDecimal endProbability;

    public LotteryProbabilityModel(BigDecimal startProbability, BigDecimal endProbability) {
        this.startProbability = startProbability;
        this.endProbability = endProbability;
    }

    public LotteryProbabilityModel(@NonNull PrizeEntity prizeEntity, BigDecimal startProbability, BigDecimal endProbability) {
        this.prizeEntity = prizeEntity;
        this.startProbability = startProbability;
        this.endProbability = endProbability;

        this.prizeName = prizeEntity.getPrizeName();
        this.prizeId = prizeEntity.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotteryProbabilityModel that = (LotteryProbabilityModel) o;
        return startProbability.compareTo(that.startProbability) >= 0 &&
                endProbability.compareTo(that.endProbability) < 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startProbability, endProbability);
    }

    @Override
    public String toString() {
        return "LotteryProbabilityModel{" +
                "prizeName='" + prizeName + '\'' +
                '}';
    }
}
