package com.sun.demo.io;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author by Sun, Date on 2020/2/22.
 * PS: Not easy to write code, please indicate.
 * 构建sql脚本
 */
public class BuilderSql {

    private static final String SHOP_ID_MARK = "#{shopId}";
    private static final String COMPANY_ID_MARK = "#{companyId}";

    // 商品ID
    private static final String GOODS_ID = "bf9a08153f0e4b52a2cc2cb0869d3352";
    // 规格ID
    private static final String SPEC_ID = "efce4481baab4f57beee5da760acc223";

    // 楼层生成记录模板
    private static final String INSERT_FLOOR_SQL_TEMPLATE =
            "INSERT INTO ds_shop_floor_goods ( `id`, `floor_id`, `goods_id`, `sort_`, `shop_id`, `user_id`, `update_time`, `update_by`, `specification_id`, `company_id` ) VALUES ( uuid(), '6f1965a64c9d4acba02e7ce523ea2bc8', '" + GOODS_ID + "', '0', '" + SHOP_ID_MARK + "', NULL, now(), NULL, '" + SPEC_ID + "', '" + COMPANY_ID_MARK + "' );";

    // 推荐表生成记录模板
    private static final String INSERT_RECOMMEND_SQL_TEMPLATE =
            "INSERT INTO `ds_goods_info_recommend` ( `id`, `delivery`, `num`, `company_id`, `goods_id`, `specifications_id`, `create_date`, `update_date`, `check_time`, `place_status`, `shelf_status` ) VALUES ( uuid(), '2', '0', '#{companyId}', 'bf9a08153f0e4b52a2cc2cb0869d3352', '" + SPEC_ID + "',  now(), now(), now(), '1', '1' );";

    public static void main(String[] args) throws IOException {
        String outputPath = "C:\\Users\\Sun\\Desktop";

        ClassPathResource resource = new ClassPathResource("file/excel所有经销商信息");
        List<String> companyList = IOUtils.readLines(resource.getInputStream(), "UTF-8");

        List<String> floorSqlList = new ArrayList<>();
        List<String> recommendSqlList = new ArrayList<>();
        replaceMark(companyList, floorSqlList, INSERT_FLOOR_SQL_TEMPLATE);
        replaceMark(companyList, recommendSqlList, INSERT_RECOMMEND_SQL_TEMPLATE);

        String floorSql = StringUtils.join(floorSqlList, "\n");
        String recommendSql = StringUtils.join(recommendSqlList, "\n");

        IOUtils.write(floorSql, new FileOutputStream(outputPath + "\\商品楼层脚本.sql"), "UTF-8");
        IOUtils.write(recommendSql, new FileOutputStream(outputPath + "\\推荐商品表脚本.sql"), "UTF-8");
    }

    private static void replaceMark(List<String> companyList, List<String> realSqlList, String sqlTemplate) {
        for (String companyInfo : companyList) {
            if (StringUtils.isEmpty(companyInfo)) {
                continue;
            }
            if (companyInfo.startsWith("-")) {
                continue;
            }
            String[] infos = companyInfo.split(",");
            String shopId = infos[0];
            String companyId = infos[1];

            String realSql = sqlTemplate.replace(SHOP_ID_MARK, shopId).replace(COMPANY_ID_MARK, companyId);
            realSqlList.add(realSql);
        }
    }


    /**
     * 查找文本中的不同信息
     */
    @Test
    public void findDifferent() throws IOException {

        ClassPathResource resource = new ClassPathResource("file/excel待导入经销商信息");
        ClassPathResource exist = new ClassPathResource("file/存在的经销商");
        List<String> companyList = IOUtils.readLines(resource.getInputStream(), "UTF-8");
        List<String> existList = IOUtils.readLines(exist.getInputStream(), "UTF-8");

        for (String code : companyList) {
            if (!existList.contains(code)) {
                System.out.println(code);
            }
        }
    }
}
