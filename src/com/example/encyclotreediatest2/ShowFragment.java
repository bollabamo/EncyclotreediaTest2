package com.example.encyclotreediatest2;

import com.example.encyclotreediatest2.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShowFragment extends Fragment {
	Data ShowData;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final DatabaseHandler db = new DatabaseHandler(getActivity());
		String name = this.getArguments().getString("name");
		Log.d(name, name);
		View showView = inflater.inflate(
				R.layout.fragment_show, container, false);
		Data data = db.getDataByName(name);
		if(data != null){	
			TextView data_name = (TextView) showView.findViewById(R.id.dataName);
			TextView data_latin = (TextView) showView.findViewById(R.id.dataLatin);
			TextView data_descrip = (TextView) showView.findViewById(R.id.dataDescrip);

			data_name.setText(data.getName());
			data_latin.setText(data.getLatin());
			data_descrip.setText(data.getDescription());
		}
			return showView;
	}
//	public void onStart(){
//		super.onStart();
//		
//		final DatabaseHandler db = new DatabaseHandler(getActivity());
//		String name = this.getArguments().getString("name");
//		Data data = db.getDataByName(name);
//		if(data != null){
////    	String name = db.getDataByName("Poison Ivy").getName();
//		}
//	}
}
