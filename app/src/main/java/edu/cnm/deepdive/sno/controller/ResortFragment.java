package edu.cnm.deepdive.sno.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import edu.cnm.deepdive.sno.adapter.SkiResortAdapter;
import edu.cnm.deepdive.sno.databinding.FragmentResortBinding;
import edu.cnm.deepdive.sno.viewmodel.ResortViewModel;
import org.jetbrains.annotations.NotNull;

public class ResortFragment extends Fragment {

  private FragmentResortBinding binding;
  private ResortViewModel viewModel;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentResortBinding.inflate(inflater);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(ResortViewModel.class);
    getLifecycle().addObserver(viewModel);
    viewModel.getAllSkiResorts().observe(getViewLifecycleOwner(), (skiResorts) -> {
      SkiResortAdapter adapter = new SkiResortAdapter(getContext(), skiResorts, (resort) ->
          Navigation.findNavController(getView()).navigate(ResortFragmentDirections.showWeather(
              (float) resort.getLatitude(), (float) resort.getLongitude())));
      //noinspection ConstantConditions
      binding.skiResortList.addItemDecoration(
          new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
      binding.skiResortList.setAdapter(adapter);
    });
  }
}