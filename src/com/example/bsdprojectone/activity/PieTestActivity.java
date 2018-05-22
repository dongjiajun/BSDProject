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
//achartengine����ͼЧ��������

/*@param
 * NUMBER		��ͼ��ǰ���ֵĿ���
 * PIE_DATA_VALUES	��Ҫ��datasetʹ�õ�ʵ�ʱ�ͼ������
 * */
public class PieTestActivity  extends BaseActivity{
	
	GraphicalView graphicalView;
	LinearLayout layout;
	public static int NUMBER=4;//�����ĸ���
	public static double[] PIE_DATA_VALUES=new double[NUMBER];//˳�������̣��˹�����е�֣����
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_pie_test);
		layout=(LinearLayout)findViewById(R.id.pie_test);
		testPiePic();
	}
	
		//���Ա���ͼ
		private void testPiePic()
		{
			int[] colors={Color.BLUE,Color.GREEN,Color.MAGENTA,Color.RED};//��������ɫ
			CategoryPic categoryPic=new CategoryPic();
			
			categoryPic.setValues(PIE_DATA_VALUES);
			graphicalView=ChartFactory.getPieChartView(getBaseContext(),categoryPic.getCategoryDataset(),categoryPic.getCategoryRenderer());//����ͼ
			layout.addView(graphicalView);
		}

}
