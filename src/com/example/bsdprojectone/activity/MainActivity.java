package com.example.bsdprojectone.activity;



//�������ͣʹ�á�
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
 * testList		����JSON���ݽ���ʹ���������
 * fakeList		��������ʵ������ʹ���������
 * realList		ʵ�����ݴ��ʹ�õ�����
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
	
	
	//����¼���ʵ��
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
				getPieData(11);//�������ݵĽ���
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
			
			public void onFinish(final String response) {//�ɹ�
				new Thread((new Runnable(){
					public void run() {
						Utility.handleServerResponseRealData(response, 1);
						}
				})).start();
			}
			
			public void onError(Exception e) {//���� 
				new Thread((new Runnable(){
					public void run() {
						/*.�쳣�߼�.*/
						String content="";//�������ݣ��мǲ����пո�
						Utility.handleServerResponseRealData(content, 1);
						Log.d(AddrInterface.TAG,"��ȡ�������ʧ�ܣ������Ƿ�֤�������֮��ĵ����糩ͨ");
						}
				})).start();
			}
		});
	}
	
	//��ȡ��ͼ��������
	public  static void getPieData(int id)
	{
		String address=AddrInterface.CONNECT_SERVER_PIE;
		HttpUtil.sendHttpRequest(address,new HttpCallbackListener(){
			
			public void onFinish(final String response) {//�ɹ�
				new Thread((new Runnable(){
					public void run() {
						Utility.handleServerResponsePie(response, 1);
						}
				})).start();
			}
			
			public void onError(Exception e) {//���� 
				new Thread((new Runnable(){
					public void run() {
						/*..*/
						Log.d(AddrInterface.TAG,"��ȡ����ͼ����ʧ�ܣ������Ƿ�֤�������֮��ĵ����糩ͨ");
						}
				})).start();
			}
		});
	}
	
	//��ȡ��״ͼ��������
	public static void getBarData(int id)
	{
		String address=AddrInterface.CONNECT_SERVER_BAR;
		HttpUtil.sendHttpRequest(address,new HttpCallbackListener(){
			
			public void onFinish(final String response) {//�ɹ�
				new Thread((new Runnable(){
					public void run() {
						Utility.handleServerResponseBar(response, 1);
					}
				})).start();
			}
			
			public void onError(Exception e) {//���� 
				new Thread((new Runnable(){
					public void run() {
						/*.�쳣�߼�.*/
						String content="";//�������ݣ��мǲ����пո�
						Utility.handleServerResponseRealData(content, 1);
						Log.d(AddrInterface.TAG,"��ȡ��״ͼ����ʧ�ܣ������Ƿ�֤�������֮��ĵ����糩ͨ");
						}
				})).start();
			}
		});
	}

}
