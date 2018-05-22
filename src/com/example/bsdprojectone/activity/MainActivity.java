package com.example.bsdprojectone.activity;



//本活动已暂停使用。
import java.util.ArrayList;

import com.example.bsdprojectone.R;
import com.example.bsdprojectone.model.ASRSDataAdapter;
import com.example.bsdprojectone.model.AddrInterface;
import com.example.bsdprojectone.model.JsonTestClass;
import com.example.bsdprojectone.util.BaseActivity;
import com.example.bsdprojectone.util.HttpCallbackListener;
import com.example.bsdprojectone.util.HttpUtil;
import com.example.bsdprojectone.util.Utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*@param
 * testList		测试JSON数据解析使用类的链表
 * fakeList		测试所需实际数据使用类的链表
 * realList		实际数据存放使用的链表
 * */
public class MainActivity extends BaseActivity implements OnClickListener{
	private TextView textviewAll;
	private Button button_bar;
	private Button button_pie;
	private Button button_broken;
	public  static ArrayList<JsonTestClass> testList=new ArrayList<JsonTestClass>();
	public  static ArrayList<ASRSDataAdapter> fakeList=new ArrayList<ASRSDataAdapter>();
	public  static ArrayList<ASRSDataAdapter> realList=new ArrayList<ASRSDataAdapter>(); 
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		button_bar=(Button)findViewById(R.id.button_bar);
		button_pie=(Button)findViewById(R.id.button_pie);
		button_broken=(Button)findViewById(R.id.button_broken);
		
		button_bar.setOnClickListener(this);
		button_pie.setOnClickListener(this);
		button_broken.setOnClickListener(this);
		
		
	}
	
	
	//点击事件的实现
	public void onClick(View view)
	{
		switch(view.getId())
		{
			case R.id.button_bar:
			{
				getBarData(11);
				getTableRowRealData(11);
				Intent intentBar=new Intent(MainActivity.this,AchartTestActivity.class);
				startActivity(intentBar);
				break;
			}
			case R.id.button_pie:
			{
				getPieData(11);//测试数据的交互
				Intent intentPie=new Intent(MainActivity.this,PieTestActivity.class);
				startActivity(intentPie);
				break;
			}
			case R.id.button_broken:
			{
				Intent intentWarn=new Intent(MainActivity.this,WarnActivity.class);
				startActivity(intentWarn);
				break;
			}
			default:break;
		}
	}
	
	public static void getTableRowRealData(int id)
	{
		String address=AddrInterface.CONNECT_SERVER_TABLE;
		HttpUtil.sendHttpRequest(address,new HttpCallbackListener(){
			
			public void onFinish(final String response) {//成功
				new Thread((new Runnable(){
					public void run() {
						Utility.handleServerResponseRealData(response, 1);
						}
				})).start();
			}
			
			public void onError(Exception e) {//错误 
				new Thread((new Runnable(){
					public void run() {
						/*.异常逻辑.*/
						String content="";//做空数据，切记不能有空格
						Utility.handleServerResponseRealData(content, 1);
						Log.d(AddrInterface.TAG,"获取表格数据失败，请检测是否保证与服务器之间的的网络畅通");
						}
				})).start();
			}
		});
	}
	
	//获取饼图更新数据
	public  static void getPieData(int id)
	{
		String address=AddrInterface.CONNECT_SERVER_PIE;
		HttpUtil.sendHttpRequest(address,new HttpCallbackListener(){
			
			public void onFinish(final String response) {//成功
				new Thread((new Runnable(){
					public void run() {
						Utility.handleServerResponsePie(response, 1);
						}
				})).start();
			}
			
			public void onError(Exception e) {//错误 
				new Thread((new Runnable(){
					public void run() {
						/*..*/
						Log.d(AddrInterface.TAG,"获取饼形图数据失败，请检测是否保证与服务器之间的的网络畅通");
						}
				})).start();
			}
		});
	}
	
	//获取柱状图更新数据
	public static void getBarData(int id)
	{
		String address=AddrInterface.CONNECT_SERVER_BAR;
		HttpUtil.sendHttpRequest(address,new HttpCallbackListener(){
			
			public void onFinish(final String response) {//成功
				new Thread((new Runnable(){
					public void run() {
						Utility.handleServerResponseBar(response, 1);
					}
				})).start();
			}
			
			public void onError(Exception e) {//错误 
				new Thread((new Runnable(){
					public void run() {
						/*.异常逻辑.*/
						String content="";//做空数据，切记不能有空格
						Utility.handleServerResponseRealData(content, 1);
						Log.d(AddrInterface.TAG,"获取柱状图数据失败，请检测是否保证与服务器之间的的网络畅通");
						}
				})).start();
			}
		});
	}

}
