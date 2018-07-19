package com.jason.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

/**
 * 测试用的实体类
 * @author jason
 *
 */
@ApiModel(value="user对象", description="封装了对象实体类")
public class User {
	@ApiModelProperty(hidden=true)
	private String id;
	@ApiModelProperty(value="客户姓名",name="name")
	private String name;
	@ApiModelProperty(value="客户年龄",name="age")
	private Integer age;
	@ApiModelProperty(value="客户签名",name="sign")
	private	String sign;
	
	public User(){
		
	}
	
	public User(String id,String name,Integer age,String sign){
		this.id = id;
		this.name = name;
		this.age = age;
		this.sign = sign;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
