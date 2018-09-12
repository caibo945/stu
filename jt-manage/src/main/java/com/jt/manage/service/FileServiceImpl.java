package com.jt.manage.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;

@Service
public class FileServiceImpl implements FileService{
	
	private String localPath="E:/jt-upload/";//本地磁盘路径
	private String urlPath="http://image.jt.com/";//和上面的路径一样，但是面向客户端用户，访问网络地址
	/*
	 * 1.判断文件是否为图片	.jpg/png/gif
	 * 2.判断是否为恶意程序
	 * 3.分文件存储 
	 * 4.杜绝重名现象，保证文件不重名
	 */
	@Override
	public PicUploadResult uploadFile(MultipartFile uploadFile) {
		PicUploadResult result=new PicUploadResult();
		//1.获取图片名称  abc.jpg
		String fileName = uploadFile.getOriginalFilename();
		//2.使用正则表达式判断
		fileName = fileName.toLowerCase();//将字符全部小写
		if(!fileName.matches("^.*(jpg|png|gif)$")){
			result.setError(1);//表示不是图片
			return result;	
		}
		//3.判断图片是否为恶意程序 .exe
		try {
			BufferedImage bufferedImage= ImageIO.read(uploadFile.getInputStream());
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			if(width==0||height==0){
				result.setError(1);
				return result;
			}
			//4.为了实现文件分文件存储
			String dateDir= new SimpleDateFormat("yyyy/MM/dd")
					.format(new Date()).toString();
			String fileDir=localPath+dateDir;
			//创建文件夹 E/jt-upload/2018/11/11
			File dirFile= new File(fileDir);
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			/*5.保证文件不重名，uuid
			 * 5.1截取文件的后缀
			 * 5.2使用UUID当文件名+随机数3位
			 * */
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			String UUIDName=UUID.randomUUID().toString().replace("-", "");
			int randomNum=new Random().nextInt(1000);
			String realFileName = UUIDName+randomNum + fileType;
			//实现文件上传
			/**
			 *  E/jt-upload/2018/11/11
			 */
			String realLocalPath = fileDir+"/"+realFileName;
			uploadFile.transferTo(new File(realLocalPath));
			
			result.setHeight(height+"");
			result.setWidth(width+"");
			/**
			 * 实现图片的回显
			 */
			//http://image.jt.com/+
			String realUrlPath = urlPath+dateDir+"/"+realFileName;
			result.setUrl(realUrlPath);
		} catch (Exception e) {
			e.printStackTrace();
			//不是正经图片信息，
			result.setError(1);
			return result;
		}
		return result;
	}

}
