package jdk8Practise.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ltz
 * @version V1.0
 * @date 2020/11/12 15:55
 * @Description
 * @email goodmanalibaba@foxmail.com
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class WechatSendMessageRequestVo {
    /**
     * 当前人的openID
     */
    String openid;
    /**
     * 要发送的模板类型
     */
//    NoticeType noticeType;
    /**
     * 是否要跳转小程序 true 是要跳转小程序，这个时候会取模板配置的url--没有不传
     */
    Boolean toMiniProgram;
    /**
     * 要跳转小程序的后缀的参数;注意按照参数顺序添加，先进先出--没有不传
     */
    List<String> toMiniProgramurlParamsList;
    /**
     * 微信公众号模板第一句的参数的集合 注意按照模板的顺序，先进先出--没有不传
     */
    List<String> firstParamList;
    /**
     * 微信公众号模板正文里面参数的集合 注意按照模板的顺序，先进先出--没有不传
     */
    List<String> paramList;
    /**
     * 微信公众号模板 备注信息--没有不传
     */
    String remark;
    /**
     * 非跳转小程序的外网页面链接（公众号跳转） 比如H5 页面; url 和 小程序 同时设置的话，优先跳小程序--没有不传
     */
    String toUrl;
    /**
     * 第一句的正文--支持自定义--没有则不传
     * */
    String firstContentRequest;

//    @ApiModelProperty(value = "  系统来源 1 乐才 0 职微你", required = false)
//    private Byte systemSource;


//    String appId;//公众号的APPID
//    String accessToken;//请求的授权token
//    String templateId;//消息模板
//    String toUrl;//要跳转的小程序的页面的url===
//    MiniProgramRequest miniProgramRequest;//要跳转的小程序的配置信息
////        MessageParam messageParam;//消息参数 && 颜色配置 TODO 不要，用下面的参数组装起来
//        MessageTemplate messageTemplate;////微信模板



}
