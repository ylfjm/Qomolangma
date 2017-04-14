/**
 * 
 */
package com.boz.common.utils;

import com.tocersoft.base.constant.Constants;
import com.tocersoft.base.entity.BSettlementEmail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author be.dev
 *
 */
public class EmailUtil {

	/**
	 * 发送邮件
	 * 
	 * @param emailList 收件人集合
	 * @param nowDate 发送邮件时间
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	public static void sendMail(List<String> emailList, Date nowDate, String subject, String content) {
		if(CollectionUtils.isEmpty(emailList)) {
			return;
		}
		/**
		 * 1.获得一个Session对象. 2.创建一个代表邮件的对象Message. 3.发送邮件Transport
		 */
		// 1.获得连接对象
		// 配置发送邮件的环境属性
		/*
		 * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
		 * mail.user / mail.from
		 */
		final Properties props = new Properties();
		props.setProperty("mail.transport.protocol", Constants.MAIL_TRANSPORT_PROTOCOL); // smtp协议
		props.put("mail.smtp.host", Constants.MAIL_SMTP_HOST);// 发件人使用发邮件的电子信箱服务器我使用的是163的服务器
		props.put("mail.smtp.auth", "true"); // 这样才能通过验证
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.port", Constants.MAIL_SMTP_PORT);

		// 获取session
		Session session = Session.getInstance(props);
		//session.setDebug(true);

		// 2.创建邮件对象:
		Message message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(Constants.FROM_ACCOUNT, Constants.FROM_NICK_NAME));// 设置发件人
			if(!CollectionUtils.isEmpty(emailList)) {
				String to = emailList.get(0);
				message.addRecipient(RecipientType.TO, new InternetAddress(to));// 设置收件人
				if(emailList.size() > 1) {
					List<String> ccEmailList = emailList.subList(1, emailList.size());
					int size = ccEmailList.size();
					InternetAddress[] ccAddressArray = new InternetAddress[size];
					for(int i = 0; i < size; i++) {
						ccAddressArray[i] = new InternetAddress(ccEmailList.get(i));
					}
					message.addRecipients(RecipientType.CC, ccAddressArray);// 设置抄送，类型为抄送
					//String cc = StringUtils.join(ccEmailList.iterator(), ",");
				}
			}
			
			message.setSentDate(nowDate);
			// 设置标题
			message.setSubject(subject);
			// 可以装载多个主体部件！可以把它当成是一个集合
			MimeMultipart partList = new MimeMultipart();
			// 创建一个部件
			MimeBodyPart part1 = new MimeBodyPart();
			part1.setContent(content, "text/html;charset=utf-8");
			partList.addBodyPart(part1);

			message.setContent(partList);// 把邮件的内容设置为多部件的集合对象
			message.saveChanges();
			// 3.发送邮件:
			Transport transport = session.getTransport(Constants.MAIL_TRANSPORT_PROTOCOL);
			transport.connect(Constants.MAIL_SMTP_HOST, Constants.FROM_ACCOUNT, Constants.FROM_PASSWORD);// 发邮件人帐户密码。
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 发送邮件（带附件）
     * @param emailList
     * @param nowDate
     * @param subject
     * @param content
     * @param fileName
     * @return
     */
    public static BSettlementEmail sendMail(List<String> emailList, Date nowDate, String subject, String content, String path) {
        if(CollectionUtils.isEmpty(emailList)) {
            return null;
        }
	    /**
		 * 1.获得一个Session对象. 2.创建一个代表邮件的对象Message. 3.发送邮件Transport
		 */
		// 1.获得连接对象
		// 配置发送邮件的环境属性
		/*
		 * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
		 * mail.user / mail.from
		 */
		final Properties props = new Properties();
		props.setProperty("mail.transport.protocol", Constants.MAIL_TRANSPORT_PROTOCOL); // smtp协议
		props.put("mail.smtp.host", Constants.MAIL_SMTP_HOST);// 发件人使用发邮件的电子信箱服务器我使用的是163的服务器
		props.put("mail.smtp.auth", "true"); // 这样才能通过验证
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.port", Constants.MAIL_SMTP_PORT);

		// 获取session
		Session session = Session.getInstance(props);
		// session.setDebug(true);

		// 2.创建邮件对象:
		Message message = new MimeMessage(session);

		try {
			BSettlementEmail email = new BSettlementEmail();
			email.setTransportProtocol(Constants.MAIL_TRANSPORT_PROTOCOL);
			message.setFrom(new InternetAddress(Constants.FROM_ACCOUNT, Constants.FROM_NICK_NAME));// 设置发件人
			email.setSender(Constants.FROM_ACCOUNT);
			email.setSendDate(nowDate);
			if (!CollectionUtils.isEmpty(emailList)) {
				String to = emailList.get(0);
				message.addRecipient(RecipientType.TO, new InternetAddress(to));// 设置收件人
				email.setAddressee(to);
				if (emailList.size() > 1) {
					List<String> ccEmailList = emailList.subList(1, emailList.size());
					int size = ccEmailList.size();
					InternetAddress[] ccAddressArray = new InternetAddress[size];
					for (int i = 0; i < size; i++) {
						ccAddressArray[i] = new InternetAddress(ccEmailList.get(i));
					}
					message.addRecipients(RecipientType.CC, ccAddressArray);// 设置抄送，类型为抄送
					String cc = StringUtils.join(ccEmailList.iterator(), ",");
					email.setCc(cc);
				}
			}

			message.setSentDate(nowDate);
			// 设置标题
			message.setSubject(subject);
			email.setSubject(subject);
			// 可以装载多个主体部件！可以把它当成是一个集合
			MimeMultipart partList = new MimeMultipart();
			// 创建一个部件
			MimeBodyPart part1 = new MimeBodyPart();
			part1.setContent(content, "text/html;charset=utf-8");
			email.setMailContent(content);
			partList.addBodyPart(part1);
			MimeBodyPart part2 = new MimeBodyPart();
			part2.attachFile(new File(path));
			email.setAttachFile(path);
			partList.addBodyPart(part2);

			message.setContent(partList);// 把邮件的内容设置为多部件的集合对象
			message.saveChanges();
			// 3.发送邮件:
			Transport transport = session.getTransport(Constants.MAIL_TRANSPORT_PROTOCOL);
			transport.connect(Constants.MAIL_SMTP_HOST, Constants.FROM_ACCOUNT, Constants.FROM_PASSWORD);// 发邮件人帐户密码。
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
            return email;
		} catch (Exception e) {
			e.printStackTrace();
            return null;
		}
	}
}
