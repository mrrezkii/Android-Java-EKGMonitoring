package com.mobcomm.ekgmonitoring.Fragment;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.mobcomm.ekgmonitoring.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.telkom.iot.AntaresHTTPAPI;
import id.co.telkom.iot.AntaresResponse;

public class MeasureFragment extends Fragment implements AntaresHTTPAPI.OnResponseListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cpBPMLevel)
    CircleProgressBar cpBPMLevel;

    private final String TAG = "ANTARES-API";
    private final String APIKEY = "004cbd64ff8a7fd4:53c77e7cf8c628fd";
    private final String APPNAME = "MyMedication";
    private final String DEVICENAME = "KotakObat";
    private AntaresHTTPAPI antaresAPIHTTP;
    private String dataDevice;
    private ValueAnimator animator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_measure, container, false);
        ButterKnife.bind(this, rootView);
        antaresAPIHTTP = new AntaresHTTPAPI();
        antaresAPIHTTP.addListener(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        cpBPMLevel.setProgressFormatter((progress, max) -> progress + " BPM");
        antaresAPIHTTP.getLatestDataofDevice(APIKEY, APPNAME, DEVICENAME);
    }

    @Override
    public void onResponse(AntaresResponse antaresResponse) {
        Log.d(TAG, Integer.toString(antaresResponse.getRequestCode()));
        if (antaresResponse.getRequestCode() == 0) {
            try {
                JSONObject body = new JSONObject(antaresResponse.getBody());
                dataDevice = body.getJSONObject("m2m:cin").getString("con");
                String numberOnly = dataDevice.replaceAll("[^0-9]", "");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        animator = ValueAnimator.ofInt(60, 100);
                        animator.addUpdateListener(animation -> {
                            int progress = (int) animation.getAnimatedValue();
                            cpBPMLevel.setProgress(progress);
                        });
                        animator.setIntValues(Integer.parseInt(numberOnly));
                        animator.setDuration(1000);
                        animator.start();
                    }
                });
                Log.d(TAG, dataDevice);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}