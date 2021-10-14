package com.abinge.boot.staging.checkauth;

import lombok.Getter;

/**
 * Excel头部
 *
 * @author chengjian
 * @date 2020-12-11
 */
@Getter
public enum ExportExcelHeadEnum {

//    GOPS_BILL_DETAIL("对账单详细",
//            new String[]{"结算机构","结算单元","交易类型","支付流水","退款流水号","资金对账状态","订单号","退款单号","机构签约公司","收银台机构号","收银台机构名称","收款账号","交易金额（订单）","原始金额（订单）","项目","项目分润系数",
//             "交易金额（项目）","原始金额（项目）","系统结算金额（项目）","补账金额（项目）", "实际结算金额（项目）","补账状态(项目)","补账原因(项目)","数量（项目）","备注（项目）", "交易时间","完成时间","订单创建时间","用户姓名","用户手机号","科室","医生","健康币","优惠券","服务包","超级会员支付","会员套餐","健康医务室支付",},
//            new String[]{"providerOrgName","settlementUnitName","tradeTypeName","gpayNo","grefundNo","accountResult","orderCode","refundCode","signCom","partnerNo","partnerName","channelAccountNo","tradePrice","tradeOriginalPrice","itemName","itemProfitRatio",
//                    "itemConfigPayAmount","itemConfigOriginAmount","itemSystemSettlementAmount","itemSupplementAmount","itemActualSettlementAmount","itemSupplementStatusName","itemSupplementReason","itemNum","itemRemark","tradeTime","finishTime","orderCreateTime","userName","userMobile","dept","doctor","hcPay","coupon","packPay","vipMemberPay","account","healthPay"}),
//
//    CONSOLE_ORDER("商户订单",
//            new String[]{"订单号","产品名称","结算机构","结算单元","项目","订单金额","是否对账","订单状态","完成时间","创建时间","是否有退单","用户姓名","手机","科室","医生","备注"},
//            new String[]{"orderCode","productName","providerOrgName","settlementUnitName","itemName","orderPrice","isSoaName","orderStatusName","finishTime","createTime","isOrderRefund","userName","userMobile","dept","doctor","remark"}),
//
//    CONSOLE_BILL_DETAIL("对账单明细",
//            new String[]{"订单号","退款单号","结算机构","结算单元","产品","交易类型","项目","原始金额","结算金额","完成时间","创建时间","用户姓名","手机","身份证","科室","医生","备注"},
//            new String[]{"orderCode","refundCode","providerOrgName","settlementUnitName","productName","tradeTypeName","itemName","tradeOriginalPrice","settlementAmount","finishTime","createTime","userName","userMobile","userCertNo","dept","doctor","remark"}),
//

    APP_SERVICES_SHEET("frog应用权限",
            new String[]{"应用名","appKey","appSecret","serviceId","是否鉴权通过"},
            new String[]{"appName","appKey","appSecret","serviceId","passStsCheck"});

    private final String title;

    private final String[] headers;

    private final String[] fields;

    ExportExcelHeadEnum(String title, String[] headers, String[] fields) {
        this.title = title;
        this.headers = headers;
        this.fields = fields;
    }

    public String getTitle() {
        return title;
    }

    public String[] getHeaders() {
        return headers;
    }

    public String[] getFields() {
        return fields;
    }
}
