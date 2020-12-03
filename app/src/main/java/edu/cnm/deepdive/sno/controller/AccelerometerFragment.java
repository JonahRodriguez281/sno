package edu.cnm.deepdive.sno.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.sno.databinding.FragmentAccelerometerBinding;
import edu.cnm.deepdive.sno.viewmodel.AccelerometerViewModel;

/**
 * Fragment to inflate the UI in for the Accelerometer screen.
 */
public class AccelerometerFragment extends Fragment {

  private AccelerometerViewModel accelerometerViewModel;
  private FragmentAccelerometerBinding binding;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentAccelerometerBinding.inflate(inflater);
    accelerometerViewModel = new ViewModelProvider(this).get(AccelerometerViewModel.class);
    accelerometerViewModel.getMaxSpeed().observe(getViewLifecycleOwner(),
        (integer) -> binding.maxSpeedNumber.setText(String.valueOf(integer)));
    return binding.getRoot();
  }
}