package com.example.encyclotreediatest2;

import net.sourceforge.zbar.Symbol;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;

public class HomeFragment extends Fragment {
	
	public static final int ZBAR_SCANNER_REQUEST = 0;
	public static final int ZBAR_OR_SCANNER_REQUEST = 1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(
    			R.layout.fragment_home, container, false);
        Button scan = (Button) view.findViewById(R.id.scanButton);
        	scan.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((TabController)getActivity()).launchQRScanner(v);
				}
			});

        return view;
    }
}
