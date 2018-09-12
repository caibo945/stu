package com.jt.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

@Table(name="tb_item")//将对象与表绑定
public class Item extends BasePojo{
	@Id //主键   2个@id 联合主键
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String title;	//商品标题
	//字段sell_point 
	//@Column(name="sell_point")
	private String sellPoint;	//卖点信息
	private Long price;		//价格 计算速度快，精确
	//Java 提供的算数工具类 进行加减运算
	private Integer num;	//商品数量
	private String barcode;		//二维码
	private String image;	//图片内容  1.jpg  保存的是地址 ，数据库严禁将文件存入数据库中
	private Long cid;	//商品分类
	private Integer status;		//商品分类 1.正常，2.下架
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
