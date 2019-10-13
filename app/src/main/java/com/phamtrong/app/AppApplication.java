package com.phamtrong.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
//@RestController
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}


	//	@Bean
	//	 public FilterRegistrationBean corsFilter() {
	//
	//	  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	//
	//	  CorsConfiguration config = new CorsConfiguration();
	//
	//	  config.setAllowCredentials(true);
	//
	//	  config.addAllowedOrigin("*");
	//
	//	  config.addAllowedHeader("*");
	//
	//	  config.addAllowedMethod("*");
	//
	//	  source.registerCorsConfiguration("/**", config);
	//
	//	  FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	//
	//	  bean.setOrder(0);
	//
	//	  return bean;
	//
	//	 }



//	@Value("${gmail.username")
//	private String username;
//
//	@Value("${gmail.password")
//	private String password;
//

//	@RequestMapping(value="/send", method = RequestMethod.POST)
//	public String sendEmail(@RequestBody EmailMessage emailMessage) throws AddressException, MessagingException, IOException {
//		sendmail(emailMessage);
//		return "Email send successfully";
//	}

//	private void sendmail(EmailMessage emailMessage) throws AddressException, MessagingException, IOException {
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "587");
//		
//		Session session = Session.getInstance(props, 
//				new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username,password);
//			}
//		});
//		
//		Message msg = new MimeMessage(session);
//		msg.setFrom(new InternetAddress(username, false));
//		
//		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailMessage.getTo_address()));
//		msg.setSubject(emailMessage.getSubject());
//		msg.setContent(emailMessage.getBody(), "text/html");
//		msg.setSentDate(new Date());
//		
//		
//		MimeBodyPart messageBodyPart = new MimeBodyPart();
//		messageBodyPart.setContent(emailMessage.getBody(), "text/html");
//		
//		Multipart mutipart = new MimeMultipart();
//		mutipart.addBodyPart(messageBodyPart);
//		MimeBodyPart attachPart = new MimeBodyPart();
//		
//		attachPart.attachFile("/Users/phamtrong/Desktop/image.png");
//		mutipart.addBodyPart(attachPart);
//		msg.setContent(mutipart);
//		Transport.send(msg);
//	}
	
}



