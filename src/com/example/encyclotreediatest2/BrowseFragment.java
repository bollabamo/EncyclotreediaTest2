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
    	
        final DatabaseHandler db = new DatabaseHandler(getActivity());
        SQLiteDatabase database = db.getWritableDatabase();
        database.execSQL("delete from datas");
//        db.addData(new Data(1,"Welcome to the Intensive Management Trail", 
//        		
//        		"Intensive Management Trail", 
//        		
//        		"Welcome to Intensive Management Trail, a hiker-only trail located within Oregon State University’s College Forests! This 1.2 mile trail was built to allow forest visitors an"+
//        		"opportunity to observe some of the many forest practices that have been developed by OSU forest managers to improve forest health, provide sustainable harvests, and" +
//        		"increase biodiversity. These QR codes accompany the interpretive signs that you will encounter along the trail." +
//        		"You will pass through at least 13 different managed plots and encounter examples of pruning, thinning, different spacing options,"+
//        		"and the sustainable practice of agroforestry, which combines agricultural practices and forestry.",
//        		
//        		"The forest stands you will encounter were planted in the 1960s and 1970s with seedlings of Douglas-fir " +
//        		"(<em>Pseudotsuga menziesii </em>) from around the Pacific Northwest with the intent of watching how each different " +
//        		"strain would grow in this area different forest practices on the growth and health of the stands. After 30 to 40 years of growth," +
//        		" effects of some of those practices are now apparent. Look for numbered posts along the way that correlate to the following stops " +
//        		"so you can learn about the different areas as you go."));
//        db.addData(new Data(2,"Poison Oak", "Toxicodendron diversilobum", "Toxicodendron diversilobum, " +
//        		"commonly named Pacific poison oak or western poison oak (syn. Rhus diversiloba), " +
//        		"is a woody vine or shrub in the Anacardiaceae (sumac) family." +
//        		" It is widely distributed in western North America," +
//        		" inhabiting conifer and mixed broadleaf forests, woodlands, grasslands, and chaparral biomes.", null));
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
                 Log.d("Test", Integer.toString(RowData.length));
                 if(RowData.length >=4 && RowData[3] != null){
                	 String extra = RowData[3].replaceAll("^\"|\"$", "");
                	 db.addData(new Data(title, trail, quick, extra));
                 }
                 else{
                	 db.addData(new Data(title, trail, quick, null));
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
