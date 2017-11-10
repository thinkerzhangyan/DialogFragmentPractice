package tk.thinkerzhangyan.dialogfragmentpractice;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnchorFragmentDialog.newInstance("","大萌妹匝儿嘻嘻","18岁","北京","粉丝：2.8万","主播公告：直播时间：上午10:00下午5:00五个飞机房管，两个十万水军微信群。qq:2233292微博：大萌妹匝儿嘻嘻   谢谢大家关注。",null).show(getSupportFragmentManager(),"dialog");
            }
        });

    }
}
