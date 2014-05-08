package com.example.encyclotreediatest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.encyclotreediatest2.Data;
import com.example.encyclotreediatest2.DatabaseHandler;
import com.example.encyclotreediatest2.R;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.app.FragmentManager;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class BrowseFragment extends ListFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	View view = inflater.inflate(
    			R.layout.fragment_browse, container, false);
    	final ListView listView = (ListView) view.findViewById((android.R.id.list));
    	
    	
    	//Inserts all info into database, will need to be moved to the update activity once it's created
        final DatabaseHandler db = new DatabaseHandler(getActivity());
        SQLiteDatabase database = db.getWritableDatabase();
        database.execSQL("delete from datas");
        InputStream is;
		try {
			is = getActivity().getAssets().open("QR Code Intensive Management Trail Content.csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                 String[] RowData = line.split("~");
                 String title = RowData[1].replaceAll("^\"|\"$", "");
                 String trail = RowData[0].replaceAll("^\"|\"$", "");
                 String quick = RowData[2].replaceAll("^\"|\"$", "");
                 String extra = RowData[3].replaceAll("^\"|\"$", ""); 
                 String images = RowData[4].replaceAll("^\"|\"$", "");
                 if(RowData[3] != null){
                	 
                	 db.addData(new Data(title, trail, quick, extra, images));
                 }
                 else{
                	 db.addData(new Data(title, trail, quick, null, images));
                 }
            }
            is.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        List<Data> data = db.getAllDatas();       
        List<Map<String, String>> dataMap = new ArrayList<Map<String, String>>();
        
        for (Data cn : data) {
            Map<String, String> datum = new HashMap<String, String>(2);
//            datum.put("title", cn.getTitle());
            datum.put("trail", cn.getTrail());
            dataMap.add(datum);
        }
        //For some reason, entering trail makes the title show up instead. Need to fix this, but for now it works
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), (List<? extends Map<String, ?>>) dataMap,
                                                  android.R.layout.simple_list_item_1,
                                                  new String[] {"trail"},
                                                  new int[] {android.R.id.text1});

        listView.setAdapter(adapter);
		return view;
        
    }
    public void onListItemClick(ListView l, View v, int position, long id){
    	super.onListItemClick(l, v, position, id);
    	final DatabaseHandler db = new DatabaseHandler(getActivity());
    	Object obj = l.getItemAtPosition(position);

    	String[] fullString = obj.toString().split("=");
//    	String dataName = fullString[2].substring(0, fullString[2].length()-1);
    	String dataName = fullString[1].replaceAll("^\"|\"$", "").substring(0, fullString[1].length()-1);
    	Data data = db.getDataByTitle(dataName);
    	((TabController)getActivity()).setData(data);
    	ActionBar show = getActivity().getActionBar();
    	show.setSelectedNavigationItem(2);
    }
}
