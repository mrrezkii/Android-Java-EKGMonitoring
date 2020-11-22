package com.mobcomm.ekgmonitoring.Fragment;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.mobcomm.ekgmonitoring.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeasureFragment extends Fragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cpBPMLevel)
    CircleProgressBar cpBPMLevel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_measure, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        cpBPMLevel.setProgressFormatter((progress, max) -> progress + " BPM");
        simulateProgress();
    }

    private void simulateProgress() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(animation -> {
            int progress = (int) animation.getAnimatedValue();
            cpBPMLevel.setProgress(progress);
        });
        animator.setIntValues(60);
        animator.setDuration(2500);
        animator.start();
    }

}