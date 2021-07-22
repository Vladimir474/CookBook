package com.arhiser.cookbook.screens.main;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;


import com.arhiser.cookbook.App;
import com.arhiser.cookbook.R;
import com.arhiser.cookbook.model.Recept;
import com.arhiser.cookbook.screens.details.EditActivity;
import com.arhiser.cookbook.screens.details.SelectActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.NoteViewHolder> {

    private SortedList<Recept> sortedList;

    public Adapter() {

        sortedList = new SortedList<>(Recept.class, new SortedList.Callback<Recept>() {
            @Override
            public int compare(Recept o1, Recept o2) {
                if (!o2.favorite && o1.favorite) {
                    return -1;
                }
                if (o2.favorite && !o1.favorite) {
                    return 1;
                }
                return 0;
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Recept oldItem, Recept newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Recept item1, Recept item2) {
                return item1.uid == item2.uid;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<Recept> recepts) {
        sortedList.replaceAll(recepts);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView nameDish_tv, time_tv;
        View delete, edit;
        CheckBox favorite;
        Recept recept;

        boolean silentUpdate;

        public NoteViewHolder(@NonNull final View itemView) {
            super(itemView);

            nameDish_tv = itemView.findViewById(R.id.nameDish_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
            favorite = itemView.findViewById(R.id.favorite);

            //кнопки для вызова
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SelectActivity.start((Activity) itemView.getContext(), recept);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.getInstance().getNoteDao().delete(recept);
                }
            });



            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditActivity.start((Activity) itemView.getContext(), recept);
                }
            });

            favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if (!silentUpdate) {
                        recept.favorite = checked;
                        App.getInstance().getNoteDao().update(recept);
                    }
                    updateStrokeOut();
                }
            });


        }

        public void bind(Recept recept) {
            this.recept = recept;

            nameDish_tv.setText(recept.name_dish);
            time_tv.setText(recept.time + " мин.");

            updateStrokeOut();

            silentUpdate = true;
            favorite.setChecked(recept.favorite);
            silentUpdate = false;
        }

        private void updateStrokeOut() {


        }

    }
}
