package com.xlyuan.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.orsoncharts.util.json.JSONArray;
import com.orsoncharts.util.json.JSONObject;
import com.xlyuan.demo.Product;

//import net.sf.json.JSONArray;

public class test {
	public static void main(String[] args)throws Exception{
		List<Product> list= new ArrayList<Product>(); 
		//总行
		list.add(new Product("综合授信",10));
		list.add(new Product("单笔授信",15));
		list.add(new Product("额度项下授信",11));
		list.add(new Product("线上贷款",20));
		list.add(new Product("金融市场",16));
		list.add(new Product("国际业务",10));
		list.add(new Product("银行承兑汇票",24));
		list.add(new Product("核心交易",31));
		list.add(new Product("小微IPC",19));
		
		//村行
		List<Product> list2= new ArrayList<Product>(); 
		list2.add(new Product("综合授信",5));
		list2.add(new Product("单笔授信",10));
		list2.add(new Product("额度项下授信",10));
		list2.add(new Product("线上贷款",26));
		list2.add(new Product("金融市场",12));
		list2.add(new Product("国际业务",27));
		list2.add(new Product("银行承兑汇票",14));
		list2.add(new Product("核心交易",36));
		list2.add(new Product("小微IPC",9));
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
		
		String json2 = getProductJson(list3,listFl,map);
		System.out.println("JSON数据2："+json2);
		
		//随机数
		
		
	}
	public static String getProductJson2(Map map){
		if(map == null)return"";
		JSONArray array = null;
		JSONArray array2= new JSONArray();
		JSONObject jsonObject = null;
		JSONObject jsonObject2 = null;
		Product proInfo = null;
		List<Product> proList =null;
		int l=0;
		for(Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();){
			l++;
			String key = iterator.next();
			proList = (List<Product>) map.get(key);
			jsonObject2=  new JSONObject();
			array = new JSONArray();
			for(int i=0;i<proList.size();i++){
				proInfo = proList.get(i);
				jsonObject = new JSONObject();
				jsonObject.put("name",proInfo.getName());
				jsonObject.put("num",proInfo.getNum());
				array.add(i,jsonObject);
			}
			jsonObject2.put(key, array);
			System.out.println("jsonObject2"+jsonObject2.toString());
			array2.add(l-1,jsonObject2);
		}
		
		return array2.toString();
	} 
	public static String getProductJson(List list3,List listFl,Map map){
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
			//array4.add(array3);
			//System.out.println("array4: "+array4.toString());
			array4.add(array3);
		}
		System.out.println("array4: "+array4.toString());
		
		jsonObject.put("FL", array1);
		jsonObject.put("name", array2);
		jsonObject.put("num", array4);
		return jsonObject.toString();
	}
	public static String getJson(List<Product> pro){
		JSONArray array = new JSONArray();
		array.addAll(pro);
		return array.toString();
	}
}
