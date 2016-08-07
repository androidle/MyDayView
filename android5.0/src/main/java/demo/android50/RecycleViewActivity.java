package demo.android50;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import demo.android50.model.Cheeses;


public class RecycleViewActivity extends AppCompatActivity {

    @InjectView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.inject(this);

        MyAdapter adapter = new MyAdapter();
        //rv.setLayoutManager(new LinearLayoutManager(getApplication()));//线性布局-->ListView
        rv.setLayoutManager(new GridLayoutManager(getApplication(),3));
        rv.setAdapter(adapter);

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{


        //创建每个条目
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(getApplication(), R.layout.item, null);
            return new MyHolder(view);
        }


        //给每个条目绑定数据
        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.tv.setText(Cheeses.NAMES[position]);
        }

        @Override
        public int getItemCount() {
            return Cheeses.NAMES.length;
        }

        //缓存每个条目的子View
        class MyHolder extends RecyclerView.ViewHolder{
            TextView tv;
            public MyHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.myTv);
            }
        }
    }


}
