package com.venus.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest request;
	@Autowired
	ServletContext context;

	public String getString(String name) {
		if (request.getParameter(name).isEmpty()) {
			return "";
		}
		return request.getParameter(name);
	}

	public int getInt(String name) {
		if (request.getParameter(name).isEmpty()) {
			return -1;
		}
		return Integer.parseInt(request.getParameter(name));
	}

	public double getDouble(String name) {
		if (request.getParameter(name).isEmpty()) {
			return -1;
		}
		return Double.parseDouble(request.getParameter(name));
	}

	public boolean getBoolean(String name) {
		return request.getParameter(name) != null;
	}

	public Date getDate(String name, String pattern) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		String dateString = request.getParameter(name);
		Date date = dateFormat.parse(dateString);
		return date;
	}

	public File save(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = context.getRealPath("/static/");
		Path path = Paths.get(uploadDir + File.separator + fileName);
		Files.createDirectories(path.getParent());
		Files.copy(file.getInputStream(), path);
		return path.toFile();
	}
}
