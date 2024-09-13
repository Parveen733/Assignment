package com.demo.assignment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.assignment.databinding.RowUserBinding;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<UserTbl> userList = new ArrayList<>();
    private OnUserItemListener onUserItemListener;

    // Constructor
    public UserAdapter(OnUserItemListener onUserItemListener) {
        this.onUserItemListener = onUserItemListener;
    }

    // Set user list and notify adapter
    public void setUserList(List<UserTbl> users) {
        this.userList = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowUserBinding binding = RowUserBinding.inflate(inflater, parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserTbl user = userList.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private final RowUserBinding binding;

        public UserViewHolder(RowUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(UserTbl user) {
            binding.tvUserName.setText(user.getFirstName());
            binding.tvUserEmail.setText(user.getEmail());
            // Assuming you want to load the avatar image here
            // Load avatar with Glide or Picasso if needed
            // Glide.with(binding.ivUserAvatar.getContext()).load(user.getAvatar()).into(binding.ivUserAvatar);

            // Set up click listener
            binding.ivUploadImg.setOnClickListener(v -> {
                if (onUserItemListener != null) {
                    onUserItemListener.onItemClick(
                            String.valueOf(user.getId()), // Assuming you want to use user ID
                            user.getFirstName(), // Assuming you want to use user name
                            user.getAvatar(), // Assuming you want to use user profile picture URL
                            v,
                            this
                    );
                }
            });
        }
    }

    // Interface for item click listener
    public interface OnUserItemListener {
        void onItemClick(String id, String userName, String userProf, View view, RecyclerView.ViewHolder holder);
    }

}
