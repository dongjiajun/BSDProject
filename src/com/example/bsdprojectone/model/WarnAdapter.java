package com.example.bsdprojectone.model;

import java.util.List;

import com.example.bsdprojectone.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WarnAdapter extends ArrayAdapter<Warn>{
	//private static int CORORTEMP=1;
	private int resourceId;
	public WarnAdapter(Context context, int textViewResourceId,
			List<Warn> objects) {
				super(context, textViewResourceId, objects);
				resourceId = textViewResourceId;
			}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		//CORORTEMP++;
		Warn warn = getItem(position); //��ȡ��ǰ�ı��� ʵ��
		View view=null;//��һ�ε�������Ϊ����������
		ViewHolder viewHolder=null;
		if (convertView == null) 
		{
				view = LayoutInflater.from(getContext()).inflate(resourceId, null);
				viewHolder = new ViewHolder();
				viewHolder.warnTextView = (TextView) view.findViewById
				(R.id.warn_content);
			
				view.setTag(viewHolder);//��getTag()������Ӧ
		} 
		else 	{
				view = convertView;
				viewHolder = (ViewHolder) view.getTag(); 
				}
			//.... 
	/*	if(CORORTEMP==99)
			CORORTEMP=1;
		if((CORORTEMP%2)==1)
			view.setBackgroundColor(Color.GREEN);
		else
			view.setBackgroundColor(Color.WHITE);*/
		if((position%2)==1)
			view.setBackgroundColor(Color.parseColor("#b7d5ef"));//#5fc4fa
		else
			view.setBackgroundColor(Color.parseColor("#c8eea8"));
			viewHolder.warnTextView.setText("������Ϣ:"+warn.getDeviceId()+warn.getWarnText());
			viewHolder.warnTextView.setTextColor(Color.RED);
			return view;
		
		}
	class ViewHolder {
			TextView warnTextView;
		}
}
