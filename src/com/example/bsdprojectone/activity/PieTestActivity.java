package com.example.bsdprojectone.activity;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;

import com.example.bsdprojectone.R;
import com.example.bsdprojectone.model.CategoryPic;
import com.example.bsdprojectone.util.BaseActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
//achartengine饼形图效果测试类

/*@param
 * NUMBER		饼图当前划分的块数
 * PIE_DATA_VALUES	需要给dataset使用的实际饼图的数据
 * */
public class PieTestActivity  extends BaseActivity{
	
	GraphicalView graphicalView;
	LinearLayout layout;
	public static int NUMBER=4;//定义四个块
	public static double[] PIE_DATA_VALUES=new double[NUMBER];//顺序，整托盘，人工，机械手，入库
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_pie_test);
		layout=(LinearLayout)findViewById(R.id.pie_test);
		testPiePic();
	}
	
		//测试饼形图
		private void testPiePic()
		{
			int[] colors={Color.BLUE,Color.GREEN,Color.MAGENTA,Color.RED};//测试用颜色
			CategoryPic categoryPic=new CategoryPic();
			
			categoryPic.setValues(PIE_DATA_VALUES);
			graphicalView=ChartFactory.getPieChartView(getBaseContext(),categoryPic.getCategoryDataset(),categoryPic.getCategoryRenderer());//饼形图
			layout.addView(graphicalView);
		}

}
