package com.sun.demo.base.random;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Author by Sun, Date on 2020/9/7.
 * PS: Not easy to write code, please indicate.
 */
public class RandomTest {

    @Test
    public void start() {

        List<PrizeEntity> prizeList = new ArrayList<>();
        prizeList.add(new PrizeEntity("1", BigDecimal.valueOf(0.25), "长沙"));
        prizeList.add(new PrizeEntity("2", BigDecimal.valueOf(0.25), "厦门"));
        prizeList.add(new PrizeEntity("3", BigDecimal.valueOf(0.25), "珠海"));
        prizeList.add(new PrizeEntity("4", BigDecimal.valueOf(0.25), "家里蹲"));

        List<LotteryProbabilityModel> probability = getProbability(prizeList);
        LotteryProbabilityModel randomGet = randomGet(probability);

        System.out.println("随机结果为:" + randomGet);
    }


    /**
     * 拼接获取 奖品几率升序 list
     *
     * @param prizeList 奖品列表
     * @return
     */
    public List<LotteryProbabilityModel> getProbability(List<PrizeEntity> prizeList) {
        List<LotteryProbabilityModel> prizeProbabilityList = new ArrayList<>();
        BigDecimal mark = BigDecimal.ZERO;
        for (int i = 0; i < prizeList.size(); i++) {
            final PrizeEntity prize = prizeList.get(i);
            final BigDecimal pro = prize.getProbability();
            if (pro != null && pro.compareTo(BigDecimal.ZERO) > 0) {
                prizeProbabilityList.add(new LotteryProbabilityModel(prize, mark, mark.add(pro)));
                mark = mark.add(pro);
            }
        }
        return prizeProbabilityList;
    }

    /**
     * 按照 概率随机抽取
     *
     * @param prizeList
     * @return
     */
    private LotteryProbabilityModel randomGet(List<LotteryProbabilityModel> prizeList) {
        final BigDecimal random = BigDecimal.valueOf(RandomUtils.nextDouble(0d, 1d));
        final int index = prizeList.indexOf(new LotteryProbabilityModel(random, random));
        if (index >= 0 && index < prizeList.size()) {
            return prizeList.get(index);
        }
        return null;
    }

}
