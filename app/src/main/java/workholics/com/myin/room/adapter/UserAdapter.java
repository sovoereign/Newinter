package workholics.com.myin.room.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import workholics.com.myin.R;
import workholics.com.myin.room.entity.User;

/**
 * Created by fdc10 on 3/23/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.BwViewHolder> {

    private List<User> list;
    private Context mContext;

    public UserAdapter(Context context, List<User> list) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public BwViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem_user, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
        ));
        BwViewHolder mh = new BwViewHolder(v, mContext);

        return mh;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(BwViewHolder holder, final int i) {


        final User model = list.get(i);


        holder.tv_name.setText(list.get(i).name);


    }


    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }


    class BwViewHolder extends RecyclerView.ViewHolder {



        TextView tv_name;



        public BwViewHolder(View itemView, final Context mContextv) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);


        }

    }


}

