package com.example.bsdprojectone.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.bsdprojectone.R;
import com.example.bsdprojectone.model.AddrInterface;
import com.example.bsdprojectone.model.Warn;
import com.example.bsdprojectone.model.WarnAdapter;
import com.example.bsdprojectone.util.ActivityCollector;
import com.example.bsdprojectone.util.BaseActivity;
import com.example.bsdprojectone.util.HttpCallbackListener;
import com.example.bsdprojectone.util.HttpUtil;
import com.example.bsdprojectone.util.Utility;

public class WarnActivity extends BaseActivity{

		private ListView warnListView;
		private WarnAdapter adapter;
		private static int THREADCUTWARN=1;
		//private static int TEMPSTATUS=1;
		
		public static ArrayList<Warn> warnList=new ArrayList<Warn>();
		
		Handler handler=new Handler(){
			public void handleMessage(Message msg)
			{
				switch(msg.what)
				{
					case 1:
					{

						
						if(warnList.size()==0)
						{
							//TEMPSTATUS=0;
							WarnActivity.this.finish();
							if(WarnActivity.this.isFinishing())
							{
								THREADCUTWARN=0;
							}
							Intent intent=new Intent(WarnActivity.this,AchartTestActivity.class);
							startActivity(intent);
						}
						break;
					}
					case 2:
					{
						/*if(warnList.size()==0)
							{	
								WarnActivity.this.finish();
								if(WarnActivity.this.isFinishing())
								{
									THREADCUTWARN=0;
								}
									Intent intent=new Intent(WarnActivity.this,AchartTestActivity.class);
									startActivity(intent);
							}
						*/
						break;
					}
				}
				
			}
		};
		
		Runnable updateWarn=new Runnable(){
			public void run(){
				int i=0;
				/*for(i=0;i<2;i++)
				{
					if(i==0)
					{
						Message msg=new Message();
						msg.what=1;
						handler.sendMessage(msg);
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if(i==1)
					{
						Message msg=new Message();
						msg.what=2;
						WarnActivity.warnList.clear();
						handler.sendMessage(msg);
					}
					
				}*/
				while(true)
				{
					try {
						Thread.sleep(100);//这段时间用来等待将THREADCUTWARN置0
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					if(THREADCUTWARN==0)//防止不断生成新的AchartTestActivity
					{
						break;
					}
					Message msg=new Message();//一个Message不能重复使用，必须重新生成。
					msg.what=1;
					
					
					getWarnData(1);//获取最新信息
					try {
						Thread.sleep(2000);//需要消除页面关闭之后的等待时间，可以从这里入手。
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					handler.sendMessage(msg);	
				}
			}
		};
		protected void onCreate(Bundle savedInstanceState)
		{
			THREADCUTWARN=1;
			//TEMPSTATUS=1;
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.warn_layout);
			

			warnListView=(ListView)findViewById(R.id.warn_listview);
			adapter = new WarnAdapter(WarnActivity.this,
					R.layout.warn_item, warnList);
			warnListView.setAdapter(adapter);
			warnListView.setOnItemClickListener(new OnItemClickListener()
			{
				public void onItemClick(AdapterView<?>parent,View view,int position,long id)
				{
					Toast.makeText(WarnActivity.this, "warn test", Toast.LENGTH_SHORT).show();
				}
			}
					);
			//getWarnData(1);//在handler中已经调用了，所以不需要
			new Thread(updateWarn).start();
			Log.d("warn","onCreate on");

		}
		
		
		//在AchartTestActivity中还存在一个副本。
		public void getWarnData(int id)
		{
			String address=AddrInterface.CONNECT_SERVER_WARN;
			HttpUtil.sendHttpRequest(address,new HttpCallbackListener(){
				
				public void onFinish(final String response) {//成功
					runOnUiThread(new Runnable(){
						public void run() {
							Utility.handleServerResponseWarn(response, 2);
							//Toast.makeText(WarnActivity.this, response, Toast.LENGTH_SHORT).show();
							adapter.notifyDataSetChanged();//刷新显示
							warnListView.setEnabled(false);
							
							
						}
					});
				}
				
				public void onError(Exception e) {//错误 
					runOnUiThread(new Runnable(){
						public void run() {
							/*..测试通过*/
							String content="";
							Utility.handleServerResponseWarn(content, 2);
							Log.d(AddrInterface.TAG,"获取报警数据失败,请确认与服务器连接正常");
							
						}
					});
				}
			});
		}
		
		protected void onResume()
		{
			super.onResume();
			//Log.d(AddrInterface.TAG,"继续警告页面");
			//Toast.makeText(WarnActivity.this, "on resume", Toast.LENGTH_SHORT).show();
		}
		protected void onStop()
		{
			super.onStop();
			THREADCUTWARN=0;
		}
}
