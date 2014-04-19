package com.example.encyclotreediatest2;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class BrowseFragment extends ListFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	View view = inflater.inflate(
    			R.layout.fragment_browse, container, false);
    	final ListView listView = (ListView) view.findViewById((android.R.id.list));
    	
        final DatabaseHandler db = new DatabaseHandler(getActivity());
        SQLiteDatabase database = db.getWritableDatabase();
        database.execSQL("delete from datas");
        db.addData(new Data(1,"Poison Ivy", "Toxicodendron rydbergii", "Poison ivy belongs to the family Anacardiaceae (the Sumac family)." +
        		" It grows primarily in temperate climates in the Americas and Asia."));
        db.addData(new Data(2,"Poison Oak", "Toxicodendron diversilobum", "Toxicodendron diversilobum, " +
        		"commonly named Pacific poison oak or western poison oak (syn. Rhus diversiloba), " +
        		"is a woody vine or shrub in the Anacardiaceae (sumac) family." +
        		" It is widely distributed in western North America," +
        		" inhabiting conifer and mixed broadleaf forests, woodlands, grasslands, and chaparral biomes."));
        
        List<Data> data = db.getAllDatas();       
        List<Map<String, String>> dataMap = new ArrayList<Map<String, String>>();
        
        for (Data cn : data) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("name", cn.getName());
            datum.put("latin", cn.getLatin());
            dataMap.add(datum);
        }
        
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), (List<? extends Map<String, ?>>) dataMap,
                                                  android.R.layout.simple_list_item_2,
                                                  new String[] {"name", "latin"},
                                                  new int[] {android.R.id.text1,
                                                             android.R.id.text2});
        listView.setAdapter(adapter);
		return view;
        
    }
    public void onListItemClick(ListView l, View v, int position, long id){
    	super.onListItemClick(l, v, position, id);
    	final DatabaseHandler db = new DatabaseHandler(getActivity());
    	Object obj = l.getItemAtPosition(position);
    	String[] fullString = obj.toString().split("=");
    	String dataName = fullString[2].substring(0, fullString[2].length()-1);
    	
    	Data data = db.getDataByName(dataName);
    	((TabController)getActivity()).setData(data);
    	ActionBar show = getActivity().getActionBar();
    	show.setSelectedNavigationItem(2);
    }
}
