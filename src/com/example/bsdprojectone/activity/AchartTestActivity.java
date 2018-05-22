package com.example.bsdprojectone.activity;

/*AChartengine��״ͼЧ��������*/

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.RangeBarChart;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bsdprojectone.R;
import com.example.bsdprojectone.model.ASRSDataAdapter;
import com.example.bsdprojectone.model.AddrInterface;
import com.example.bsdprojectone.model.BarPic;
import com.example.bsdprojectone.model.BrokenLinePic;
import com.example.bsdprojectone.model.CategoryPic;
import com.example.bsdprojectone.util.BaseActivity;
import com.example.bsdprojectone.util.HttpCallbackListener;
import com.example.bsdprojectone.util.HttpUtil;
import com.example.bsdprojectone.util.Utility;

/*@param
 * NUMBER �������
 * BARPIC_DATA_VALUES  �����������
 * BARPIC_COMPLETE_VALUES	��������������
 * THREADCUT    ���л�ʱ������finish Activiyt,handlerҲ�޷�ֱ���ͷţ�ʹ�ô˱�����Ϊ����״̬��
 * */

public class AchartTestActivity extends BaseActivity{
	GraphicalView graphicalView;//achartengine  ��ͼ
	LinearLayout layout;
	private static int THREADCUT=1;//0 �������٣�1��������,�߳���ֹ���(��)
	
	public static int NUMBER=14;
	public static double[] BARPIC_DATA_VALUES=new double[NUMBER];//Ĭ��ֵΪ0
	public static double[] BARPIC_COMPLETE_VALUES=new double[NUMBER];
	
	private TextView inall_textView;
	private TextView out_full_textView;
	private TextView out_people_textView;
	private TextView out_mechine_textView;
	private TextView out_full_surplus_textView;
	private TextView out_people_surplus_textView;
	private TextView out_mechine_surplus_textView;
	private TextView time_show;
	
