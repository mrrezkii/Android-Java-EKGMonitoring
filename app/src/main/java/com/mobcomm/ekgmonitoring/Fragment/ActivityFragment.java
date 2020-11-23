package com.mobcomm.ekgmonitoring.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.mobcomm.ekgmonitoring.R;
import com.mobcomm.ekgmonitoring.Util.ChartBaseUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityFragment extends ChartBaseUtil {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.chart1)
    BarChart chart;

    public static Fragment newInstance() {
        return new ActivityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_activity, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        chart.getDescription().setEnabled(false);
        chart.setMaxVisibleValueCount(60);
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        chart.getAxisLeft().setDrawGridLines(false);
        chart.animateY(1500);
        chart.getLegend().setEnabled(false);

        showChart();
    }

    private void showChart() {
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "BarChart");
    }

}