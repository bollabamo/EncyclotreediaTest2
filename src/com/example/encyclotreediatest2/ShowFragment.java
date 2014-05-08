package com.example.encyclotreediatest2;

import com.example.encyclotreediatest2.R;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ShowFragment extends Fragment {
//	Data ShowData;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View showView = inflater.inflate(
				R.layout.fragment_show, container, false);
		return showView;
	}
}
