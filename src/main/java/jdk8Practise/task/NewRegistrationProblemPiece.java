package jdk8Practise.task;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class NewRegistrationProblemPiece implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    /** (name = "id", value = "id") */
    private Long id;

    /**
     * 问题件编码
     */
    /** (name = "code", value = "问题件编码") */
    private String code;
    /**
     * 运单编号
     */
    /** (name = "waybillNo", value = "运单编号") */
    private String waybillNo;
    /**
     * 接收网点id
     */
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Integer receiveNetworkId;
    /**
     * 接收网点名称
     */
    /** (name = "receiveNetworkName", value = "接收网点名称") */
    private String receiveNetworkName;
    /**
     * 接收网点编码
     */
    /** (name = "receiveNetworkCode", value = "接收网点编码") */
    private String receiveNetworkCode;
    /**
     * 登记网点id
     */
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Integer registrationNetworkId;
    /**
     * 登记网点名称
     */
    /** (name = "registrationNetworkName", value = "登记网点名称") */
    private String registrationNetworkName;
    /**
     * 登记网点编码
     */
    /** (name = "registrationNetworkCode", value = "登记网点编码") */
    private String registrationNetworkCode;
    /**
     * 问题类型id（异常件的操作类型）
     */
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Integer probleTypeId;
    /**
     * 问题类型名称（异常件类型名称）
     */

    /** (name = "probleTypeName", value = "问题类型名称（异常件操作类型）") */
    private String probleTypeName;
    /**
     * 问题类型科目id（关联异常件类型id）
     */
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long probleTypeSubjectId;
    /**
     * 问题类型科目名称（关联异常件类型名称）
     */
    /** (name = "probleTypeSubjectName", value = "问题类型科目名称（异常件类型名称）") */
    private String probleTypeSubjectName;

    /** (name = "probleTypeSubjectCode", value = "问题类型科目名称（异常件类型编码）") */
    private String probleTypeSubjectCode;

    /**
     * 问题描述
     */
    /** (name = "probleDescription", value = "问题描述") */
    private String probleDescription;

    /**
     * 问题描述图片路径
     */
    /** (name = "paths", value = "问题描述图片路径") */
    private String paths;
    /**
     * 问题件状态 1：待处理 2：处理中 3：已处理
     */
    /** (name = "problePieceStatus", value = "问题件状态 1：登记方已登记 2：登记方已回复 3：接收方已回复 4:已转投诉件 5:已完结") */
    private Integer problePieceStatus;
    /**
     * 问题件来源id
     */
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Integer problePieceSource;

    /** (name = "registrantPhone", value = "登记者手机号") */
    private String registrantPhone;

    /** (name = "registrantTelephone", value = "登记者座机号") */
    private String registrantTelephone;

    /**
     * 清单编号
     */
    private String listId;

    /**
     * 包号
     */
    private String packageNumber;

    /**
     * 接收网点的上级网点id
     */
    @JsonSerialize(using= com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long receiveNetworkParentId;

    /**
     * 接收网点的上级网点名称
     */
    /** (name = "receiveNetworkParentName", value = "接收网点的上级网点名称") */
    private String receiveNetworkParentName;

    /**
     * 接收网点的上级网点编码
     */
    /** (name = "receiveNetworkParentName", value = "接收网点的上级网点编码") */
    private String receiveNetworkParentCode;

    /**
     * 登记网点的上级网点id
     */
    @JsonSerialize(using= com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Integer registrationNetworkParentId;

    /**
     * 登记网点的上级网点名称
     */
    /** (name = "registrationNetworkParentName", value = "登记网点的上级网点名称") */
    private String registrationNetworkParentName;

    /**
     * 登记网点的上级网点编码
     */
    /** (name = "registrationNetworkParentCode", value = "登记网点的上级网点编码") */
    private String registrationNetworkParentCode;

    /**
     * 扫描PDA编号
     */
    private String scanPda;

    /**
     * 扫描类型编码
     */
    private String scanTypeCode;

    /**
     * 扫描类型名称
     */
    private String scanTypeName;

    /**
     * 扫描时间
     */
    /** (name = "scanTime", value = "扫描时间") */
    private LocalDateTime scanTime;

     // POD记录对外新加字端
    //  登录人员的电话号码，网点联系方式，网点类型，网点类型ID，网点所属城市，网点所属省份
    /***
     *   登录人员的电话号码
     */
    private String scanByContact;

    private Integer sendNoArriveTime;

    /***
     *   网点联系方式
     */
    private String scanNetworkContact;
    /***
     *   网点类型
     */
    private String scanNetworkTypeName;

    /***
     *   网点类型ID
     */
    private Integer scanNetworkTypeId;

    /***
     *   网点所属城市
     */
    private String scanNetworkCity;
    /***
     *   网点所属省份
     */
    private String scanNetworkProvince;

    /**
     * 所属国家
     */
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    /** (name = "countryId", value = "所属国家") */
    private Integer countryId;

    /**
     * 是否启用:1启用,2不启用
     */
    /** (name = "isEnable", value = "是否启用:1启用,2不启用") */
    private Integer isEnable;

    /**
     * 是否删除:1未删除,2已删除
     */
    /** (name = "isDelete", value = "是否删除:1未删除,2已删除") */
    private Integer isDelete;

    /**
     * 创建人ID
     */
    private Integer createBy;

    /**
     * 创建者编码
     */
    private String createByCode;

    /**
     * 创建人名称
     */
    private String createByName;

    /**
     * 最后更新人ID
     */
    private Integer updateBy;

    /**
     * 更新者编码
     */
    private String updateByCode;

    /**
     * 最后修改人名称
     */
    private String updateByName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建日期
     */
    private LocalDate createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 客户编号code
     */
    private String customerCode;
    /**
     * 寄件客户名称
     */
    private String customerName;
    /**
     * 寄件时间
     */
    private LocalDateTime senderTime;
    /**
     * 寄件网点ID
     */
    private Integer senderNetworkId;
    /**
     * 寄件网点名称
     */
    private String senderNetworkName;
    /**
     * 二级问题类型code
     */
    private String secondLevelTypeCode;
    /**
     * 二级问题类型名称
     */
    private String secondLevelTypeName;

    /**
     * 第一次回复时间
     */
    private LocalDateTime firstReplyTime;
    /**
     * 接收方第一次回复时间
     */
    private LocalDateTime receiverReplyTime;
    /**
     * 是否必要回复 1 是 2 否
     */
    private Integer needReply;
    /**
     * 视频json
     */
    private String videos;
    /**
     *最后回复人用户id
     */
    private Integer lastReplyUserId;
    /**
     * 最后回复人用户姓名
     */
    private String lastReplyUserName;
    /**
     * 最后回复时间
     */
    private LocalDateTime lastReplyTime;
    /**
     * 完结处理时长（单位:分钟）
     */
    private Integer finishDuration;
    /**
     * 回复时间长（单位：M）
     */
    private Integer replyDuration;

    /**
     * 转作废件次数  (1 成功转为作废件    2 未转作废件)
     */
    private Integer isBlankOut;

    /**
     * 作废件编号
     */
    private String blankOutNo;

    /**
     * 作废件状态
     */
    private Integer blankOutStatus;

    /** (name = "processStatusList", value = "处理状态") */
    private Integer processStatus;

    /** (name = "processResult", value = "处理结果") */
    private String processResult;


    /**
     * 知悉网点， 可以多个逗号隔开
     */
    private String knowNetwork;

    /**
     * 是否转留仓件  1是  2否
     */
    private Integer keepWarehouse;

    /**
     * 运单类型 {@link com.yl.css.api.enums.WaybillTypeEnum}
     */
    private Integer waybillType;

    /**
     * 登记网点管理大区编号
     */
    private String regNetworkMrCode;

    /**
     * 登记网点管理大区名称
     */
    private String regNetworkMrName;

    /**
     * 接收网点管理大区编号
     */
    private String receiveNetworkMrCode;

    /**
     * 接收网点管理大区名称
     */
    private String receiveNetworkMrName;

    /**
     * 登记网点代理区编号
     */
    private String regNetworkProxyCode;

    /**
     * 登记网点代理区名称
     */
    private String regNetworkProxyName;

    /**
     * 接收网点代理区编号
     */
    private String receiveNetworkProxyCode;

    /**
     * 接收网点代理区名称
     */
    private String receiveNetworkProxyName;
    /**
     * 订单来源名称
     */
    /** (value = "订单来源名称") */
    private String orderSourceName;
    /**
     * 订单来源编码
     */
    /** (value = "订单来源编码") */
    private String orderSourceCode;


    /**
     * 1 进港  2 出港 默认全部
     */
    private Integer inOutBound;
}
