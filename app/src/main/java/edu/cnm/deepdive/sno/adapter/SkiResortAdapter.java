package edu.cnm.deepdive.sno.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.sno.adapter.SkiResortAdapter.Holder;
import edu.cnm.deepdive.sno.databinding.SkiResortItemBinding;
import edu.cnm.deepdive.sno.model.entity.SkiResort;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Adapter to attach the list of {@link SkiResort} to the RecyclerView in the
 * {@link edu.cnm.deepdive.sno.controller.ResortFragment}.
 */
public class SkiResortAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final LayoutInflater inflater;
  private final List<SkiResort> resorts;
  private final OnResortClickListener listener;

  /**
   * Constructs an instance of the SkiResortAdapter
   * @param context Application context
   * @param resorts List of ski resorts
   * @param listener Listener for clicking on an item
   */
  public SkiResortAdapter(Context context, List<SkiResort> resorts,
      OnResortClickListener listener) {
    this.context = context;
    this.resorts = resorts;
    inflater = LayoutInflater.from(context);
    this.listener = listener;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    SkiResortItemBinding binding = SkiResortItemBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return resorts.size();
  }

  /**
   * Holder class for the RecyclerView
   */
  class Holder extends RecyclerView.ViewHolder {

    private final SkiResortItemBinding binding;

    /**
     * Constructs an instance of holder
     * @param binding SkiResortItem binding
     */
    public Holder(SkiResortItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      SkiResort skiResort = resorts.get(position);
      binding.skiResortListItem.setText(skiResort.getName());
      itemView.setOnClickListener((v) -> listener.onClick(skiResort));
    }
  }

  /**
   * Interface for the onClickListener when selecting an item in the RecyclerView.
   */
  public interface OnResortClickListener {
    void onClick(SkiResort skiResort);
  }
}
