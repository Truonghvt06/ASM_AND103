package truonghvph35818.fpoly.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import truonghvph35818.fpoly.Model.SlideItem;
import truonghvph35818.fpoly.R;


public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder>{

    private List<SlideItem> slideItems;
    private ViewPager2 viewPager2;

    public SlideAdapter(List<SlideItem> slideItems, ViewPager2 viewPager2) {
        this.slideItems = slideItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_view_pager_item, parent, false)

        );
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {


        holder.setImage(slideItems.get(position));
        if(position == slideItems.size()-2){
            viewPager2.post(runnable);
        }

    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }

    class SlideViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView imageView;


        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageSlide);
        }

        void setImage(SlideItem slideItem){

            //if you want to display image from internet you can put here using

            imageView.setImageResource(slideItem.getImage());
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            slideItems.addAll(slideItems);
            notifyDataSetChanged();

        }
    };

}
