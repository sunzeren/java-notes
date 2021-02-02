package com.sun.demo.io.log.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.StringJoiner;

/**
 * 中台请求日志响应Model
 */
@NoArgsConstructor
@Data
public class PlatformLogRsp {

    /**
     * status : 1
     * msg : 操作成功
     * data : {"rows":[{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.511","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"26591612237985511","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[21983] lng[121.40698269314237] lat[31.323970811631945] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.499","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"320531612237985499","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[45182] lng[116.61191053602431] lat[23.26449273003472] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.425","className":"com.candao.dms2.horseman.service.impl.HorsemanCensusOrderService","methodName":"updateByEvent","clientType":0,"costTime":0,"logId":"320511612237985425","ip":"","clientIp":"","level":"INFO","msg":"骑手更新当前订单:horsemanId[46838] orderId[118183358] event[COMPLETE] info[{\"beforeHorsemanId\":0,\"beforeOrderStatus\":4,\"event\":\"COMPLETE\",\"horsemanId\":46838,\"orderId\":118183358}]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.339","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"26301612237985339","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[28481] lng[121.41329535590278] lat[31.252731119791665] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.311","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"3321612237985311","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[21550] lng[115.983275] lat[40.4661] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.299","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"26251612237985299","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[12466] lng[118.12155192057291] lat[24.49246826171875] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.255","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"741612237985255","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[43759] lng[117.5019140625] lat[39.048946397569445] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"order-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.250","className":"com.candao.dms2.order.service.impl.OrderStatusService","methodName":"assignOrderAsyncHandle","clientType":0,"costTime":0,"logId":"23880091612237985250","ip":"","clientIp":"","level":"INFO","msg":"自动指派订单：指派订单:[1980105-02/02/2021-6008] 给骑手[张明河]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"order-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.248","className":"com.candao.dms2.order.service.impl.OrderCountServiceImpl","methodName":"lambda$null$0","clientType":0,"costTime":0,"logId":"23906731612237985248","ip":"","clientIp":"","level":"INFO","msg":"订单查询状态计数修改成功,storeId=[11143],oldQueryStatus=[2],nowQueryStatus=[3],耗时：[2ms]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.248","className":"com.candao.dms2.horseman.service.impl.HorsemanStatusService","methodName":"syncAssignOrders","clientType":0,"costTime":0,"logId":"26561612237985248","ip":"","clientIp":"","level":"INFO","msg":"指派订单[118191202],骑手id[10681],最近接单时间[Optional[2021-02-02 11:53:05.244]]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.234","className":"com.candao.dms2.horseman.service.impl.HorsemanStatusService","methodName":"syncHorsemanData","clientType":0,"costTime":0,"logId":"320621612237985234","ip":"","clientIp":"","level":"INFO","msg":"同步骑手请求:骑手id[33191] 订单id[118189659] 订单请求参数[OrderStatusChangeSyncHorseman{orderStatus=31, horsemanId=33191, orderId=118189659, markExceptionType=0, forceComplete=0}]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.214","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"320631612237985214","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[17182] lng[118.13817301432292] lat[24.508895399305555] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.193","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"26241612237985193","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[41927] lng[121.5021302625868] lat[31.25814914279514] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"order-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.187","className":"com.candao.dms2.order.service.impl.OrderStatusService","methodName":"saveOrderOtherInfoToCaches","clientType":0,"costTime":0,"logId":"23889931612237985187","ip":"","clientIp":"","level":"INFO","msg":"(请求结果)同步配送关单信息保存到缓存:ID[118188152],关单信息[OrderOtherInfo{closeOrderDistance=266, isAdvanceCloseOrder=1, closeLng=121.35080159505209, closeLat=31.1717724609375, createTagTime=1612237883277}]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.169","className":"com.candao.dms2.horseman.service.impl.HorsemanStatusService","methodName":"lambda$syncHorsemanData$3","clientType":0,"costTime":0,"logId":"4671612237985169","ip":"","clientIp":"","level":"INFO","msg":"开始同步骑手:骑手id[23682] 订单id[118188152] 订单请求参数[OrderStatusChangeSyncHorseman{orderStatus=100, horsemanId=23682, orderId=118188152, markExceptionType=0, forceComplete=0}] 骑手当前订单[HorsemanCurrentOrderImpl { horsemanId = 23682, currentAssignOrders = , currentDeliveryOrders = 118187593,118188152,118188153, currentKeepOrders = , currentCancelOrders =  }] 骑手[HorsemanImpl { id = 23682, tokenId = 56, number = anyu_15000884030, loginName = anyu_15000884030, password = 47e6abfbed45f83d244e241ef543db77, horsemanName = 刘成强, horsemanPhone = 15000884030, vehicleId = 1145, status = 1, workStatus = 1, deliveryStatus = 3, networkStatus = 1, lastOnlineTime = 2020-12-31 08:14:19.0, horsemanType = 1, horsemanSource = 0, roleId = null, lastOrder = 2021-02-02 11:44:03.48, lastWait = 2021-02-02 11:49:09.854, currentAssignOrders = , currentDeliveryOrders = , currentKeepOrders = , currentCancelOrders = , currentBindStoreIds = 8453, currentBindStoreName = 上海麦当劳航北路餐厅, completeOrders = 18, exceptionOrders = 0, forwardOrderNum = 0, sessionId = dc8506ca17e74718adf30f038e4bd023_anyu_15000884030, pushToken = , pushTokenAndroid = 120c83f76091cd25d75, ruleId = 0, remarkBoms = , canDelivery = 0, createBy = anyu_13482148769, createTime = 2019-08-20 13:54:39.0, updateBy = cw, updateTime = 2020-11-23 16:43:09.0, managerRoleId = 2699 }]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"order-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.156","className":"com.candao.dms2.order.service.impl.OrderStatusService","methodName":"deliveryCompleteV2","clientType":0,"costTime":0,"logId":"12271021612237985156","ip":"","clientIp":"","level":"INFO","msg":"配送完成-上传信息 骑手id[23682]订单ID[118188152]完成事件[拥有完成订单距离标识]订单实际地址坐标和订单坐标有误差[false]订单维度[31.174146]订单经度[121.350449]骑手符合规则时的维度[31.1717724609375]骑手符合规则时的经度[121.35080159505209]APP判断的关单距离[266]APP创建标识时间[1612237883277]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"order-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.134","className":"com.candao.dms2.order.service.impl.ExceptionService$2","methodName":"run","clientType":0,"costTime":0,"logId":"23906691612237985134","ip":"","clientIp":"","level":"INFO","msg":"消息读取orderId[118181280] 访问消息中心，更新列表消息为 骑手长 已读","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.133","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"731612237985133","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[46439] lng[121.511867] lat[31.197294] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.127","className":"com.candao.dms2.horseman.service.impl.HorsemanStatusService","methodName":"lambda$orderChangeHorseman$2","clientType":0,"costTime":0,"logId":"4371612237985127","ip":"","clientIp":"","level":"INFO","msg":"订单被改派:原骑手id[22141] 移除该订单[118191146]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.106","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"3301612237985105","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[31313] lng[113.34719807942709] lat[23.00708523220486] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null}],"pages":2303742,"total":46074830,"page":1,"pageSize":20}
     * serverTime : 1612237988616
     */

