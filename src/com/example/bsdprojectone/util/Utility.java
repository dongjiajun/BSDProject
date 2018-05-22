package com.example.bsdprojectone.util;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.bsdprojectone.activity.AchartTestActivity;
import com.example.bsdprojectone.activity.MainActivity;
import com.example.bsdprojectone.activity.PieTestActivity;
import com.example.bsdprojectone.activity.WarnActivity;
import com.example.bsdprojectone.model.ASRSDataAdapter;
import com.example.bsdprojectone.model.AddrInterface;
import com.example.bsdprojectone.model.JsonTestClass;
import com.example.bsdprojectone.model.Warn;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;



public class Utility {
	
	/*��������������(����ͼ����)*/
	public synchronized static boolean handleServerResponseBar(String response,int type)
		{
			if (!TextUtils.isEmpty(response)) //�ſմ����޷�ִ��
			{
				Log.d(AddrInterface.TAG,"��ȡ��������״ͼ���ݳɹ�");
				try{
					
					JSONArray jsonArray=new JSONArray(response);
					for(int tag=0;tag<14;tag++)//��������
					{
						AchartTestActivity.BARPIC_DATA_VALUES[tag]=0;
						AchartTestActivity.BARPIC_COMPLETE_VALUES[tag]=0;
					}
					if(jsonArray.length()!=0)
					{
						for(int i=0;i<jsonArray.length();i++)//ע������Ŀǰֻ���һ��double���飬����˳������б�֤��
						{
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							AchartTestActivity.BARPIC_DATA_VALUES[i]=Double.parseDouble(jsonObject.getString("waysum"));
							AchartTestActivity.BARPIC_COMPLETE_VALUES[i]=Double.parseDouble(jsonObject.getString("waycomplete"));
						}
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else{
				for(int tag=0;tag<14;tag++)//��������
				{
					AchartTestActivity.BARPIC_DATA_VALUES[tag]=0;
					AchartTestActivity.BARPIC_COMPLETE_VALUES[tag]=0;
				}
			}
			return false;		
	}
	
	/*�������������صı���ͼ������*/
	public synchronized static boolean handleServerResponsePie(String response,int type)
	{
		if (!TextUtils.isEmpty(response)) //�ſմ������ִ��
		{
			try{
				JSONObject jsonObject=new JSONObject(response);
				//JSONObject jsonObject = jsonArray.getJSONObject(0);//�����ĸ�˳�����ң���CategoryPic��
				PieTestActivity.PIE_DATA_VALUES[0]=Double.parseDouble(jsonObject.getString("out_full"));
				PieTestActivity.PIE_DATA_VALUES[1]=Double.parseDouble(jsonObject.getString("out_people"));
				PieTestActivity.PIE_DATA_VALUES[2]=Double.parseDouble(jsonObject.getString("out_mechine"));
				PieTestActivity.PIE_DATA_VALUES[3]=Double.parseDouble(jsonObject.getString("in_num"));
				

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return false;		
	}
	
	/*������������ */
	public synchronized static boolean handleTestData(String response,int type)
	{
		if (!TextUtils.isEmpty(response)) //�ſմ������ִ��
		{
			try{
				JSONArray jsonArray = new JSONArray(response);
				for (int i = 0; i < jsonArray.length(); i++) 
				{
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					MainActivity.testList.add(
							new JsonTestClass(
									Integer.parseInt(jsonObject.getString("id")),
									Double.parseDouble(jsonObject.getString("version")),
									jsonObject.getString("name")
									)
					);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return false;
}
	
	/*��������ģ���JSONʵ�ʵ�����*/
	public synchronized static boolean handleFakeData(String response,int type)
	{
		if (!TextUtils.isEmpty(response)) //�ſմ������ִ��
		{
			try{
				JSONArray jsonArray = new JSONArray(response);
				//����ֻ����������ݣ���ֹ����
				MainActivity.fakeList.clear();
				for (int i = 0; i < jsonArray.length(); i++) 
				{
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					//Toast.makeText("", text, duration)
					MainActivity.fakeList.add(
							new ASRSDataAdapter(
									Integer.parseInt(jsonObject.getString("warehouse_in")),
									Integer.parseInt(jsonObject.getString("warehouse_out_all_complete")),
									Integer.parseInt(jsonObject.getString("warehouse_out_floor_one_complete")),
									Integer.parseInt(jsonObject.getString("warehouse_floor_two_people_complete")),
									Integer.parseInt(jsonObject.getString("warehouse_floor_two_mechine_complete")),
									Integer.parseInt(jsonObject.getString("warehouse_out_all_surplus")),
									Integer.parseInt(jsonObject.getString("warehouse_out_floor_one_surplus")),
									Integer.parseInt(jsonObject.getString("warehouse_floor_two_people_surplus")),
									Integer.parseInt(jsonObject.getString("warehouse_floor_two_mechine_surplus"))
									)
					);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return false;
}
	
	/*�������������ص�ʵ������(TableRow)*/
	public synchronized static boolean handleServerResponseRealData(String response,int type)
	{
		Log.d(AddrInterface.TAG,"��ȡ������������ݳɹ�");
		if (!TextUtils.isEmpty(response)) //�ſմ������ִ��
		{
			try{
				JSONObject jsonObject = new JSONObject(response);
				//����ֻ����������ݣ���ֹ����
				MainActivity.realList.clear();
					MainActivity.realList.add(
							new ASRSDataAdapter(
									Integer.parseInt(jsonObject.getString("allin")),
									Integer.parseInt(jsonObject.getString("out_all")),
									Integer.parseInt(jsonObject.getString("out_full")),
									Integer.parseInt(jsonObject.getString("out_people")),
									Integer.parseInt(jsonObject.getString("out_mechine")),
									Integer.parseInt(jsonObject.getString("out_all_surplus")),
									Integer.parseInt(jsonObject.getString("out_full_surplus")),
									Integer.parseInt(jsonObject.getString("out_people_surplus")),
									Integer.parseInt(jsonObject.getString("out_mechine_surplus"))
									)
					);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else{
			MainActivity.realList.clear();//������������Ա�񲿷ֻᱣ�����һ�ε����ӡ�
		}
		return false;
	}
	public synchronized static boolean handleServerResponseWarn(String response,int type)//ע��֮ǰ�ĵط�һ���ò���forѭ����ԭ���Ƿ��ص�json�����Ͳ����������ʽ��ֻҪʹ�����������ʽ�ľͺ��ˡ�
	{
		Log.d(AddrInterface.TAG,"��ȡ�������������ݳɹ�");
		if (!TextUtils.isEmpty(response)) //�ſմ������ִ��
		{
			try{
				JSONArray jsonArray=new JSONArray(response);
				WarnActivity.warnList.clear();
				for(int i=0;i<jsonArray.length();i++)
				{
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					Warn mid=new Warn();
					mid.setDeviceId(jsonObject.getString("dev_id"));
					mid.setWarnFlag(Integer.parseInt(jsonObject.getString("warn_flag")));
					mid.setWarnText(jsonObject.getString("warn_text"));
					WarnActivity.warnList.add(mid);
					
					
				}	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else{
			WarnActivity.warnList.clear();
		}
		return false;
	}
	

}
