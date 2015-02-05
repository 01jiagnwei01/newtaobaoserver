package com.gxkj.taobaoservice.util.maildemo;

 
/**
 * 发件箱工厂
 *
 * @author MZULE
 *
 */
public class MailSenderFactory {
 
    /**
     * 服务邮箱
     */
    private static SimpleMailSender serviceSms = null;
 
    /**
     * 获取邮箱
     *
     * @param type 邮箱类型
     * @return 符合类型的邮箱
     */
    public static SimpleMailSender getSender(MailSenderType type) {
    if (type == MailSenderType.SERVICE) {
        if (serviceSms == null) {
        	serviceSms = new SimpleMailSender("01jiangwei01@163.com",
            "xxxxxxxxxxx");
        }
        return serviceSms;
    }
    return null;
    }
 
}