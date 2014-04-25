package com.example.encyclotreediatest2;

import java.util.Locale;

import net.sourceforge.zbar.Symbol;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TabController extends FragmentActivity implements
		ActionBar.TabListener {
	public static final int ZBAR_SCANNER_REQUEST = 0;
	public static final int ZBAR_OR_SCANNER_REQUEST = 1;
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	

	Data ShowData;
	
	public void setData(Data data){
		ShowData = data;
	}
	public Data getData(){
		return ShowData;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DatabaseHandler db = new DatabaseHandler(this);
		ShowData = db.getData(1);
		setContentView(R.layout.activity_tab_controller);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		
		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_controller, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
		if(tab.getText() == "Show"){
//			Fragment frag = mSectionsPagerAdapter.getItem(2);
//			mSectionsPagerAdapter.notifyDataSetChanged();
//			Log.d(frag.getView().toString(), "testing");
			TextView dataTitle = (TextView) findViewById(R.id.dataTitle);
			TextView dataTrail = (TextView) findViewById(R.id.dataTrail);
			TextView dataQuick = (TextView) findViewById(R.id.dataQuick);
			TextView dataExtra = (TextView) findViewById(R.id.dataExtra);
			dataTitle.setText(Html.fromHtml(ShowData.getTitle()));
			dataTrail.setText(Html.fromHtml(ShowData.getTrail()));
			dataQuick.setText(Html.fromHtml(ShowData.getQuickFacts()));
			dataExtra.setText(Html.fromHtml(ShowData.getExtraText()));
		}
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {

	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			if(position == 0){
				Fragment home = new HomeFragment();
				return home;
			}
			if(position == 1){
				BrowseFragment browse = new BrowseFragment();
				return browse;
			}
			if(position == 2){
//				this.notifyDataSetChanged();
				ShowFragment show = new ShowFragment();
				Bundle bundle = new Bundle();
				bundle.putString("title", ShowData.getTitle());
//				Log.d(ShowData.getName(),ShowData.getName());
				show.setArguments(bundle);
				return show;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return "Home";
			case 1:
				return "Browse";
			case 2:
				return "Show";
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_tab_controller_dummy, container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}
	 public void launchScanner(View v) {
	        if (isCameraAvailable()) {
	            Intent intent = new Intent(this, ZBarScannerActivity.class);
	            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
	        } else {
	            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
	        }
	    }

	    public void launchQRScanner(View v) {
	        if (isCameraAvailable()) {
	            Intent intent = new Intent(this, ZBarScannerActivity.class);
	            intent.putExtra(ZBarConstants.SCAN_MODES, new int[]{Symbol.QRCODE});
	            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
	        } else {
	            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
	        }
	    }

	    public boolean isCameraAvailable() {
	        PackageManager pm = getPackageManager();
	        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
	    }
	    public void onActivityResult(int requestCode, int resultCode, Intent data)
	    {    
	            // Scan result is available by making a call to data.getStringExtra(ZBarConstants.SCAN_RESULT)
	            // Type of the scan result is available by making a call to data.getStringExtra(ZBarConstants.SCAN_RESULT_TYPE)
	            Toast.makeText(this, "Scan Result = " + data.getStringExtra(ZBarConstants.SCAN_RESULT), Toast.LENGTH_SHORT).show();
	            DatabaseHandler db = new DatabaseHandler(this);
	            ShowData = db.getDataByTitle(data.getStringExtra(ZBarConstants.SCAN_RESULT));
	            getActionBar().setSelectedNavigationItem(2);
	            Toast.makeText(this, "Scan Result Type = " + data.getIntExtra(ZBarConstants.SCAN_RESULT_TYPE, 0), Toast.LENGTH_SHORT).show();
	            // The value of type indicates one of the symbols listed in Advanced Options below.
	    }

}
