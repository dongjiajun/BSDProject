package com.example.bsdprojectone.util;

public interface HttpCallbackListener {

	void onFinish(String response);
	void onError(Exception e);
}
