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
    	
        final DatabaseHandler db = new DatabaseHandler(getActivity());
        SQLiteDatabase database = db.getWritableDatabase();
        database.execSQL("delete from datas");
        db.addData(new Data(1,"Welcome to the Intensive Management Trail", 
        		
        		"Intensive Management Trail", 
        		
        		"Welcome to Intensive Management Trail, a hiker-only trail located within Oregon State University’s College Forests! This 1.2 mile trail was built to allow forest visitors an"+
        		"opportunity to observe some of the many forest practices that have been developed by OSU forest managers to improve forest health, provide sustainable harvests, and" +
        		"increase biodiversity. These QR codes accompany the interpretive signs that you will encounter along the trail." +
        		"You will pass through at least 13 different managed plots and encounter examples of pruning, thinning, different spacing options,"+
        		"and the sustainable practice of agroforestry, which combines agricultural practices and forestry.",
        		
        		"The forest stands you will encounter were planted in the 1960s and 1970s with seedlings of Douglas-fir " +
        		"(<italic>Pseudotsuga menziesii </italic>) from around the Pacific Northwest with the intent of watching how each different " +
        		"strain would grow in this area different forest practices on the growth and health of the stands. After 30 to 40 years of growth," +
        		" effects of some of those practices are now apparent. Look for numbered posts along the way that correlate to the following stops " +
        		"so you can learn about the different areas as you go."));
        db.addData(new Data(2,"Poison Oak", "Toxicodendron diversilobum", "Toxicodendron diversilobum, " +
        		"commonly named Pacific poison oak or western poison oak (syn. Rhus diversiloba), " +
        		"is a woody vine or shrub in the Anacardiaceae (sumac) family." +
        		" It is widely distributed in western North America," +
        		" inhabiting conifer and mixed broadleaf forests, woodlands, grasslands, and chaparral biomes.", null));
        
        List<Data> data = db.getAllDatas();       
        List<Map<String, String>> dataMap = new ArrayList<Map<String, String>>();
        
        for (Data cn : data) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("title", cn.getTitle());
            datum.put("trail", cn.getTrail());
            dataMap.add(datum);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), (List<? extends Map<String, ?>>) dataMap,
                                                  android.R.layout.simple_list_item_2,
                                                  new String[] {"trail", "title"},
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
    	
    	Data data = db.getDataByTitle(dataName);
    	((TabController)getActivity()).setData(data);
    	ActionBar show = getActivity().getActionBar();
    	show.setSelectedNavigationItem(2);
    }
}
