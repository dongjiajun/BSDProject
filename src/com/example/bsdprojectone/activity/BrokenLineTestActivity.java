package com.example.bsdprojectone.activity;

import java.util.ArrayList;
import java.util.List;


import com.example.bsdprojectone.R;
import com.example.bsdprojectone.model.BrokenLinePic;
import com.example.bsdprojectone.util.BaseActivity;



import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;


/*achartengine����ͼЧ�������ࣨ�˴���Ϊ���ϲ��ң�*/
public class BrokenLineTestActivity  extends BaseActivity{
	private LinearLayout layout;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.broken_test_layout);
		testBroken();
	}
	public void testBroken()
	{
		List<String> test_list=new ArrayList<String>();
		test_list.add("123");
		test_list.add("456");
		layout=(LinearLayout)findViewById(R.id.broken_layout);
		BrokenLinePic.setchart(BrokenLineTestActivity.this,layout,new String[]{"Ѫ��ֵ"},
        		new int[]{Color.GREEN},test_list,"ʱ��","Ѫ��ֵ");
	}

        
       

}