	Handler handler=new Handler(){
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				case 1:
				{
					time_show.setText(msg.obj.toString());break;
				}
				case 2:
				{
					setTableData();
					testBarPic();
					break;
				}
				case 3:
				{
					
					if(WarnActivity.warnList.size()!=0)
					{
						AchartTestActivity.this.finish();
						if(AchartTestActivity.this.isFinishing())
						{
							THREADCUT=0;
						}
						Intent intent=new Intent(AchartTestActivity.this,WarnActivity.class);//
						startActivity(intent);
					}
				}
			}
			
		}
	};
	
	/*ˢ��ʱ���Runnable*/
	Runnable updateThread=new Runnable(){
		public void run(){
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date;
			
			while(true)
			{
				if(THREADCUT==0)
				{
					break;
				}
				date=dateFormat.format(new java.util.Date());
				Message msg=new Message();//һ��Message�����ظ�ʹ�ã������������ɡ�
				msg.what=1;//1����ˢ��ʱ��
				msg.obj=date;
				handler.sendMessage(msg);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					//Log.d(TAG,"����ʱ���쳣");
				}
				
			}
		}
	};
	
	/*ˢ�����ݵ�Runnable*/
	Runnable updatePage=new Runnable(){
		public void run(){
			while(true)
			{
				if(THREADCUT==0)
				{
					break;
				}
				Message msg=new Message();
				msg.what=2;
				MainActivity.getTableRowRealData(1);//��ȡ���µı������
				MainActivity.getBarData(1);//��ȡ���µ���״ͼ����
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					//Log.d(TAG,"���������쳣");
				}
				handler.sendMessage(msg);
				
			}
			}
	};
	
	/*��ȡ����״̬��Runnable*/
	Runnable queryWarnStatus=new Runnable(){
		public void run(){
			while(true)
			{
				if(THREADCUT==0)
				{
					break;
				}
				Message msg=new Message();//һ��Message�����ظ�ʹ�ã������������ɡ�
				msg.what=3;
				getWarnData(1);
				try {
					Thread.sleep(5000);//��֮ǰ��ʮ�����̵���5��
				} catch (InterruptedException e) {
					e.printStackTrace();
					//Log.d(TAG,"��ȡ����״̬�쳣");
				}
				handler.sendMessage(msg);
			}
		}
	};
	
	
	

	
	
	protected void onCreate(Bundle savedInstanceState)
	{
		THREADCUT=1;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_achart);
		layout=(LinearLayout)findViewById(R.id.bar_test);
		inall_textView=(TextView)findViewById(R.id.all_in_textview);
		out_full_textView=(TextView)findViewById(R.id.out_full_textview);
		out_people_textView=(TextView)findViewById(R.id.out_people_textview);
		out_mechine_textView=(TextView)findViewById(R.id.out_mechine_textview);
		out_full_surplus_textView=(TextView)findViewById(R.id.out_full_surplus_textview);
		out_people_surplus_textView=(TextView)findViewById(R.id.out_people_surplus_textview);
		out_mechine_surplus_textView=(TextView)findViewById(R.id.out_mechine_surplus_textview);
		time_show=(TextView)findViewById(R.id.time_show);
		
		setTableData();//�������ݵ���������Ч�ģ����ǲ���Ӱ������ʹ�á�
		testBarPic();
		new Thread(updateThread).start();//ʱ�����
		new Thread(updatePage).start();//���ݸ���
		new Thread(queryWarnStatus).start();//��ʱ��ⱨ�����
		Log.d("Achart","onCreate on");

		
		
	}
	
	protected void onResume()
	{
		super.onResume();
		//Log.d(AddrInterface.TAG,"������״ͼ�");
		//Toast.makeText(AchartTestActivity.this, "on resume", Toast.LENGTH_SHORT).show();
	}
	//��������״̬
	public void getWarnData(int id)
	{
		String address=AddrInterface.CONNECT_SERVER_WARN;
		HttpUtil.sendHttpRequest(address,new HttpCallbackListener(){
			
			public void onFinish(final String response) {//�ɹ�
				runOnUiThread(new Runnable(){
					public void run() {
						Utility.handleServerResponseWarn(response, 2);
					}
				});
			}
			
			public void onError(Exception e) {//���� 
				runOnUiThread(new Runnable(){
					public void run() {
						Log.d(AddrInterface.TAG,"�޷����ӵ���������ȡ��������");
						Toast.makeText(AchartTestActivity.this, "�޷����ӵ���������ȡ�������ݣ���������״̬", Toast.LENGTH_LONG);
					}
				});
			}
		});
	}
			
	
	//��table����ʵ��ֵ���ڴ�֮ǰ���ȡ���ݡ�
	private void setTableData()
	{
		if(!MainActivity.realList.isEmpty())//��������Ͽ�����ʱ���߷��������ؿ�����ʱ���޷����롣
		{
			ASRSDataAdapter mid=(ASRSDataAdapter)MainActivity.realList.get(0);
			inall_textView.setText(String.valueOf(mid .getIn()));
			out_full_textView.setText(String.valueOf(mid.getOutFloorOneComplete()));
			out_people_textView.setText(String.valueOf(mid.getOutFloorTwoPeopleComplete()));
			out_mechine_textView.setText(String.valueOf(mid.getOutFloorTwoMechineComplete()));
			out_full_surplus_textView.setText(String.valueOf(mid.getOutFloorOneSurplus()));
			out_people_surplus_textView.setText(String.valueOf(mid.getOutFloorTwoPeopleSurplus()));
			out_mechine_surplus_textView.setText(String.valueOf(mid.getOutFloorTwoMechineSurplus()));
		}
	}
	
	/*
	 * ���ӷ�������ȡ������ת��Ϊʵ�ʰٷֱ�
	 * */
	private double[] getRealPersent()
	{
		//����Ҫһ������Ƿ������ݵ����̣���ʱ���á�
		double []real=new double[NUMBER];
		DecimalFormat df=new DecimalFormat("######0.0");//���巵�صĸ�������ʽ������һλС���㡣
		for(int i=0;i<NUMBER;i++)
		{
			real[i]=Double.parseDouble(df.format(((BARPIC_COMPLETE_VALUES[i]/BARPIC_DATA_VALUES[i])*100)));
		}
		return real;
	}
	
	
	/*
	 * ������״ͼ,�ڴ�֮ǰ�����Ȼ�ȡ���ݣ���Ҫע�����Ϊ�˱�֤����Ч�ʣ������ݽ�������Ϊ���߳�����ɣ����Ի������Ҫһ����ʱ���С�
	 * */
	private void testBarPic()
	{
		String[] titles={"����ٷֱ�"};//һ��title��Ӧ��һ����ɫ�������������ĸ������ܳ�������
		int[] colors={Color.GREEN};//������������ɫ
		List<double[]> values=new ArrayList<double[]>();
		values.add(getRealPersent());//�������ݣ��������һ��title�����
		//Toast.makeText(AchartTestActivity.this, values.get(0)[0]+"", Toast.LENGTH_SHORT).show();
		BarPic barPic=new BarPic();
		barPic.buildBarRenderer(colors);//���������ڵķ�װ��ʽ����Ҫ�����������ܴ���һ������ͼ��ʵ����
		barPic.Init();
		barPic.buildBarDataSet(titles, values);

		graphicalView=ChartFactory.getBarChartView(getBaseContext(),barPic.getBarDataset(),barPic.getBarRenderer(),barPic.type);//����ͼ
		layout.removeAllViews();//��һ��ʮ����Ҫ����״ͼ�ܷ�ˢ�µĹؼ�
		layout.addView(graphicalView);
	}

	/*
	 * //�ڵ�������˳���ǰҳ��ʱ����ؽ��̰߳�������0����Ȼ��ʹactivity������
	 * �߳��޷���ֹ������һֱ�����µ�ҳ�棬��ȻlaunchModeΪsingleTask,
	 * ��һ������ɳ����޷����������⡣
	 * */
	protected void onStop()
	{
		super.onStop();
		Log.d("Achart","on Stop");
		THREADCUT=0;
	}
	
	
}
