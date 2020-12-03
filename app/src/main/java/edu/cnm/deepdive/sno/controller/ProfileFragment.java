package edu.cnm.deepdive.sno.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.sno.R;
import edu.cnm.deepdive.sno.databinding.FragmentProfileBinding;
import edu.cnm.deepdive.sno.viewmodel.ProfileViewModel;
import org.jetbrains.annotations.NotNull;

/**
 * Fragment to inflate the UI for the profile screen, binding various text elements and attributes
 * with information from the database.
 */
public class ProfileFragment extends Fragment {

  private ProfileViewModel profileViewModel;
  private FragmentProfileBinding binding;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentProfileBinding.inflate(inflater);
    profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    profileViewModel.getDistance().observe(getViewLifecycleOwner(),
        (number) -> binding.distanceValue.setText(String.valueOf(number)));
    profileViewModel.getDaysLogged().observe(getViewLifecycleOwner(),
        (days) -> binding.daysLoggedValue.setText(String.valueOf(days)));
    setHasOptionsMenu(true);
    return binding.getRoot();
  }

  @Override
  public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
    inflater.inflate(R.menu.add_options, menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
    boolean handled = true;
    switch (item.getItemId()) {
      case R.id.add_item:
        Navigation.findNavController(getView()).navigate(ProfileFragmentDirections.openDialog());
        handled = true;
//        addGear();
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }
    return false;
  }

  private void addGear() {
  }
}