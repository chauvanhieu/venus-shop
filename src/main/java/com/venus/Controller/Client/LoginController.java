package com.venus.Controller.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venus.DTO.CodeOTP;
import com.venus.Service.LoginService;
import com.venus.Service.MailerService;
import com.venus.Service.SessionService;
import com.venus.entities.User;
import com.venus.repository.UserRepository;

@Controller
public class LoginController {
	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public String generateRandomString() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(100);

		for (int i = 0; i < 100; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			sb.append(randomChar);
		}

		return sb.toString();
	}

	private List<CodeOTP> listOTP = new ArrayList<>();

	@Autowired
	HttpServletRequest request;
	@Autowired
	SessionService sessionService;
	@Autowired
	LoginService loginService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	MailerService mailService;

	@GetMapping("/store-login")
	public String login() {
		return "login-register";
	}

	@PostMapping("/store-login")
	public String handleLogin(@RequestParam String email, @RequestParam String password) {
		if (!email.equals("") && !password.equals("")) {
			loginService.login();
		}

		return "login-register";
	}

	@GetMapping("/logout")
	public String logout() {
		if (sessionService.get("user") != null) {
			sessionService.remove("user");
		}
		return "redirect:/";
	}

	@GetMapping("/forgot-password")
	public String forgotPassword() {
		return "forgot-password";
	}

	public String getURL() {
		StringBuilder url = new StringBuilder();
		String scheme = request.getScheme();
		int port = request.getServerPort();

		url.append(scheme).append("://").append(request.getServerName());

		if (port != 80 && port != 443) {
			url.append(":").append(port);
		}

		return url.toString();
	}

	@PostMapping("/forgot-password")
	public String handleForgotPassword(@RequestParam String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			sessionService.set("forgotPasswordMessage", "");
			CodeOTP newCode = new CodeOTP();
			newCode.setEmail(email);
			newCode.setCode(generateRandomString());
			listOTP.add(newCode);
			String link = getURL() + "/reset-password?email=" + user.get().getEmail() + "&code=" + newCode.getCode();
			try {
				mailService.send(user.get().getEmail(), "[VenusShop] - Reset Your Password",
						"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
								+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"font-family:Poppins, sans-serif\">\r\n"
								+ " <head>\r\n" + "  <meta charset=\"UTF-8\">\r\n"
								+ "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\r\n"
								+ "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
								+ "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
								+ "  <meta content=\"telephone=no\" name=\"format-detection\">\r\n"
								+ "  <title>New Template</title><!--[if (mso 16)]>\r\n"
								+ "    <style type=\"text/css\">\r\n" + "    a {text-decoration: none;}\r\n"
								+ "    </style>\r\n"
								+ "    <![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>\r\n"
								+ "<xml>\r\n" + "    <o:OfficeDocumentSettings>\r\n"
								+ "    <o:AllowPNG></o:AllowPNG>\r\n" + "    <o:PixelsPerInch>96</o:PixelsPerInch>\r\n"
								+ "    </o:OfficeDocumentSettings>\r\n" + "</xml>\r\n"
								+ "<![endif]--><!--[if !mso]><!-- -->\r\n"
								+ "  <link href=\"https://fonts.googleapis.com/css2?family=Krona+One&display=swap\" rel=\"stylesheet\">\r\n"
								+ "  <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\"><!--<![endif]-->\r\n"
								+ "  <style type=\"text/css\">\r\n" + "#outlook a {\r\n" + "	padding:0;\r\n"
								+ "}\r\n" + ".es-button {\r\n" + "	mso-style-priority:100!important;\r\n"
								+ "	text-decoration:none!important;\r\n" + "}\r\n" + "a[x-apple-data-detectors] {\r\n"
								+ "	color:inherit!important;\r\n" + "	text-decoration:none!important;\r\n"
								+ "	font-size:inherit!important;\r\n" + "	font-family:inherit!important;\r\n"
								+ "	font-weight:inherit!important;\r\n" + "	line-height:inherit!important;\r\n"
								+ "}\r\n" + ".es-desk-hidden {\r\n" + "	display:none;\r\n" + "	float:left;\r\n"
								+ "	overflow:hidden;\r\n" + "	width:0;\r\n" + "	max-height:0;\r\n"
								+ "	line-height:0;\r\n" + "	mso-hide:all;\r\n" + "}\r\n"
								+ "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:30px!important; text-align:center } h2 { font-size:24px!important; text-align:center } h3 { font-size:20px!important; text-align:left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important; text-align:center } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:24px!important; text-align:center } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:left } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:inline-block!important } a.es-button, button.es-button { font-size:18px!important; display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } }\r\n"
								+ "</style>\r\n" + " </head>\r\n"
								+ " <body style=\"width:100%;font-family:Poppins, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\r\n"
								+ "  <div class=\"es-wrapper-color\" style=\"background-color:#2E0249\"><!--[if gte mso 9]>\r\n"
								+ "			<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\r\n"
								+ "				<v:fill type=\"tile\" color=\"#2E0249\"></v:fill>\r\n"
								+ "			</v:background>\r\n" + "		<![endif]-->\r\n"
								+ "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#2E0249\">\r\n"
								+ "     <tr>\r\n" + "      <td valign=\"top\" style=\"padding:0;Margin:0\">\r\n"
								+ "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:#570A57;background-repeat:repeat;background-position:center top\">\r\n"
								+ "         <tr>\r\n"
								+ "          <td align=\"center\" bgcolor=\"#2e0249\" style=\"padding:0;Margin:0;background-color:#2e0249\">\r\n"
								+ "           <table bgcolor=\"#2e0249\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#2e0249;width:600px\">\r\n"
								+ "             <tr>\r\n"
								+ "              <td class=\"esdev-adapt-off\" align=\"left\" style=\"padding:20px;Margin:0\">\r\n"
								+ "               <table cellpadding=\"0\" cellspacing=\"0\" class=\"esdev-mso-table\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:560px\">\r\n"
								+ "                 <tr>\r\n"
								+ "                  <td class=\"esdev-mso-td\" valign=\"top\" style=\"padding:0;Margin:0\">\r\n"
								+ "                   <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\r\n"
								+ "                     <tr>\r\n"
								+ "                      <td class=\"es-m-p0r\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:73px\">\r\n"
								+ "                       <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
								+ "                         <tr>\r\n"
								+ "                          <td align=\"left\" class=\"es-m-txt-l\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#F6C6EA;font-size:14px\"><img src=\"https://qcxpvc.stripocdn.email/content/guids/CABINET_6238642ba45954e6d6ce5fb7661d4679/images/finger_16converted_BrK.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"60\"></a></td>\r\n"
								+ "                         </tr>\r\n" + "                       </table></td>\r\n"
								+ "                     </tr>\r\n" + "                   </table></td>\r\n"
								+ "                  <td style=\"padding:0;Margin:0;width:20px\"></td>\r\n"
								+ "                  <td class=\"esdev-mso-td\" valign=\"top\" style=\"padding:0;Margin:0\">\r\n"
								+ "                   <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\r\n"
								+ "                     <tr>\r\n"
								+ "                      <td align=\"left\" style=\"padding:0;Margin:0;width:467px\">\r\n"
								+ "                       <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
								+ "                         <tr>\r\n"
								+ "                          <td align=\"right\" class=\"es-m-txt-r\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#F6C6EA;font-size:14px\"><img src=\"https://qcxpvc.stripocdn.email/content/guids/CABINET_9b143e749e3aaed3697507da71b3bd7b7e0aa7b60b3fdc77ad722f0ebae80f8e/images/bellremindernotificationalertalarmiconsignsymbolapplicationwebsiteuiwhitebackground.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"45\"></a></td>\r\n"
								+ "                         </tr>\r\n" + "                       </table></td>\r\n"
								+ "                     </tr>\r\n" + "                   </table></td>\r\n"
								+ "                 </tr>\r\n" + "               </table></td>\r\n"
								+ "             </tr>\r\n" + "           </table></td>\r\n" + "         </tr>\r\n"
								+ "       </table>\r\n"
								+ "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\r\n"
								+ "         <tr>\r\n"
								+ "          <td align=\"center\" style=\"padding:0;Margin:0;background-size:initial;background-attachment:initial;background-origin:initial;background-clip:initial;background-color:initial\">\r\n"
								+ "           <table class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\r\n"
								+ "             <tr>\r\n"
								+ "              <td align=\"left\" style=\"Margin:0;padding-left:20px;padding-right:20px;padding-top:40px;padding-bottom:40px;border-radius:20px;background-image:url(https://qcxpvc.stripocdn.email/content/guids/CABINET_9b143e749e3aaed3697507da71b3bd7b7e0aa7b60b3fdc77ad722f0ebae80f8e/images/meshgradient_3.png);background-repeat:no-repeat;background-position:center center\" background=\"https://qcxpvc.stripocdn.email/content/guids/CABINET_9b143e749e3aaed3697507da71b3bd7b7e0aa7b60b3fdc77ad722f0ebae80f8e/images/meshgradient_3.png\">\r\n"
								+ "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
								+ "                 <tr>\r\n"
								+ "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\r\n"
								+ "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
								+ "                     <tr>\r\n"
								+ "                      <td align=\"center\" class=\"es-m-txt-l\" style=\"padding:0;Margin:0\"><h1 style=\"Margin:0;line-height:48px;mso-line-height-rule:exactly;font-family:'Krona One', sans-serif;font-size:40px;font-style:normal;font-weight:bold;color:#2E0249\">Venus Shop</h1></td>\r\n"
								+ "                     </tr>\r\n" + "                   </table></td>\r\n"
								+ "                 </tr>\r\n" + "                 <tr>\r\n"
								+ "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\r\n"
								+ "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
								+ "                     <tr>\r\n"
								+ "                      <td align=\"center\" style=\"padding:0;Margin:0;padding-top:20px\"><!--[if mso]><a href=\"https://viewstripo.email\" target=\"_blank\" hidden>\r\n"
								+ "	<v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" esdevVmlButton href=\"https://viewstripo.email\" \r\n"
								+ "                style=\"height:41px; v-text-anchor:middle; width:290px\" arcsize=\"24%\" stroke=\"f\"  fillcolor=\"#fc78c0\">\r\n"
								+ "		<w:anchorlock></w:anchorlock>\r\n"
								+ "		<center style='color:#ffffff; font-family:\"Krona One\", sans-serif; font-size:15px; font-weight:400; line-height:15px;  mso-text-raise:1px'>Đổi mật khẩu của bạn</center>\r\n"
								+ "	</v:roundrect></a>\r\n"
								+ "<![endif]--><!--[if !mso]><!-- --><span class=\"msohide es-button-border\" style=\"border-style:solid;border-color:#FFFFFF;background:#FC78C0;border-width:0px;display:inline-block;border-radius:10px;width:auto;mso-hide:all\"><a href=\""
								+ link
								+ "\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:18px;display:inline-block;background:#FC78C0;border-radius:10px;font-family:'Krona One', sans-serif;font-weight:normal;font-style:normal;line-height:22px;width:auto;text-align:center;padding:10px 20px 10px 20px;mso-padding-alt:0;mso-border-alt:10px solid #FC78C0\" >Đổi mật khẩu của bạn</a></span><!--<![endif]--></td>\r\n"
								+ "                     </tr>\r\n" + "                   </table></td>\r\n"
								+ "                 </tr>\r\n" + "               </table></td>\r\n"
								+ "             </tr>\r\n" + "             <tr>\r\n"
								+ "              <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">\r\n"
								+ "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
								+ "                 <tr>\r\n"
								+ "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\r\n"
								+ "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
								+ "                     <tr>\r\n"
								+ "                      <td align=\"center\" height=\"0\" style=\"padding:0;Margin:0\"></td>\r\n"
								+ "                     </tr>\r\n" + "                   </table></td>\r\n"
								+ "                 </tr>\r\n" + "               </table></td>\r\n"
								+ "             </tr>\r\n" + "           </table></td>\r\n" + "         </tr>\r\n"
								+ "       </table>\r\n"
								+ "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\r\n"
								+ "         <tr>\r\n"
								+ "          <td align=\"center\" style=\"padding:0;Margin:0\">\r\n"
								+ "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:transparent;border-radius:10px;width:600px\">\r\n"
								+ "             <tr>\r\n"
								+ "              <td class=\"esdev-adapt-off\" align=\"left\" style=\"padding:20px;Margin:0;border-left:2px solid #f6c6ea;border-right:2px solid #f6c6ea;border-bottom:2px solid #f6c6ea;border-radius:0px 0px 20px 20px\">\r\n"
								+ "               <table cellpadding=\"0\" cellspacing=\"0\" class=\"esdev-mso-table\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:560px\">\r\n"
								+ "                 <tr>\r\n"
								+ "                  <td class=\"esdev-mso-td\" valign=\"top\" style=\"padding:0;Margin:0\">\r\n"
								+ "                   <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\r\n"
								+ "                     <tr>\r\n"
								+ "                      <td align=\"left\" style=\"padding:0;Margin:0;width:268px\">\r\n"
								+ "                       <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
								+ "                         <tr>\r\n"
								+ "                          <td align=\"center\" style=\"padding:0;Margin:0;display:none\"></td>\r\n"
								+ "                         </tr>\r\n" + "                       </table></td>\r\n"
								+ "                     </tr>\r\n" + "                   </table></td>\r\n"
								+ "                  <td style=\"padding:0;Margin:0;width:20px\"></td>\r\n"
								+ "                  <td class=\"esdev-mso-td\" valign=\"top\" style=\"padding:0;Margin:0\">\r\n"
								+ "                   <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\r\n"
								+ "                     <tr>\r\n"
								+ "                      <td align=\"left\" style=\"padding:0;Margin:0;width:268px\">\r\n"
								+ "                       <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
								+ "                         <tr>\r\n"
								+ "                          <td align=\"center\" style=\"padding:0;Margin:0;display:none\"></td>\r\n"
								+ "                         </tr>\r\n" + "                       </table></td>\r\n"
								+ "                     </tr>\r\n" + "                   </table></td>\r\n"
								+ "                 </tr>\r\n" + "               </table></td>\r\n"
								+ "             </tr>\r\n" + "           </table></td>\r\n" + "         </tr>\r\n"
								+ "       </table></td>\r\n" + "     </tr>\r\n" + "   </table>\r\n" + "  </div>\r\n"
								+ " </body>\r\n" + "</html>");
			} catch (Exception e) {
				System.out.println("Lỗi gửi mail reset password: " + e.getMessage());
			}
			return "forgot-password-sended-mail";
		}
		sessionService.set("forgotPasswordMessage", "Địa chỉ email không tồn tại");
		return "forgot-password";
	}

	@GetMapping("/reset-password")
	public String resetPassword(@RequestParam String email, @RequestParam String code) {
		if (listOTP.size() > 0) {
			int index = 0;
			for (CodeOTP item : listOTP) {
				if (item.getEmail().equals(email) && item.getCode().equals(code)) {
					sessionService.set("email", email);
					listOTP.remove(index);
					return "reset-password";
				}
				index++;
			}
		}

		return "redirect:/store-login";
	}

	@PostMapping("/reset-password")
	public String handleResetPassword(@RequestParam("new-password") String newPassword,
			@RequestParam("confirm-new-password") String confirmPassword, @RequestParam("email") String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			User u = user.get();
			u.setPassword(confirmPassword);
			userRepository.save(u);
		}
		return "redirect:/";
	}
}
