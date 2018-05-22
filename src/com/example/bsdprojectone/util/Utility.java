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
	
	/*解析服务器数据(柱形图部分)*/
	public synchronized static boolean handleServerResponseBar(String response,int type)
		{
			if (!TextUtils.isEmpty(response)) //放空代码无法执行
			{
				Log.d(AddrInterface.TAG,"获取服务器柱状图数据成功");
				try{
					
					JSONArray jsonArray=new JSONArray(response);
					for(int tag=0;tag<14;tag++)//清零数据
					{
						AchartTestActivity.BARPIC_DATA_VALUES[tag]=0;
						AchartTestActivity.BARPIC_COMPLETE_VALUES[tag]=0;
					}
					if(jsonArray.length()!=0)
					{
						for(int i=0;i<jsonArray.length();i++)//注意这里目前只添加一个double数组，所以顺序必须有保证。
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
				for(int tag=0;tag<14;tag++)//清零数据
				{
					AchartTestActivity.BARPIC_DATA_VALUES[tag]=0;
					AchartTestActivity.BARPIC_COMPLETE_VALUES[tag]=0;
				}
			}
			return false;		
	}
	
	/*解析服务器返回的饼形图的数据*/
	public synchronized static boolean handleServerResponsePie(String response,int type)
	{
		if (!TextUtils.isEmpty(response)) //放空代码可以执行
		{
			try{
				JSONObject jsonObject=new JSONObject(response);
				//JSONObject jsonObject = jsonArray.getJSONObject(0);//下面四个顺序不能乱，见CategoryPic。
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
	
	/*解析测试数据 */
	public synchronized static boolean handleTestData(String response,int type)
	{
		if (!TextUtils.isEmpty(response)) //放空代码可以执行
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
	
	/*解析测试模拟的JSON实际的数据*/
	public synchronized static boolean handleFakeData(String response,int type)
	{
		if (!TextUtils.isEmpty(response)) //放空代码可以执行
		{
			try{
				JSONArray jsonArray = new JSONArray(response);
				//这里只能先清除数据，防止叠加
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
	
	/*解析服务器返回的实际数据(TableRow)*/
	public synchronized static boolean handleServerResponseRealData(String response,int type)
	{
		Log.d(AddrInterface.TAG,"获取服务器表格数据成功");
		if (!TextUtils.isEmpty(response)) //放空代码可以执行
		{
			try{
				JSONObject jsonObject = new JSONObject(response);
				//这里只能先清除数据，防止叠加
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
			MainActivity.realList.clear();//这里清除，所以表格部分会保持最后一次的样子。
		}
		return false;
	}
	public synchronized static boolean handleServerResponseWarn(String response,int type)//注意之前的地方一条用不了for循环的原因是返回的json本来就不是数组的形式，只要使它返回数组格式的就好了。
	{
		Log.d(AddrInterface.TAG,"获取服务器报警数据成功");
		if (!TextUtils.isEmpty(response)) //放空代码可以执行
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
