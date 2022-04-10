package com.lmy.shopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 评价包装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCommentsVo {
    /**
     * ID
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String commId;

    /**
     * 商品id
     */

    private String productId;

    /**
     * 商品名称
     */

    private String productName;

    /**
     * 订单项(商品快照)ID 可为空
     */

    private String orderItemId;

    /**
     * 评论用户id 用户名须脱敏
     */

    private String userId;

    /**
     * 是否匿名（1:是，0:

否）
     */
    private Integer isAnonymous;

    /**
     * 评价类型（1好评，0中评，-1

差评）
     */

    private Integer commType;

    /**
     * 评价等级 1：好评 2：中评 3：差评
     */

    private Integer commLevel;

    /**
     * 评价内容
     */

    private String commContent;

    /**
     * 评价晒图(JSON {img1:url1,img2:url2}  )
     */

    private String commImgs;

    /**
     * 评价时间 可为空
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date sepcName;

    /**
     * 是否回复（0:未回复，1:

已回复）
     */

    private Integer replyStatus;

    /**
     * 回复内容
     */

    private String replyContent;

    /**
     * 回复时间
     */
    private Date replyTime;

    /**
     * 是否显示（1:是，0:否）
     */

    private Integer isShow;

    /**
     * 用户名
     */
    private String username;

    /**
     *  昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String userImg;


    /**
     * 商品图片
     */
    private  String productImg;
}