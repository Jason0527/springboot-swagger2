package com.jason.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jason.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
/**
 * 只是做swagger插件自动生成文档功能，所以数据全部模拟，不做底层存取
 * @author jason
 *
 */
@Api(value="用户相关接口")//用在类上，统一该类下所有接口的作用
@RestController
@RequestMapping("users")
public class UserController {
	/**
	 * 没有参数
	 * @return
	 */
	@ApiOperation(value ="获取所有用户")//用于方法上，value:接口名称/目的     notes:接口说明   tags:分类
	@RequestMapping(value="",method=RequestMethod.GET)
	public Map<String,Object> getUserList(){
		Map<String,Object> result = new HashMap<String,Object>(3);
		try{
		User user = new User("01","叶凡",19,"扼杀天才，只手遮天");
		User user1 = new User("02","石昊",24,"独断万古");
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user1);
		result.put("state", "0");
		result.put("msg", "获取用户列表成功");
		result.put("data", list);
		return result;
		}catch(Exception e){
			e.printStackTrace();
			result.put("state","-1");
			result.put("msg", "error");
			return result;
		}
	}
	/**
	 * 基本类型参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value ="根据id获取用户")//作用于方法      value:接口名称/目的     notes:接口说明   tags:分类
	//参数描述集合，作用于方法
	@ApiImplicitParams({
		  //单个参数描述  name=参数名   value=参数说明     dataType=参数类型         paramType=传参方式（query：url后面加“？”传参，path:url中用“/”传参【restful】）  required=是否必传
		  @ApiImplicitParam(name="id",value="用户id",dataType="string",paramType="path",required=true)})
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Map<String,Object> getUserList(//@ApiParam(name="id",value="用户id",required=true)//@ApiParam注解  name=属性名  value=属性描述  required=是否必填
										@PathVariable("id") String id){	
		Map<String,Object> result = new HashMap<String,Object>(3);
		try{
		User user = new User("01","叶凡",19,"扼杀天才，只手遮天");
		User user1 = new User("02","石昊",24,"独断万古");
		if("01".equals(id)){
			result.put("data", user);
		}else if("02".equals(id)){
			result.put("data", user1);
		}
		result.put("state", "0");
		result.put("msg", "获取用户列表成功");
		return result;
		}catch(Exception e){
			e.printStackTrace();
			result.put("state","-1");
			result.put("msg", "error");
			return result;
		}
	}
	/**
	 * 实体类参数+基本类型参数
	 * @param user
	 * @param me
	 * @return
	 */
	//实体类传参相对而言比较绕，有着不少限制
	//接收参数为实体类的时候， @ApiImplicitParam注解会很乏力，无法在ui界面展示实体类对应的各个属性。
	//这时候用到@RequestBody注解，swagger会将@RequestBody注解的实体类参数，在其ui见面会展示该实体类对应的json字符串（配合实体类的@ApiModel和@ApiModelProperty可以选择展示哪些）
	//但是@RequestBody注解限制http请求体类型通常用application/json(不可以是form-data x-www-form-urlencode,报400)，所以前端页面若是表单提交，则去掉@RequestBody--很恶心
	//查了很多，没有找到一个两全其美的办法（既支持所有类型的提交，又能在ui页面很好的展示实体类的各个字段），有人说用@ModelAttribute注解参数，这样带来的问题是，没有办法隐藏掉一些不想让调用方看到的字段，比如id
	//再有就是，一旦用@RequestBody修饰了一个实体类参数，那么在添加别的参数，paramType类型就不能是body,因为他会将该类型尝试解析为实体类属性，解析失败报400
	//所以我的结论是，尽量避免使用实体类作为参数接收，限制太多
	@ApiOperation(value ="保存用户")//作用于方法      value:接口名称/目的     notes:接口说明   tags:分类
	@ApiImplicitParam(name="me",value="测试参数",dataType="string",required=true,paramType="query")
	@RequestMapping(value="",method=RequestMethod.POST)
	public Map<String,Object> saveUser(@RequestBody User user,String me){
		Map<String,Object> result = new HashMap<String,Object>(3);
		System.out.println(user.getName());
		System.out.println(me);
		result.put("state", "0");
		result.put("msg","保存成功");
		return result;
	}
}
