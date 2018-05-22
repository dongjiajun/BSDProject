package com.example.bsdprojectone.model;


import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.graphics.Color;

//我们需要提前定义好需要的个数，不使用常量，不然不方便设置颜色。
//目前限制种类为4种
//饼形图测试
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
	        series.add("整托盘出库", values[0]);
	        series.add("人工拆盘", values[1]);
	        series.add("机械手拆盘", values[2]);
	        series.add("入库",values[3]);
	        return series;
	      }
		
		public CategorySeries getCategoryDataset()
		{
			this.dataset=buildCategoryDataset("出库数量分布图");
			return dataset;
		}
		
		public DefaultRenderer buildCategoryRenderer(int[] colors) {
	          DefaultRenderer renderer = new DefaultRenderer();
	          renderer.setXAxisColor(Color.BLACK);

	          
	          renderer.setLegendTextSize(20);//设置左下角表注的文字大小
	          renderer.setBackgroundColor(Color.WHITE);
	          renderer.setZoomButtonsVisible(true);//设置显示放大缩小按钮  
	          renderer.setZoomEnabled(false);//设置不允许放大缩小.  
	          renderer.setChartTitleTextSize(30);//设置图表标题的文字大小
	          renderer.setChartTitle("统计结果");//设置图表的标题  默认是居中顶部显示
	          renderer.setLabelsTextSize(25);//饼图上标记文字的字体大小
	          renderer.setLabelsColor(Color.BLACK);//饼图上标记文字的颜色
	          renderer.setPanEnabled(false);//设置是否可以平移
	          renderer.setDisplayValues(true);//是否显示值
	          renderer.setClickEnabled(true);//设置是否可以被点击
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
