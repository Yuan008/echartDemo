package com.xlyuan.demo.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.orsoncharts.util.json.JSONArray;
import com.orsoncharts.util.json.JSONObject;
import com.xlyuan.demo.Product;


public class TestServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Random rand = new java.util.Random();
		List<Product> list= new ArrayList<Product>(); 
		//总行
		list.add(new Product("综合授信",rand.nextInt(50)));
		list.add(new Product("单笔授信",rand.nextInt(50)));
		list.add(new Product("额度项下授信",rand.nextInt(50)));
		list.add(new Product("线上贷款",rand.nextInt(50)));
		list.add(new Product("金融市场",rand.nextInt(50)));
		list.add(new Product("国际业务",rand.nextInt(50)));
		list.add(new Product("银行承兑汇票",rand.nextInt(50)));
		list.add(new Product("核心交易",rand.nextInt(50)));
		list.add(new Product("小微IPC",rand.nextInt(50)));
		
		Random rand2 = new java.util.Random();
		//村行
		List<Product> list2= new ArrayList<Product>(); 
		list2.add(new Product("综合授信",rand2.nextInt(60)));
		list2.add(new Product("单笔授信",rand2.nextInt(60)));
		list2.add(new Product("额度项下授信",rand2.nextInt(60)));
		list2.add(new Product("线上贷款",rand2.nextInt(60)));
		list2.add(new Product("金融市场",rand2.nextInt(60)));
		list2.add(new Product("国际业务",rand2.nextInt(60)));
		list2.add(new Product("银行承兑汇票",rand2.nextInt(60)));
		list2.add(new Product("核心交易",rand2.nextInt(60)));
		list2.add(new Product("小微IPC",rand2.nextInt(60)));
		
//		//心电图模式
//		List<Product> list4= new ArrayList<Product>(); 
//		list4.add(new Product("综合授信",));
//		list4.add(new Product("单笔授信",rand2.nextInt(60)));
//		list4.add(new Product("额度项下授信",rand2.nextInt(60)));
//		list4.add(new Product("线上贷款",rand2.nextInt(60)));
//		list4.add(new Product("金融市场",rand2.nextInt(60)));
//		list4.add(new Product("国际业务",rand2.nextInt(60)));
//		list4.add(new Product("银行承兑汇票",rand2.nextInt(60)));
//		list4.add(new Product("核心交易",rand2.nextInt(60)));
//		list4.add(new Product("小微IPC",rand2.nextInt(60)));
		
		List list3 = new ArrayList();
		list3.add(list);
		list3.add(list2);
		
		//分类
		List<String> listFl = new ArrayList<String>();
		listFl.add("综合授信");
		listFl.add("单笔授信");
		listFl.add("额度项下授信");
		listFl.add("线上贷款");
		listFl.add("金融市场");
		listFl.add("国际业务");
		listFl.add("银行承兑汇票");
		listFl.add("核心交易");
		listFl.add("小微IPC");
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(1,"总行");
		map.put(2, "村行");
		
		String json = getProductJson(list3,listFl,map);
		System.out.println("JSON数据："+json);
		//ObjectMapper mapper1 = new ObjectMapper();
		//String json = mapper1.writeValueAsString(list);		
		
		//System.out.println("JSON数据："+json);
		//将json数据返回给客户端
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(json);
		//JSONArray
		//ObjectMapper mapper = new ObjectMapper();
        
        //1.类别json封装
        
		
	}
	
	public  String getProductJson(List list3,List listFl,Map map){
		if(list3 == null)return"";
		JSONArray array1 = new JSONArray();//总分类
		JSONArray array2 = new JSONArray();//类别
		JSONArray array3 = null ;//笔数
		JSONArray array4 = new JSONArray() ;//笔数汇总
		JSONObject jsonObject = new JSONObject();
		Product proInfo = null;
		List<Product> proList = null;
		//获取类别数组
		for(int i=0;i<listFl.size();i++){
			array2.add(listFl.get(i));
		}
		for(Iterator<Integer> l=map.keySet().iterator();l.hasNext();){
			String fl = (String)map.get(l.next());
			//获取总分类数组
			array1.add(fl);
		}
		//获取笔数分组
		for(int i=0;i<list3.size();i++){
			array3 = new JSONArray();
			proList = (List<Product>) list3.get(i);
			for(int j=0;j<proList.size();j++){
				proInfo = proList.get(j);
				array3.add(proInfo.getNum());
			}
			array4.add(array3);
		}
		//System.out.println("array4: "+array4.toString());
		
		jsonObject.put("FL", array1);
		jsonObject.put("name", array2);
		jsonObject.put("num", array4);
		return jsonObject.toString();
	}
	
}