    private int status;
    private String msg;
    private DataBean data;
    private long serverTime;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * rows : [{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.511","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"26591612237985511","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[21983] lng[121.40698269314237] lat[31.323970811631945] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.499","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"320531612237985499","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[45182] lng[116.61191053602431] lat[23.26449273003472] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.425","className":"com.candao.dms2.horseman.service.impl.HorsemanCensusOrderService","methodName":"updateByEvent","clientType":0,"costTime":0,"logId":"320511612237985425","ip":"","clientIp":"","level":"INFO","msg":"骑手更新当前订单:horsemanId[46838] orderId[118183358] event[COMPLETE] info[{\"beforeHorsemanId\":0,\"beforeOrderStatus\":4,\"event\":\"COMPLETE\",\"horsemanId\":46838,\"orderId\":118183358}]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.339","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"26301612237985339","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[28481] lng[121.41329535590278] lat[31.252731119791665] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.311","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"3321612237985311","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[21550] lng[115.983275] lat[40.4661] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.299","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"26251612237985299","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[12466] lng[118.12155192057291] lat[24.49246826171875] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.255","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"741612237985255","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[43759] lng[117.5019140625] lat[39.048946397569445] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"order-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.250","className":"com.candao.dms2.order.service.impl.OrderStatusService","methodName":"assignOrderAsyncHandle","clientType":0,"costTime":0,"logId":"23880091612237985250","ip":"","clientIp":"","level":"INFO","msg":"自动指派订单：指派订单:[1980105-02/02/2021-6008] 给骑手[张明河]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"order-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.248","className":"com.candao.dms2.order.service.impl.OrderCountServiceImpl","methodName":"lambda$null$0","clientType":0,"costTime":0,"logId":"23906731612237985248","ip":"","clientIp":"","level":"INFO","msg":"订单查询状态计数修改成功,storeId=[11143],oldQueryStatus=[2],nowQueryStatus=[3],耗时：[2ms]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.248","className":"com.candao.dms2.horseman.service.impl.HorsemanStatusService","methodName":"syncAssignOrders","clientType":0,"costTime":0,"logId":"26561612237985248","ip":"","clientIp":"","level":"INFO","msg":"指派订单[118191202],骑手id[10681],最近接单时间[Optional[2021-02-02 11:53:05.244]]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.234","className":"com.candao.dms2.horseman.service.impl.HorsemanStatusService","methodName":"syncHorsemanData","clientType":0,"costTime":0,"logId":"320621612237985234","ip":"","clientIp":"","level":"INFO","msg":"同步骑手请求:骑手id[33191] 订单id[118189659] 订单请求参数[OrderStatusChangeSyncHorseman{orderStatus=31, horsemanId=33191, orderId=118189659, markExceptionType=0, forceComplete=0}]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.214","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"320631612237985214","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[17182] lng[118.13817301432292] lat[24.508895399305555] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.193","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"26241612237985193","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[41927] lng[121.5021302625868] lat[31.25814914279514] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"order-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.187","className":"com.candao.dms2.order.service.impl.OrderStatusService","methodName":"saveOrderOtherInfoToCaches","clientType":0,"costTime":0,"logId":"23889931612237985187","ip":"","clientIp":"","level":"INFO","msg":"(请求结果)同步配送关单信息保存到缓存:ID[118188152],关单信息[OrderOtherInfo{closeOrderDistance=266, isAdvanceCloseOrder=1, closeLng=121.35080159505209, closeLat=31.1717724609375, createTagTime=1612237883277}]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.169","className":"com.candao.dms2.horseman.service.impl.HorsemanStatusService","methodName":"lambda$syncHorsemanData$3","clientType":0,"costTime":0,"logId":"4671612237985169","ip":"","clientIp":"","level":"INFO","msg":"开始同步骑手:骑手id[23682] 订单id[118188152] 订单请求参数[OrderStatusChangeSyncHorseman{orderStatus=100, horsemanId=23682, orderId=118188152, markExceptionType=0, forceComplete=0}] 骑手当前订单[HorsemanCurrentOrderImpl { horsemanId = 23682, currentAssignOrders = , currentDeliveryOrders = 118187593,118188152,118188153, currentKeepOrders = , currentCancelOrders =  }] 骑手[HorsemanImpl { id = 23682, tokenId = 56, number = anyu_15000884030, loginName = anyu_15000884030, password = 47e6abfbed45f83d244e241ef543db77, horsemanName = 刘成强, horsemanPhone = 15000884030, vehicleId = 1145, status = 1, workStatus = 1, deliveryStatus = 3, networkStatus = 1, lastOnlineTime = 2020-12-31 08:14:19.0, horsemanType = 1, horsemanSource = 0, roleId = null, lastOrder = 2021-02-02 11:44:03.48, lastWait = 2021-02-02 11:49:09.854, currentAssignOrders = , currentDeliveryOrders = , currentKeepOrders = , currentCancelOrders = , currentBindStoreIds = 8453, currentBindStoreName = 上海麦当劳航北路餐厅, completeOrders = 18, exceptionOrders = 0, forwardOrderNum = 0, sessionId = dc8506ca17e74718adf30f038e4bd023_anyu_15000884030, pushToken = , pushTokenAndroid = 120c83f76091cd25d75, ruleId = 0, remarkBoms = , canDelivery = 0, createBy = anyu_13482148769, createTime = 2019-08-20 13:54:39.0, updateBy = cw, updateTime = 2020-11-23 16:43:09.0, managerRoleId = 2699 }]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"order-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.156","className":"com.candao.dms2.order.service.impl.OrderStatusService","methodName":"deliveryCompleteV2","clientType":0,"costTime":0,"logId":"12271021612237985156","ip":"","clientIp":"","level":"INFO","msg":"配送完成-上传信息 骑手id[23682]订单ID[118188152]完成事件[拥有完成订单距离标识]订单实际地址坐标和订单坐标有误差[false]订单维度[31.174146]订单经度[121.350449]骑手符合规则时的维度[31.1717724609375]骑手符合规则时的经度[121.35080159505209]APP判断的关单距离[266]APP创建标识时间[1612237883277]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"order-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.134","className":"com.candao.dms2.order.service.impl.ExceptionService$2","methodName":"run","clientType":0,"costTime":0,"logId":"23906691612237985134","ip":"","clientIp":"","level":"INFO","msg":"消息读取orderId[118181280] 访问消息中心，更新列表消息为 骑手长 已读","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.133","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"731612237985133","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[46439] lng[121.511867] lat[31.197294] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.127","className":"com.candao.dms2.horseman.service.impl.HorsemanStatusService","methodName":"lambda$orderChangeHorseman$2","clientType":0,"costTime":0,"logId":"4371612237985127","ip":"","clientIp":"","level":"INFO","msg":"订单被改派:原骑手id[22141] 移除该订单[118191146]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null},{"time":0,"sysName":"horseman-service","flag":8,"step":0,"createTime":"2021-02-02 11:53:05.106","className":"com.candao.dms2.horseman.service.impl.HorsemanLocationService","methodName":"updateLocation","clientType":0,"costTime":0,"logId":"3301612237985105","ip":"","clientIp":"","level":"INFO","msg":"骑手更新坐标horsemanId[31313] lng[113.34719807942709] lat[23.00708523220486] phoneInfo[]","isErr":false,"actionName":"","apiName":"","errName":"","eid":null,"subEnvName":null,"gray":0,"thread":null}]
         * pages : 2303742
         * total : 46074830
         * page : 1
         * pageSize : 20
         */

        private int pages;
        private int total;
        private int page;
        private int pageSize;
        private List<RowsBean> rows;

        @NoArgsConstructor
        @Data
        @HeadRowHeight(25)
        @ColumnWidth(25)
        public static class RowsBean {
            /**
             * time : 0
             * sysName : horseman-service
             * flag : 8
             * step : 0
             * createTime : 2021-02-02 11:53:05.511
             * className : com.candao.dms2.horseman.service.impl.HorsemanLocationService
             * methodName : updateLocation
             * clientType : 0
             * costTime : 0
             * logId : 26591612237985511
             * ip :
             * clientIp :
             * level : INFO
             * msg : 骑手更新坐标horsemanId[21983] lng[121.40698269314237] lat[31.323970811631945] phoneInfo[]
             * isErr : false
             * actionName :
             * apiName :
             * errName :
             * eid : null
             * subEnvName : null
             * gray : 0
             * thread : null
             */

            @ExcelProperty("LogId")
            private String logId;
            @ExcelIgnore
            private int time;
            @ExcelIgnore
            private String sysName;
            @ExcelIgnore
            private int flag;
            @ExcelIgnore
            private int step;
            @ExcelProperty("日志级别")
            @ColumnWidth(15)
            private String level;
            @ExcelProperty("创建时间")
            private String createTime;
            @ExcelProperty("代码所在行")
            @ColumnWidth(50)
            private String codePath;
            @ExcelIgnore
            private String className;
            @ExcelIgnore
            private String methodName;
            @ExcelIgnore
            private int clientType;
            @ExcelIgnore
            private int costTime;
            @ExcelIgnore
            private String ip;
            @ExcelIgnore
            private String clientIp;
            @ExcelProperty("信息")
            @ColumnWidth(150)
            private String msg;
            @ExcelIgnore
            private boolean isErr;
            @ExcelIgnore
            private String actionName;
            @ExcelIgnore
            private String apiName;
            @ExcelIgnore
            private String errName;
            @ExcelIgnore
            private Object eid;
            @ExcelIgnore
            private Object subEnvName;
            @ExcelIgnore
            private int gray;
            @ExcelIgnore
            private Object thread;

            /**
             * 触发代码行
             */
            public String getCodePath() {
                return className + "#" + methodName ;
            }

            public String simpleText() {
                return new StringJoiner(", ", RowsBean.class.getSimpleName() + "[", "]")
                        .add("logId='" + logId + "'")
                        .add("createTime='" + createTime + "'")
                        .add("level='" + level + "'")
                        .add("codePath='" + className + "#" + methodName + "'")
                        .add("msg='" + StringUtils.abbreviate(msg, 150) + "'")
                        .toString();
            }
        }
    }
}
