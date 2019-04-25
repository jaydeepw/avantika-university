package in.edu.avantikauniversity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ProfileViewHolder which gives us access to our views
public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = ProfileAdapter.class.getSimpleName();

    public static final int ITEM_TYPES = 2;
    public static final int ITEM_TYPE_PROFILE = 0;
    public static final int ITEM_TYPE_NAME = 1;

    ArrayList<Object> list;

    public ProfileAdapter(ArrayList<Object> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE_PROFILE) {
            View profileLayout = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.profile_pic, null, false);
            return new ProfileViewHolder(profileLayout);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProfileViewHolder) {
            Object possiblyProfile = list.get(position);
            bindProfile((Profile) possiblyProfile, (ProfileViewHolder) holder);
        }
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
        } else {
            return ITEM_TYPE_NAME;
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

                imageView = (ImageView) itemView.findViewById(R.id.contact_name);
            }
        }
    }

