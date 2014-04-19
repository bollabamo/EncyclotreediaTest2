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
//    public void launchScanner(View v) {
//        if (isCameraAvailable()) {
//            Intent intent = new Intent(getActivity(), ZBarScannerActivity.class);
//            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
//        } else {
//            Toast.makeText(getActivity(), "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void launchQRScanner(View v) {
//        if (isCameraAvailable()) {
//            Intent intent = new Intent(getActivity(), ZBarScannerActivity.class);
//            intent.putExtra(ZBarConstants.SCAN_MODES, new int[]{Symbol.QRCODE});
//            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
//        } else {
//            Toast.makeText(getActivity(), "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public boolean isCameraAvailable() {
//        PackageManager pm = getActivity().getPackageManager();
//        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
//    }
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {    
//            // Scan result is available by making a call to data.getStringExtra(ZBarConstants.SCAN_RESULT)
//            // Type of the scan result is available by making a call to data.getStringExtra(ZBarConstants.SCAN_RESULT_TYPE)
//            Toast.makeText(getActivity(), "Scan Result = " + data.getStringExtra(ZBarConstants.SCAN_RESULT), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), "Scan Result Type = " + data.getIntExtra(ZBarConstants.SCAN_RESULT_TYPE, 0), Toast.LENGTH_SHORT).show();
//            // The value of type indicates one of the symbols listed in Advanced Options below.
//    }
}
