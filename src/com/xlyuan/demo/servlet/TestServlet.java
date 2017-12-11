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
		//����
		list.add(new Product("�ۺ�����",rand.nextInt(50)));
		list.add(new Product("��������",rand.nextInt(50)));
		list.add(new Product("�����������",rand.nextInt(50)));
		list.add(new Product("���ϴ���",rand.nextInt(50)));
		list.add(new Product("�����г�",rand.nextInt(50)));
		list.add(new Product("����ҵ��",rand.nextInt(50)));
		list.add(new Product("���гжһ�Ʊ",rand.nextInt(50)));
		list.add(new Product("���Ľ���",rand.nextInt(50)));
		list.add(new Product("С΢IPC",rand.nextInt(50)));
		
		Random rand2 = new java.util.Random();
		//����
		List<Product> list2= new ArrayList<Product>(); 
		list2.add(new Product("�ۺ�����",rand2.nextInt(60)));
		list2.add(new Product("��������",rand2.nextInt(60)));
		list2.add(new Product("�����������",rand2.nextInt(60)));
		list2.add(new Product("���ϴ���",rand2.nextInt(60)));
		list2.add(new Product("�����г�",rand2.nextInt(60)));
		list2.add(new Product("����ҵ��",rand2.nextInt(60)));
		list2.add(new Product("���гжһ�Ʊ",rand2.nextInt(60)));
		list2.add(new Product("���Ľ���",rand2.nextInt(60)));
		list2.add(new Product("С΢IPC",rand2.nextInt(60)));
		
//		//�ĵ�ͼģʽ
//		List<Product> list4= new ArrayList<Product>(); 
//		list4.add(new Product("�ۺ�����",));
//		list4.add(new Product("��������",rand2.nextInt(60)));
//		list4.add(new Product("�����������",rand2.nextInt(60)));
//		list4.add(new Product("���ϴ���",rand2.nextInt(60)));
//		list4.add(new Product("�����г�",rand2.nextInt(60)));
//		list4.add(new Product("����ҵ��",rand2.nextInt(60)));
//		list4.add(new Product("���гжһ�Ʊ",rand2.nextInt(60)));
//		list4.add(new Product("���Ľ���",rand2.nextInt(60)));
//		list4.add(new Product("С΢IPC",rand2.nextInt(60)));
		
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
		
		String json = getProductJson(list3,listFl,map);
		System.out.println("JSON���ݣ�"+json);
		//ObjectMapper mapper1 = new ObjectMapper();
		//String json = mapper1.writeValueAsString(list);		
		
		//System.out.println("JSON���ݣ�"+json);
		//��json���ݷ��ظ��ͻ���
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(json);
		//JSONArray
		//ObjectMapper mapper = new ObjectMapper();
        
        //1.���json��װ
        
		
	}
	
	public  String getProductJson(List list3,List listFl,Map map){
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
			array4.add(array3);
		}
		//System.out.println("array4: "+array4.toString());
		
		jsonObject.put("FL", array1);
		jsonObject.put("name", array2);
		jsonObject.put("num", array4);
		return jsonObject.toString();
	}
	
}
