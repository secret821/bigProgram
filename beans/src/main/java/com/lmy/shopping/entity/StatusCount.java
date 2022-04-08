package com.lmy.shopping.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc
 * @Auther LMY233
 * @Date 2022/4/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusCount {
    //响应的状态码
    @ApiModelProperty("对应的状态")
    private int status;
    //响应的提示信息
    @ApiModelProperty("对应状态的总数")
    private int  count;

}
