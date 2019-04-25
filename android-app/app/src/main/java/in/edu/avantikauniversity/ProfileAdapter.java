package in.edu.avantikauniversity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ProfileViewHolder which gives us access to our views
public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = ProfileAdapter.class.getSimpleName();

    public static final int ITEM_TYPE_PROFILE = 0;
    public static final int ITEM_TYPE_BIO = 1;

    ArrayList<Object> list;

    public ProfileAdapter(ArrayList<Object> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE_PROFILE) {
            View profileLayout = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_profile_pic, null, false);
            return new ProfileViewHolder(profileLayout);
        } else if(viewType == ITEM_TYPE_BIO) {
            View bioLayout = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_bio, null, false);
            return new BioViewHolder(bioLayout);
        }  else {
            Log.w(TAG, "Invalid type of view received");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProfileViewHolder) {
            Object possiblyProfile = list.get(position);
            bindProfile((Profile) possiblyProfile, (ProfileViewHolder) holder);
        } else if (holder instanceof BioViewHolder) {
            Object possiblyBio = list.get(position);
            bindBio((String) possiblyBio, (BioViewHolder) holder);
        } else {
            Log.e(TAG, "Unable to bind this type");
        }
    }

    private void bindBio(String bio, BioViewHolder holder) {
        holder.textViewBio.setText(bio);
        holder.textViewBio.setTag(bio);
        holder.textViewBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        v.getTag().toString(),
                        Toast.LENGTH_LONG)
                .show();
            }
        });
    }

    private void bindProfile(Profile profile, ProfileViewHolder holder) {
        Picasso.get().load(profile.dp).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "position: " + position);
        Object object = list.get(position);
        if (object instanceof Profile) {
            return ITEM_TYPE_PROFILE;
        } else if (object instanceof String) {
            return ITEM_TYPE_BIO;
        } else {
            Log.e(TAG,"Unknowntype of view");
            return -1;
        }
    }

    // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public class ProfileViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public ImageView imageView;

            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ProfileViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ProfileViewHolder instance.
                super(itemView);

                imageView = itemView.findViewById(R.id.contact_name);
            }
        }

    public class BioViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewBio;

        public BioViewHolder(View itemView) {
            super(itemView);

            textViewBio = itemView.findViewById(R.id.textview_bio);
        }
    }
    }

