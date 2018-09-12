package com.jt.manage.controller;


import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileService;

@Controller
public class FileController {
	@Autowired
	private FileService fileService;
	/**
	 * 如果文件上传完成后，再次重定向到文件上传页面
	 * 注意事项：
	 * 1.文件的名称必须和页面保持一致
	 * 2.重定向下发，
	 * 	2.1使用response对象
	 * 	2.2使用redirect重定向
	 *
	 * 文件上传步骤：
	 * 1.先获取文件名称
	 * 2.定义文件上传的路径
	 * 3.调用mvc工具类输出文件
	 * @throws IOException 
	 * @throws IllegalStateException 
	*/
	@RequestMapping("/file")
	public String file(MultipartFile fileName) throws IllegalStateException, IOException{
		//1.获取文件名称 abc.jpg
		String name = fileName.getOriginalFilename();
		
		//2.定义上传路径
		String path="E:/jt-upload";
		
		//3.判断文件是否存在，如果不存在则新建文件夹
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		//E:/jt-upload/abc.jpg
		fileName.transferTo(new File(path+"/"+name));
		
		return "redirect:/file.jsp";//重定向
//		return "forward:/file.jsp"; 转发
	}
	/*
	 * 1.判断文件是否为图片	.jpg/png/gif
	 * 2.判断是否为恶意程序
	 * 3.分文件存储
	 * 4.杜绝重名现象，保证文件不重名
	 */
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult uploadFile(MultipartFile uploadFile){
		
		return fileService.uploadFile(uploadFile);
	}
	
	
}
