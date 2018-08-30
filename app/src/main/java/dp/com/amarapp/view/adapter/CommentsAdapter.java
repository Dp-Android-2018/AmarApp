package dp.com.amarapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.CommentListItemBinding;
import dp.com.amarapp.model.pojo.CompanyComments;
import dp.com.amarapp.view.holder.CommentViewHolder;

public class CommentsAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private ObservableList<CompanyComments> comments;

    public CommentsAdapter(ObservableList<CompanyComments> comments) {
        this.comments=comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommentListItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.comment_list_item,parent,false);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.bindItemComment(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
