package com.example.bsdprojectone.model;


import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.graphics.Color;

//������Ҫ��ǰ�������Ҫ�ĸ�������ʹ�ó�������Ȼ������������ɫ��
//Ŀǰ��������Ϊ4��
//����ͼ����
public class CategoryPic {
	private CategorySeries dataset;
	private DefaultRenderer renderer;
	public  double[] values;
	public static int[] COLORS={Color.BLUE,Color.GREEN,Color.MAGENTA,Color.RED};
	public CategoryPic()
	{
		this.dataset=null;
		this.renderer=null;
	}
	
		public void setValues(double []real_value)
		{
			
			values=new double[10];
			int i=0;
			for(double val:real_value)
			{
				values[i++]=val;
			}
		}
		
		public CategorySeries buildCategoryDataset(String title) 
		{
	        CategorySeries series = new CategorySeries(title);
	        series.add("�����̳���", values[0]);
	        series.add("�˹�����", values[1]);
	        series.add("��е�ֲ���", values[2]);
	        series.add("���",values[3]);
	        return series;
	      }
		
		public CategorySeries getCategoryDataset()
		{
			this.dataset=buildCategoryDataset("���������ֲ�ͼ");
			return dataset;
		}
		
		public DefaultRenderer buildCategoryRenderer(int[] colors) {
	          DefaultRenderer renderer = new DefaultRenderer();
	          renderer.setXAxisColor(Color.BLACK);

	          
	          renderer.setLegendTextSize(20);//�������½Ǳ�ע�����ִ�С
	          renderer.setBackgroundColor(Color.WHITE);
	          renderer.setZoomButtonsVisible(true);//������ʾ�Ŵ���С��ť  
	          renderer.setZoomEnabled(false);//���ò�����Ŵ���С.  
	          renderer.setChartTitleTextSize(30);//����ͼ���������ִ�С
	          renderer.setChartTitle("ͳ�ƽ��");//����ͼ��ı���  Ĭ���Ǿ��ж�����ʾ
	          renderer.setLabelsTextSize(25);//��ͼ�ϱ�����ֵ������С
	          renderer.setLabelsColor(Color.BLACK);//��ͼ�ϱ�����ֵ���ɫ
	          renderer.setPanEnabled(false);//�����Ƿ����ƽ��
	          renderer.setDisplayValues(true);//�Ƿ���ʾֵ
	          renderer.setClickEnabled(true);//�����Ƿ���Ա����
	          renderer.setShowCustomTextGrid(true);

	          //renderer.setMargins(new int[] { 1000, 300, 15,0 });
	          //margins - an array containing the margin size values, in this order: top, left, bottom, right
	          for (int color : colors) 
	          {
	            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
	            r.setColor(color);
	            renderer.addSeriesRenderer(r);
	          }
	        return renderer;
	      }
		
		public DefaultRenderer getCategoryRenderer()
		{
			this.renderer=buildCategoryRenderer(COLORS);
			return renderer;
		}

}
