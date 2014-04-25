package com.example.encyclotreediatest2;

import com.example.encyclotreediatest2.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
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
		String title = this.getArguments().getString("title");
		View showView = inflater.inflate(
				R.layout.fragment_show, container, false);
		Data data = db.getDataByTitle(title);
		if(data != null){
			TextView data_title = (TextView) showView.findViewById(R.id.dataTitle);
			TextView data_trail = (TextView) showView.findViewById(R.id.dataTrail);
			TextView data_quickfacts = (TextView) showView.findViewById(R.id.dataQuick);
			TextView data_extratext = (TextView) showView.findViewById(R.id.dataExtra);

			data_title.setText(Html.fromHtml(data.getTitle()));
			data_trail.setText(Html.fromHtml(data.getTrail()));
			data_quickfacts.setText(Html.fromHtml(data.getQuickFacts()));
			data_extratext.setText(Html.fromHtml(data.getExtraText()));
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
