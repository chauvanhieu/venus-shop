package com.venus.Controller.Client;

import java.util.ArrayList;
import java.util.Date;
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
import com.venus.Service.MailerService;
import com.venus.Service.SessionService;
import com.venus.entities.User;
import com.venus.repository.UserRepository;

@Controller
public class RegisterController {
	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

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
	MailerService mailService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SessionService sessionService;

	@PostMapping("/register")
	public String handleReigister(@RequestParam("fullName") String fullName, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("confirm-password") String confirmPassword,
			@RequestParam("phoneNumber") String phoneNumber, @RequestParam("address") String address) {

		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			return "redirect:/login?message=Your email is already in use";
		}

		CodeOTP newCode = new CodeOTP();
		newCode.setEmail(email);
		newCode.setCode(generateRandomString());
		listOTP.add(newCode);
		String link = getURL() + "/confirm?email=" + newCode.getEmail() + "&code=" + newCode.getCode() + "&password="
				+ password + "&fullName=" + fullName + "&phoneNumber=" + phoneNumber + "&address=" + address;
		try {
			mailService.send(email, "[VenusShop] - Confirm Your Sign In ",
					"<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n"
							+ "	pageEncoding=\"UTF-8\"%>\r\n"
							+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
							+ "<html xmlns=\"http://www.w3.org/1999/xhtml\"\r\n"
							+ "	xmlns:o=\"urn:schemas-microsoft-com:office:office\"\r\n"
							+ "	style=\"font-family:Poppins, sans-serif\">\r\n" + "<head>\r\n"
							+ "<meta charset=\"UTF-8\">\r\n"
							+ "<meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\r\n"
							+ "<meta name=\"x-apple-disable-message-reformatting\">\r\n"
							+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
							+ "<meta content=\"telephone=no\" name=\"format-detection\">\r\n"
							+ "<title>New Template</title>\r\n" + "\r\n" + "<link\r\n"
							+ "	href=\"https://fonts.googleapis.com/css2?family=Krona+One&display=swap\"\r\n"
							+ "	rel=\"stylesheet\">\r\n" + "<link\r\n"
							+ "	href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\"\r\n"
							+ "	rel=\"stylesheet\">\r\n" + "<style type=\"text/css\">\r\n" + "#outlook a {\r\n"
							+ "	padding: 0;\r\n" + "}\r\n" + "\r\n" + ".es-button {\r\n"
							+ "	mso-style-priority: 100 !important;\r\n" + "	text-decoration: none !important;\r\n"
							+ "}\r\n" + "\r\n" + "a[x-apple-data-detectors] {\r\n" + "	color: inherit !important;\r\n"
							+ "	text-decoration: none !important;\r\n" + "	font-size: inherit !important;\r\n"
							+ "	font-family: inherit !important;\r\n" + "	font-weight: inherit !important;\r\n"
							+ "	line-height: inherit !important;\r\n" + "}\r\n" + "\r\n" + ".es-desk-hidden {\r\n"
							+ "	display: none;\r\n" + "	float: left;\r\n" + "	overflow: hidden;\r\n"
							+ "	width: 0;\r\n" + "	max-height: 0;\r\n" + "	line-height: 0;\r\n"
							+ "	mso-hide: all;\r\n" + "}\r\n" + "\r\n"
							+ "@media only screen and (max-width:600px) {\r\n" + "	p, ul li, ol li, a {\r\n"
							+ "		line-height: 150% !important\r\n" + "	}\r\n"
							+ "	h1, h2, h3, h1 a, h2 a, h3 a {\r\n" + "		line-height: 120%\r\n" + "	}\r\n"
							+ "	h1 {\r\n" + "		font-size: 30px !important;\r\n" + "		text-align: center\r\n"
							+ "	}\r\n" + "	h2 {\r\n" + "		font-size: 24px !important;\r\n"
							+ "		text-align: center\r\n" + "	}\r\n" + "	h3 {\r\n"
							+ "		font-size: 20px !important;\r\n" + "		text-align: left\r\n" + "	}\r\n"
							+ "	.es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a {\r\n"
							+ "		font-size: 30px !important;\r\n" + "		text-align: center\r\n" + "	}\r\n"
							+ "	.es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a {\r\n"
							+ "		font-size: 24px !important;\r\n" + "		text-align: center\r\n" + "	}\r\n"
							+ "	.es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a {\r\n"
							+ "		font-size: 20px !important;\r\n" + "		text-align: left\r\n" + "	}\r\n"
							+ "	.es-menu td a {\r\n" + "		font-size: 14px !important\r\n" + "	}\r\n"
							+ "	.es-header-body p, .es-header-body ul li, .es-header-body ol li,\r\n"
							+ "		.es-header-body a {\r\n" + "		font-size: 14px !important\r\n" + "	}\r\n"
							+ "	.es-content-body p, .es-content-body ul li, .es-content-body ol li,\r\n"
							+ "		.es-content-body a {\r\n" + "		font-size: 14px !important\r\n" + "	}\r\n"
							+ "	.es-footer-body p, .es-footer-body ul li, .es-footer-body ol li,\r\n"
							+ "		.es-footer-body a {\r\n" + "		font-size: 14px !important\r\n" + "	}\r\n"
							+ "	.es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a\r\n"
							+ "		{\r\n" + "		font-size: 12px !important\r\n" + "	}\r\n"
							+ "	*[class=\"gmail-fix\"] {\r\n" + "		display: none !important\r\n" + "	}\r\n"
							+ "	.es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 {\r\n"
							+ "		text-align: center !important\r\n" + "	}\r\n"
							+ "	.es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 {\r\n"
							+ "		text-align: right !important\r\n" + "	}\r\n"
							+ "	.es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 {\r\n"
							+ "		text-align: left !important\r\n" + "	}\r\n"
							+ "	.es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img {\r\n"
							+ "		display: inline !important\r\n" + "	}\r\n" + "	.es-button-border {\r\n"
							+ "		display: inline-block !important\r\n" + "	}\r\n"
							+ "	a.es-button, button.es-button {\r\n" + "		font-size: 18px !important;\r\n"
							+ "		display: inline-block !important\r\n" + "	}\r\n"
							+ "	.es-adaptive table, .es-left, .es-right {\r\n" + "		width: 100% !important\r\n"
							+ "	}\r\n" + "	.es-content table, .es-header table, .es-footer table, .es-content,\r\n"
							+ "		.es-footer, .es-header {\r\n" + "		width: 100% !important;\r\n"
							+ "		max-width: 600px !important\r\n" + "	}\r\n" + "	.es-adapt-td {\r\n"
							+ "		display: block !important;\r\n" + "		width: 100% !important\r\n" + "	}\r\n"
							+ "	.adapt-img {\r\n" + "		width: 100% !important;\r\n"
							+ "		height: auto !important\r\n" + "	}\r\n" + "	.es-m-p0 {\r\n"
							+ "		padding: 0 !important\r\n" + "	}\r\n" + "	.es-m-p0r {\r\n"
							+ "		padding-right: 0 !important\r\n" + "	}\r\n" + "	.es-m-p0l {\r\n"
							+ "		padding-left: 0 !important\r\n" + "	}\r\n" + "	.es-m-p0t {\r\n"
							+ "		padding-top: 0 !important\r\n" + "	}\r\n" + "	.es-m-p0b {\r\n"
							+ "		padding-bottom: 0 !important\r\n" + "	}\r\n" + "	.es-m-p20b {\r\n"
							+ "		padding-bottom: 20px !important\r\n" + "	}\r\n"
							+ "	.es-mobile-hidden, .es-hidden {\r\n" + "		display: none !important\r\n"
							+ "	}\r\n" + "	tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden {\r\n"
							+ "		width: auto !important;\r\n" + "		overflow: visible !important;\r\n"
							+ "		float: none !important;\r\n" + "		max-height: inherit !important;\r\n"
							+ "		line-height: inherit !important\r\n" + "	}\r\n" + "	tr.es-desk-hidden {\r\n"
							+ "		display: table-row !important\r\n" + "	}\r\n" + "	table.es-desk-hidden {\r\n"
							+ "		display: table !important\r\n" + "	}\r\n" + "	td.es-desk-menu-hidden {\r\n"
							+ "		display: table-cell !important\r\n" + "	}\r\n" + "	.es-menu td {\r\n"
							+ "		width: 1% !important\r\n" + "	}\r\n"
							+ "	table.es-table-not-adapt, .esd-block-html table {\r\n"
							+ "		width: auto !important\r\n" + "	}\r\n" + "	table.es-social {\r\n"
							+ "		display: inline-block !important\r\n" + "	}\r\n" + "	table.es-social td {\r\n"
							+ "		display: inline-block !important\r\n" + "	}\r\n" + "	.es-desk-hidden {\r\n"
							+ "		display: table-row !important;\r\n" + "		width: auto !important;\r\n"
							+ "		overflow: visible !important;\r\n" + "		max-height: inherit !important\r\n"
							+ "	}\r\n" + "	.es-m-p5 {\r\n" + "		padding: 5px !important\r\n" + "	}\r\n"
							+ "	.es-m-p5t {\r\n" + "		padding-top: 5px !important\r\n" + "	}\r\n"
							+ "	.es-m-p5b {\r\n" + "		padding-bottom: 5px !important\r\n" + "	}\r\n"
							+ "	.es-m-p5r {\r\n" + "		padding-right: 5px !important\r\n" + "	}\r\n"
							+ "	.es-m-p5l {\r\n" + "		padding-left: 5px !important\r\n" + "	}\r\n"
							+ "	.es-m-p10 {\r\n" + "		padding: 10px !important\r\n" + "	}\r\n"
							+ "	.es-m-p10t {\r\n" + "		padding-top: 10px !important\r\n" + "	}\r\n"
							+ "	.es-m-p10b {\r\n" + "		padding-bottom: 10px !important\r\n" + "	}\r\n"
							+ "	.es-m-p10r {\r\n" + "		padding-right: 10px !important\r\n" + "	}\r\n"
							+ "	.es-m-p10l {\r\n" + "		padding-left: 10px !important\r\n" + "	}\r\n"
							+ "	.es-m-p15 {\r\n" + "		padding: 15px !important\r\n" + "	}\r\n"
							+ "	.es-m-p15t {\r\n" + "		padding-top: 15px !important\r\n" + "	}\r\n"
							+ "	.es-m-p15b {\r\n" + "		padding-bottom: 15px !important\r\n" + "	}\r\n"
							+ "	.es-m-p15r {\r\n" + "		padding-right: 15px !important\r\n" + "	}\r\n"
							+ "	.es-m-p15l {\r\n" + "		padding-left: 15px !important\r\n" + "	}\r\n"
							+ "	.es-m-p20 {\r\n" + "		padding: 20px !important\r\n" + "	}\r\n"
							+ "	.es-m-p20t {\r\n" + "		padding-top: 20px !important\r\n" + "	}\r\n"
							+ "	.es-m-p20r {\r\n" + "		padding-right: 20px !important\r\n" + "	}\r\n"
							+ "	.es-m-p20l {\r\n" + "		padding-left: 20px !important\r\n" + "	}\r\n"
							+ "	.es-m-p25 {\r\n" + "		padding: 25px !important\r\n" + "	}\r\n"
							+ "	.es-m-p25t {\r\n" + "		padding-top: 25px !important\r\n" + "	}\r\n"
							+ "	.es-m-p25b {\r\n" + "		padding-bottom: 25px !important\r\n" + "	}\r\n"
							+ "	.es-m-p25r {\r\n" + "		padding-right: 25px !important\r\n" + "	}\r\n"
							+ "	.es-m-p25l {\r\n" + "		padding-left: 25px !important\r\n" + "	}\r\n"
							+ "	.es-m-p30 {\r\n" + "		padding: 30px !important\r\n" + "	}\r\n"
							+ "	.es-m-p30t {\r\n" + "		padding-top: 30px !important\r\n" + "	}\r\n"
							+ "	.es-m-p30b {\r\n" + "		padding-bottom: 30px !important\r\n" + "	}\r\n"
							+ "	.es-m-p30r {\r\n" + "		padding-right: 30px !important\r\n" + "	}\r\n"
							+ "	.es-m-p30l {\r\n" + "		padding-left: 30px !important\r\n" + "	}\r\n"
							+ "	.es-m-p35 {\r\n" + "		padding: 35px !important\r\n" + "	}\r\n"
							+ "	.es-m-p35t {\r\n" + "		padding-top: 35px !important\r\n" + "	}\r\n"
							+ "	.es-m-p35b {\r\n" + "		padding-bottom: 35px !important\r\n" + "	}\r\n"
							+ "	.es-m-p35r {\r\n" + "		padding-right: 35px !important\r\n" + "	}\r\n"
							+ "	.es-m-p35l {\r\n" + "		padding-left: 35px !important\r\n" + "	}\r\n"
							+ "	.es-m-p40 {\r\n" + "		padding: 40px !important\r\n" + "	}\r\n"
							+ "	.es-m-p40t {\r\n" + "		padding-top: 40px !important\r\n" + "	}\r\n"
							+ "	.es-m-p40b {\r\n" + "		padding-bottom: 40px !important\r\n" + "	}\r\n"
							+ "	.es-m-p40r {\r\n" + "		padding-right: 40px !important\r\n" + "	}\r\n"
							+ "	.es-m-p40l {\r\n" + "		padding-left: 40px !important\r\n" + "	}\r\n" + "}\r\n"
							+ "</style>\r\n" + "</head>\r\n" + "<body\r\n"
							+ "	style=\"width: 100%; font-family: Poppins, sans-serif; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; padding: 0; Margin: 0\">\r\n"
							+ "	<div class=\"es-wrapper-color\" style=\"background-color: #2E0249\">\r\n" + "\r\n"
							+ "		<table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\r\n"
							+ "			style=\"height: 100vh !important;mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px; padding: 0; Margin: 0; width: 100%; height: 100%; background-repeat: repeat; background-position: center top; background-color: #2E0249\">\r\n"
							+ "			<tr>\r\n"
							+ "				<td valign=\"top\" style=\"padding: 0; Margin: 0\">\r\n"
							+ "					<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\"\r\n"
							+ "						align=\"center\"\r\n"
							+ "						style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px; table-layout: fixed !important; width: 100%; background-color: #570A57; background-repeat: repeat; background-position: center top\">\r\n"
							+ "						<tr>\r\n"
							+ "							<td align=\"center\" bgcolor=\"#2e0249\"\r\n"
							+ "								style=\"padding: 0; Margin: 0; background-color: #2e0249\">\r\n"
							+ "								<table bgcolor=\"#2e0249\" class=\"es-header-body\" align=\"center\"\r\n"
							+ "									cellpadding=\"0\" cellspacing=\"0\"\r\n"
							+ "									style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px; background-color: #2e0249; width: 600px\">\r\n"
							+ "									<tr>\r\n"
							+ "										<td class=\"esdev-adapt-off\" align=\"left\"\r\n"
							+ "											style=\"padding: 20px; Margin: 0\">\r\n"
							+ "											<table cellpadding=\"0\" cellspacing=\"0\"\r\n"
							+ "												class=\"esdev-mso-table\"\r\n"
							+ "												style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px; width: 560px\">\r\n"
							+ "												<tr>\r\n"
							+ "													<td class=\"esdev-mso-td\" valign=\"top\"\r\n"
							+ "														style=\"padding: 0; Margin: 0\">\r\n"
							+ "														<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\"\r\n"
							+ "															align=\"left\"\r\n"
							+ "															style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px; float: left\">\r\n"
							+ "															<tr>\r\n"
							+ "																<td class=\"es-m-p0r\" valign=\"top\" align=\"center\"\r\n"
							+ "																	style=\"padding: 0; Margin: 0; width: 73px\">\r\n"
							+ "																	<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n"
							+ "																		role=\"presentation\"\r\n"
							+ "																		style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px\">\r\n"
							+ "																		<tr>\r\n"
							+ "																			<td align=\"left\" class=\"es-m-txt-l\"\r\n"
							+ "																				style=\"padding: 0; Margin: 0; font-size: 0px\"><a\r\n"
							+ "																				target=\"_blank\" href=\"https://viewstripo.email\"\r\n"
							+ "																				style=\"-webkit-text-size-adjust: none; -ms-text-size-adjust: none; mso-line-height-rule: exactly; text-decoration: underline; color: #F6C6EA; font-size: 14px\"><img\r\n"
							+ "																					src=\"https://qcxpvc.stripocdn.email/content/guids/CABINET_6238642ba45954e6d6ce5fb7661d4679/images/finger_16converted_BrK.png\"\r\n"
							+ "																					alt\r\n"
							+ "																					style=\"display: block; border: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic\"\r\n"
							+ "																					width=\"60\"></a></td>\r\n"
							+ "																		</tr>\r\n"
							+ "																	</table>\r\n"
							+ "																</td>\r\n"
							+ "															</tr>\r\n"
							+ "														</table>\r\n"
							+ "													</td>\r\n"
							+ "													<td style=\"padding: 0; Margin: 0; width: 20px\"></td>\r\n"
							+ "													<td class=\"esdev-mso-td\" valign=\"top\"\r\n"
							+ "														style=\"padding: 0; Margin: 0\">\r\n"
							+ "														<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\"\r\n"
							+ "															align=\"right\"\r\n"
							+ "															style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px; float: right\">\r\n"
							+ "															<tr>\r\n"
							+ "																<td align=\"left\"\r\n"
							+ "																	style=\"padding: 0; Margin: 0; width: 467px\">\r\n"
							+ "																	<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n"
							+ "																		role=\"presentation\"\r\n"
							+ "																		style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px\">\r\n"
							+ "																		<tr>\r\n"
							+ "																			<td align=\"right\" class=\"es-m-txt-r\"\r\n"
							+ "																				style=\"padding: 0; Margin: 0; padding-top: 5px; padding-bottom: 5px; font-size: 0px\"><a\r\n"
							+ "																				target=\"_blank\" href=\"https://viewstripo.email\"\r\n"
							+ "																				style=\"-webkit-text-size-adjust: none; -ms-text-size-adjust: none; mso-line-height-rule: exactly; text-decoration: underline; color: #F6C6EA; font-size: 14px\"><img\r\n"
							+ "																					src=\"https://qcxpvc.stripocdn.email/content/guids/CABINET_9b143e749e3aaed3697507da71b3bd7b7e0aa7b60b3fdc77ad722f0ebae80f8e/images/bellremindernotificationalertalarmiconsignsymbolapplicationwebsiteuiwhitebackground.png\"\r\n"
							+ "																					alt\r\n"
							+ "																					style=\"display: block; border: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic\"\r\n"
							+ "																					width=\"45\"></a></td>\r\n"
							+ "																		</tr>\r\n"
							+ "																	</table>\r\n"
							+ "																</td>\r\n"
							+ "															</tr>\r\n"
							+ "														</table>\r\n"
							+ "													</td>\r\n"
							+ "												</tr>\r\n"
							+ "											</table>\r\n"
							+ "										</td>\r\n"
							+ "									</tr>\r\n"
							+ "								</table>\r\n" + "							</td>\r\n"
							+ "						</tr>\r\n" + "					</table>\r\n"
							+ "					<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\"\r\n"
							+ "						align=\"center\"\r\n"
							+ "						style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px; table-layout: fixed !important; width: 100%\">\r\n"
							+ "						<tr>\r\n" + "							<td align=\"center\"\r\n"
							+ "								style=\"padding: 0; Margin: 0; background-size: initial; background-attachment: initial; background-origin: initial; background-clip: initial; background-color: initial\">\r\n"
							+ "								<table class=\"es-content-body\" align=\"center\" cellpadding=\"0\"\r\n"
							+ "									cellspacing=\"0\"\r\n"
							+ "									style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px; background-color: transparent; width: 600px\">\r\n"
							+ "									<tr>\r\n"
							+ "										<td align=\"left\"\r\n"
							+ "											style=\"Margin: 0; padding-left: 20px; padding-right: 20px; padding-top: 40px; padding-bottom: 40px; border-radius: 20px; background-image: url(https://qcxpvc.stripocdn.email/content/guids/CABINET_9b143e749e3aaed3697507da71b3bd7b7e0aa7b60b3fdc77ad722f0ebae80f8e/images/meshgradient_3.png); background-repeat: no-repeat; background-position: center center\"\r\n"
							+ "											background=\"https://qcxpvc.stripocdn.email/content/guids/CABINET_9b143e749e3aaed3697507da71b3bd7b7e0aa7b60b3fdc77ad722f0ebae80f8e/images/meshgradient_3.png\">\r\n"
							+ "											<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n"
							+ "												style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px\">\r\n"
							+ "												<tr>\r\n"
							+ "													<td align=\"center\" valign=\"top\"\r\n"
							+ "														style=\"padding: 0; Margin: 0; width: 560px\">\r\n"
							+ "														<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n"
							+ "															role=\"presentation\"\r\n"
							+ "															style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px\">\r\n"
							+ "															<tr>\r\n"
							+ "																<td align=\"center\" class=\"es-m-txt-l\"\r\n"
							+ "																	style=\"padding: 0; Margin: 0\"><h1\r\n"
							+ "																		style=\"Margin: 0; line-height: 48px; mso-line-height-rule: exactly; font-family: 'Krona One', sans-serif; font-size: 40px; font-style: normal; font-weight: bold; color: #2E0249\">You\r\n"
							+ "																		have a new message!</h1></td>\r\n"
							+ "															</tr>\r\n"
							+ "															<tr>\r\n"
							+ "																<td align=\"center\" class=\"es-m-txt-l\"\r\n"
							+ "																	style=\"padding: 0; Margin: 0; padding-bottom: 20px; padding-top: 30px; font-size: 0px\"><a\r\n"
							+ "																	target=\"_blank\" href=\"https://viewstripo.email\"\r\n"
							+ "																	style=\"-webkit-text-size-adjust: none; -ms-text-size-adjust: none; mso-line-height-rule: exactly; text-decoration: underline; color: #666666; font-size: 14px\"><img\r\n"
							+ "																		src=\"https://png.pngtree.com/template/20191219/ourmid/pngtree-happy-shop-logo-designs-fun-store-logo-template-vector-illustration-image_341573.jpg\"\r\n"
							+ "																		alt=\"Venus Shop\"\r\n"
							+ "																		style=\"display: block; border: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border-radius: 20px\"\r\n"
							+ "																		width=\"100\" title=\"Venus Shop\"></a></td>\r\n"
							+ "															</tr>\r\n"
							+ "															<tr>\r\n"
							+ "																<td align=\"center\" class=\"es-m-txt-l\"\r\n"
							+ "																	style=\"padding: 0; Margin: 0\"><h2\r\n"
							+ "																		style=\"Margin: 0; line-height: 29px; mso-line-height-rule: exactly; font-family: 'Krona One', sans-serif; font-size: 24px; font-style: normal; font-weight: bold; color: #2E0249\">from&nbsp;Venus Shop</h2></td>\r\n"
							+ "															</tr>\r\n" + "\r\n" + "\r\n"
							+ "														</table>\r\n"
							+ "													</td>\r\n"
							+ "												</tr>\r\n"
							+ "												<tr>\r\n"
							+ "													<td align=\"center\" valign=\"top\"\r\n"
							+ "														style=\"padding: 0; Margin: 0; border-radius: 20px; overflow: hidden; width: 560px\">\r\n"
							+ "														<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n"
							+ "															bgcolor=\"#ffffff\"\r\n"
							+ "															style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: separate; border-spacing: 0px; border-left: 1px solid #efefef; border-right: 1px solid #dddcdc; border-top: 1px solid #efefef; border-bottom: 1px solid #dddcdc; background-color: #ffffff; border-radius: 20px\"\r\n"
							+ "															role=\"presentation\">\r\n"
							+ "															<tr>\r\n"
							+ "																<td align=\"left\"\r\n"
							+ "																	style=\"padding: 0; Margin: 0; padding-top: 20px; padding-left: 20px; padding-right: 20px\"><p\r\n"
							+ "																		style=\"Margin: 0; -webkit-text-size-adjust: none; -ms-text-size-adjust: none; mso-line-height-rule: exactly; font-family: Poppins, sans-serif; line-height: 21px; color: #666666; font-size: 14px\">\r\n"
							+ "																		<strong>Venus Shop</strong>\r\n"
							+ "																	</p></td>\r\n"
							+ "															</tr>\r\n"
							+ "															<tr>\r\n"
							+ "																<td align=\"left\"\r\n"
							+ "																	style=\"padding: 0; Margin: 0; padding-top: 10px; padding-left: 20px; padding-right: 20px\"><p\r\n"
							+ "																		style=\"Margin: 0; -webkit-text-size-adjust: none; -ms-text-size-adjust: none; mso-line-height-rule: exactly; font-family: 'courier new', courier, 'lucida sans typewriter', 'lucida typewriter', monospace; line-height: 21px; color: #666666; font-size: 14px\">\r\n"
							+ "																		Hi " + fullName
							+ ", Thankyou for sign up to our website.\r\n"
							+ "																		Click button to confirm for your account.</p></td>\r\n"
							+ "															</tr>\r\n"
							+ "															\r\n"
							+ "														</table>\r\n"
							+ "													</td>\r\n"
							+ "												</tr>\r\n"
							+ "												<tr>\r\n"
							+ "													<td align=\"center\" valign=\"top\"\r\n"
							+ "														style=\"padding: 0; Margin: 0; width: 560px\">\r\n"
							+ "														<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n"
							+ "															role=\"presentation\"\r\n"
							+ "															style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px\">\r\n"
							+ "															<tr>\r\n"
							+ "																<td align=\"center\"\r\n"
							+ "																	style=\"padding: 0; Margin: 0; padding-top: 20px\">\r\n"
							+ "																	<!--[if mso]><a href=\"https://viewstripo.email\" target=\"_blank\" hidden>\r\n"
							+ "	<v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" esdevVmlButton href=\"https://viewstripo.email\" \r\n"
							+ "                style=\"height:41px; v-text-anchor:middle; width:222px\" arcsize=\"24%\" stroke=\"f\"  fillcolor=\"#fc78c0\">\r\n"
							+ "		<w:anchorlock></w:anchorlock>\r\n"
							+ "		<center style='color:#ffffff; font-family:\"Krona One\", sans-serif; font-size:15px; font-weight:400; line-height:15px;  mso-text-raise:1px'>Open New Message</center>\r\n"
							+ "	</v:roundrect></a>\r\n" + "<![endif]--> <!--[if !mso]><!-- --> <span\r\n"
							+ "																	class=\"es-button-border msohide\"\r\n"
							+ "																	style=\"border-style: solid; border-color: #FFFFFF; background: #FC78C0; border-width: 0px; display: inline-block; border-radius: 10px; width: auto; mso-hide: all\"><a\r\n"
							+ "																		href=\"" + link
							+ "\" class=\"es-button\"\r\n"
							+ "																		target=\"_blank\"\r\n"
							+ "																		style=\"mso-style-priority: 100 !important; text-decoration: none; -webkit-text-size-adjust: none; -ms-text-size-adjust: none; mso-line-height-rule: exactly; color: #FFFFFF; font-size: 18px; display: inline-block; background: #FC78C0; border-radius: 10px; font-family: 'Krona One', sans-serif; font-weight: normal; font-style: normal; line-height: 22px; width: auto; text-align: center; padding: 10px 20px 10px 20px; mso-padding-alt: 0; mso-border-alt: 10px solid #FC78C0\">Confirm account</a></span>\r\n"
							+ "																</td>\r\n"
							+ "															</tr>\r\n"
							+ "														</table>\r\n"
							+ "													</td>\r\n"
							+ "												</tr>\r\n"
							+ "											</table>\r\n"
							+ "										</td>\r\n"
							+ "									</tr>\r\n"
							+ "									<tr>\r\n"
							+ "										<td align=\"left\"\r\n"
							+ "											style=\"padding: 0; Margin: 0; padding-top: 20px; padding-left: 20px; padding-right: 20px\">\r\n"
							+ "											<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n"
							+ "												style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px\">\r\n"
							+ "												<tr>\r\n"
							+ "													<td align=\"center\" valign=\"top\"\r\n"
							+ "														style=\"padding: 0; Margin: 0; width: 560px\">\r\n"
							+ "														<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\r\n"
							+ "															role=\"presentation\"\r\n"
							+ "															style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse; border-spacing: 0px\">\r\n"
							+ "															<tr>\r\n"
							+ "																<td align=\"center\" height=\"0\"\r\n"
							+ "																	style=\"padding: 0; Margin: 0\"></td>\r\n"
							+ "															</tr>\r\n"
							+ "														</table>\r\n"
							+ "													</td>\r\n"
							+ "												</tr>\r\n"
							+ "											</table>\r\n"
							+ "										</td>\r\n"
							+ "									</tr>\r\n"
							+ "								</table>\r\n" + "							</td>\r\n"
							+ "						</tr>\r\n" + "					</table>\r\n"
							+ "					\r\n" + "					\r\n" + "				</td>\r\n"
							+ "			</tr>\r\n" + "		</table>\r\n" + "	</div>\r\n" + "</body>\r\n"
							+ "</html>");
		} catch (Exception e) {
			System.out.println("Lỗi gửi mail: " + e.getMessage());
		}
		return "after-sign-in";
	}

	@GetMapping("/confirm")
	public String confirm(@RequestParam("email") String email, @RequestParam("code") String code,
			@RequestParam("fullName") String fullName, @RequestParam("password") String password,
			@RequestParam("phoneNumber") String phoneNumber, @RequestParam("address") String addresss) {

		if (listOTP != null) {
			int index = 0;
			for (CodeOTP item : listOTP) {
				if (item.getEmail().equals(email) && item.getCode().equals(code)) {
					User user = new User();
					user.setCreatedAt(new Date());
					user.setEmail(email);
					user.setFullName(fullName);
					user.setPassword(password);
					user.setRule(0);
					user.setStatus(1);
					user.setAddress(addresss);
					user.setPhoneNumber(phoneNumber);
					User saveUser = userRepository.save(user);
					sessionService.set("user", saveUser);
					listOTP.remove(index);
					return "redirect:/account";
				}
				index++;
			}
		}
		return "redirect:/";
	}
}
