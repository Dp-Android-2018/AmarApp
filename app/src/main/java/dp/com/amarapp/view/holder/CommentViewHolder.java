package dp.com.amarapp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import dp.com.amarapp.databinding.CommentListItemBinding;
import dp.com.amarapp.model.pojo.AdvertContent;
import dp.com.amarapp.model.pojo.CompanyComments;
import dp.com.amarapp.viewmodel.CommentItemViewModel;
import dp.com.amarapp.viewmodel.ItemAdvertViewModel;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private CommentListItemBinding binding;
    public CommentViewHolder(CommentListItemBinding binding) {
        super(binding.rlParent);
        this.binding=binding;
    }

    public void bindItemComment(CompanyComments comment){
        //if (comment!=null && advert.getTitle()!=null && advert.getContent()!=null)
            //System.out.println("Responses Size  companyInfo :"+companyInfo.getName()+" "+companyInfo.getCity().getName());
            if(binding.getCommentViewModel()==null)
                binding.setCommentViewModel(new CommentItemViewModel(itemView.getContext(),comment));
            else
                binding.getCommentViewModel().setComment(comment);

    }
}
