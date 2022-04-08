package com.lmy.shopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @desc
 * @Auther LMY233
 * @Date 2022/4/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersVo {

    private String orderId;
    private String userId;
    private String untitled;
    private String receiverName;
    private String receiverMobile;
    private String receiverAddress;
    private BigDecimal totalAmount;
    private Integer actualAmount;
    private Integer payType;
    private String orderRemark;
    private String status;
    private String deliveryType;
    private String deliveryFlowId;
    private BigDecimal orderFreight;
    private Integer deleteStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    private Date updateTime;
    private Date payTime;
    private Date deliveryTime;
    private Date flishTime;
    private Date cancelTime;
    private Integer closeType;
    private List<OrderItem> orderItemList;
}
