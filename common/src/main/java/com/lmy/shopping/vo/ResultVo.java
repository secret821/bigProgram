package com.lmy.shopping.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应对下对象Vo
 *
 * @Author LMY
 * @Date 2021/12/24 15:56
 * @Version V1.0
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ResultVo对象",description = "返回给前端的封装对象")
public class ResultVo {
    //响应的状态码
    @ApiModelProperty("响应的状态码")
    private int code;
    //响应的提示信息
    @ApiModelProperty("响应提示的信息")
    private String msg;
    //响应的数据
    @ApiModelProperty("响应的数据")
    private Object data;

}
