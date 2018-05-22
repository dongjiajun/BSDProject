package com.example.bsdprojectone.activity;

/*AChartengine柱状图效果测试类*/

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
 * NUMBER 巷道总数
 * BARPIC_DATA_VALUES  巷道总任务数
 * BARPIC_COMPLETE_VALUES	巷道已完成任务数
 * THREADCUT    在切换时，即便finish Activiyt,handler也无法直接释放，使用此变量作为伴随状态。
 * */

public class AchartTestActivity extends BaseActivity{
	GraphicalView graphicalView;//achartengine  视图
	LinearLayout layout;
	private static int THREADCUT=1;//0 代表销毁，1代表运行,线程终止标记(重)
	
	public static int NUMBER=14;
	public static double[] BARPIC_DATA_VALUES=new double[NUMBER];//默认值为0
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
	
	/*刷新时间的Runnable*/
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
				Message msg=new Message();//一个Message不能重复使用，必须重新生成。
				msg.what=1;//1代表刷新时间
				msg.obj=date;
				handler.sendMessage(msg);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					//Log.d(TAG,"更新时间异常");
				}
				
			}
		}
	};
	
	/*刷新数据的Runnable*/
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
				MainActivity.getTableRowRealData(1);//获取最新的表格数据
				MainActivity.getBarData(1);//获取最新的柱状图数据
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					//Log.d(TAG,"更新数据异常");
				}
				handler.sendMessage(msg);
				
			}
			}
	};
	
	/*获取报警状态的Runnable*/
	Runnable queryWarnStatus=new Runnable(){
		public void run(){
			while(true)
			{
				if(THREADCUT==0)
				{
					break;
				}
				Message msg=new Message();//一个Message不能重复使用，必须重新生成。
				msg.what=3;
				getWarnData(1);
				try {
					Thread.sleep(5000);//由之前的十秒缩短到了5秒
				} catch (InterruptedException e) {
					e.printStackTrace();
					//Log.d(TAG,"获取报警状态异常");
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
		
		setTableData();//这里数据的设置是无效的，但是并不影响程序的使用。
		testBarPic();
		new Thread(updateThread).start();//时间更新
		new Thread(updatePage).start();//内容更新
		new Thread(queryWarnStatus).start();//定时检测报警情况
		Log.d("Achart","onCreate on");

		
		
	}
	
	protected void onResume()
	{
		super.onResume();
		//Log.d(AddrInterface.TAG,"继续柱状图活动");
		//Toast.makeText(AchartTestActivity.this, "on resume", Toast.LENGTH_SHORT).show();
	}
	//监听报警状态
	public void getWarnData(int id)
	{
		String address=AddrInterface.CONNECT_SERVER_WARN;
		HttpUtil.sendHttpRequest(address,new HttpCallbackListener(){
			
			public void onFinish(final String response) {//成功
				runOnUiThread(new Runnable(){
					public void run() {
						Utility.handleServerResponseWarn(response, 2);
					}
				});
			}
			
			public void onError(Exception e) {//错误 
				runOnUiThread(new Runnable(){
					public void run() {
						Log.d(AddrInterface.TAG,"无法连接到服务器获取报警数据");
						Toast.makeText(AchartTestActivity.this, "无法连接到服务器获取报警数据，请检查网络状态", Toast.LENGTH_LONG);
					}
				});
			}
		});
	}
			
	
	//给table设置实际值，在此之前需获取数据。
	private void setTableData()
	{
		if(!MainActivity.realList.isEmpty())//与服务器断开连接时或者服务器返回空数据时，无法进入。
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
	 * 将从服务器获取的数据转换为实际百分比
	 * */
	private double[] getRealPersent()
	{
		//还需要一个检测是否有数据的流程，暂时不用。
		double []real=new double[NUMBER];
		DecimalFormat df=new DecimalFormat("######0.0");//定义返回的浮点数格式，保留一位小数点。
		for(int i=0;i<NUMBER;i++)
		{
			real[i]=Double.parseDouble(df.format(((BARPIC_COMPLETE_VALUES[i]/BARPIC_DATA_VALUES[i])*100)));
		}
		return real;
	}
	
	
	/*
	 * 生成柱状图,在此之前必须先获取数据，需要注意的是为了保证程序效率，即数据交互部分为子线程中完成，所以会存在需要一定延时才行。
	 * */
	private void testBarPic()
	{
		String[] titles={"出库百分比"};//一个title对应着一种颜色，但是数据量的个数不能超过限制
		int[] colors={Color.GREEN};//测试用柱体颜色
		List<double[]> values=new ArrayList<double[]>();
		values.add(getRealPersent());//测试数据，这是针对一种title的情况
		//Toast.makeText(AchartTestActivity.this, values.get(0)[0]+"", Toast.LENGTH_SHORT).show();
		BarPic barPic=new BarPic();
		barPic.buildBarRenderer(colors);//按照我现在的封装方式，需要以下三步才能创建一个柱形图的实例。
		barPic.Init();
		barPic.buildBarDataSet(titles, values);

		graphicalView=ChartFactory.getBarChartView(getBaseContext(),barPic.getBarDataset(),barPic.getBarRenderer(),barPic.type);//柱形图
		layout.removeAllViews();//这一句十分重要，柱状图能否刷新的关键
		layout.addView(graphicalView);
	}

	/*
	 * //在点击返回退出当前页面时，务必将线程伴随标记置0，不然即使activity结束，
	 * 线程无法终止，将会一直生成新的页面，虽然launchMode为singleTask,
	 * 但一定会造成程序无法结束的问题。
	 * */
	protected void onStop()
	{
		super.onStop();
		Log.d("Achart","on Stop");
		THREADCUT=0;
	}
	
	
}
