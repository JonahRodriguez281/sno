package edu.cnm.deepdive.sno.controller;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.sno.R;
import edu.cnm.deepdive.sno.databinding.FragmentGearDialogBinding;
import edu.cnm.deepdive.sno.model.entity.Gear;
import edu.cnm.deepdive.sno.viewmodel.ProfileViewModel;
import org.jetbrains.annotations.NotNull;

/**
 * Creates a dialog popup in the {@link ProfileFragment} that adds gear into the database, according
 * to what the user specifies in the text fields.
 */
public class GearDialogFragment extends DialogFragment implements TextWatcher {

  private FragmentGearDialogBinding binding;
  private AlertDialog dialog;
  private ProfileViewModel viewModel;
  private long id;
  private Gear gear;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    id = GearDialogFragmentArgs.fromBundle(getArguments()).getGearId();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    binding = FragmentGearDialogBinding.inflate(
        LayoutInflater.from(getContext()), null, false);
    dialog = new Builder(getContext())
        .setIcon(R.drawable.ic_add)
        .setTitle(R.string.add_gear)
        .setView(binding.getRoot())
        .setNeutralButton(android.R.string.cancel, (dlg, which) -> {})
        .setPositiveButton(android.R.string.ok, (dlg, which) -> create())
        .create();
    dialog.setOnShowListener((dlg) -> checkSubmitConditions());
    return dialog;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull @NotNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    binding.gearType.addTextChangedListener(this);
    binding.description.addTextChangedListener(this);
    if (id != 0) {
      viewModel.setGearId(id);
      viewModel.getGear().observe(getViewLifecycleOwner(), (gear) -> {
        this.gear = gear;
        binding.gearType.setText(gear.getGearType().toString());
        binding.description.setText(gear.getDescription());
      });
    } else {
      gear = new Gear();
    }
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {
    checkSubmitConditions();
  }

  private void checkSubmitConditions() {
    Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
    //noinspection ConstantConditions
    positive.setEnabled(
        !binding.gearType.getText().toString().trim().isEmpty() &&
            !binding.description.getText().toString().trim().isEmpty());
  }

  private void create() {
//    gear.getGearType();
//    GearType gearType = binding.gearType.g;
    String description = binding.description.getText().toString().trim();
    gear.setGearType(gear.getGearType());
    gear.setDescription(description);
    viewModel.update(gear);
  }
}