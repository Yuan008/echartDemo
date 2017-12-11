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
		//����
		list.add(new Product("�ۺ�����",10));
		list.add(new Product("��������",15));
		list.add(new Product("�����������",11));
		list.add(new Product("���ϴ���",20));
		list.add(new Product("�����г�",16));
		list.add(new Product("����ҵ��",10));
		list.add(new Product("���гжһ�Ʊ",24));
		list.add(new Product("���Ľ���",31));
		list.add(new Product("С΢IPC",19));
		
		//����
		List<Product> list2= new ArrayList<Product>(); 
		list2.add(new Product("�ۺ�����",5));
		list2.add(new Product("��������",10));
		list2.add(new Product("�����������",10));
		list2.add(new Product("���ϴ���",26));
		list2.add(new Product("�����г�",12));
		list2.add(new Product("����ҵ��",27));
		list2.add(new Product("���гжһ�Ʊ",14));
		list2.add(new Product("���Ľ���",36));
		list2.add(new Product("С΢IPC",9));
		List list3 = new ArrayList();
		list3.add(list);
		list3.add(list2);
		
		//����
		List<String> listFl = new ArrayList<String>();
		listFl.add("�ۺ�����");
		listFl.add("��������");
		listFl.add("�����������");
		listFl.add("���ϴ���");
		listFl.add("�����г�");
		listFl.add("����ҵ��");
		listFl.add("���гжһ�Ʊ");
		listFl.add("���Ľ���");
		listFl.add("С΢IPC");
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(1,"����");
		map.put(2, "����");
		
		String json2 = getProductJson(list3,listFl,map);
		System.out.println("JSON����2��"+json2);
		
		//�����
		
		
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
		JSONArray array1 = new JSONArray();//�ܷ���
		JSONArray array2 = new JSONArray();//���
		JSONArray array3 = null ;//����
		JSONArray array4 = new JSONArray() ;//��������
		JSONObject jsonObject = new JSONObject();
		Product proInfo = null;
		List<Product> proList = null;
		//��ȡ�������
		for(int i=0;i<listFl.size();i++){
			array2.add(listFl.get(i));
		}
		for(Iterator<Integer> l=map.keySet().iterator();l.hasNext();){
			String fl = (String)map.get(l.next());
			//��ȡ�ܷ�������
			array1.add(fl);
		}
		//��ȡ��������
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
