/*
 * @Author: keiki
 * @Date: 2020-12-21 10:39:58
 * @LastEditTime: 2020-12-29 13:45:33
 * @LastEditors: keiki
 * @Description: 
 */
package com.virtualspacex.util.io.net.smtp;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class SMTP {
    public static void main(String[] args) {
        SendMailThread d = new SendMailThread("xxxxxxx@qq.com","测试邮件标题","测试邮件内容....");
        d.start();
        System.out.println("程序继续...");
    }
}

class SendMailThread extends Thread{
    
    private String mailAdr;
    private String content;
    private String subject ;
    
    public SendMailThread(String mailAdr, String subject, String content) {
        super();
        this.mailAdr = mailAdr;
        this.content = content;
        this.subject = subject;
    }

    @Override
    public void run() {
        super.run();
        sendMail(mailAdr,subject,content);
    }
    
    /*
     * mailAdr 收件人地址
     * subject 邮件标题
     * content 邮件文本内容
     */
    public void sendMail(String mailAdr,String subject,String content){
        //配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        //smtp登陆的账号、密码 ；需开启smtp登陆
        props.put("mail.user", "1101446261@qq.com");
        // 访问SMTP服务时需要提供的密码,不是邮箱登陆密码，一般都有独立smtp的登陆密码
        props.put("mail.password", "XXXXX");
        
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        try{
            InternetAddress form = new InternetAddress(
                    props.getProperty("mail.user"));
            message.setFrom(form);

            // 设置收件人
            InternetAddress to = new InternetAddress(mailAdr);
            message.setRecipient(RecipientType.TO, to);
            
            // 设置抄送
            //InternetAddress cc = new InternetAddress("luo_aaaaa@yeah.net");
            //message.setRecipient(RecipientType.CC, cc);

            // 设置密送，其他的收件人不能看到密送的邮件地址
            //InternetAddress bcc = new InternetAddress("aaaaa@163.com");
            //message.setRecipient(RecipientType.CC, bcc);
            
            // 设置邮件标题
            message.setSubject(subject);
            // 设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        }catch(MessagingException e){
            e.printStackTrace();
        }
    }
}
