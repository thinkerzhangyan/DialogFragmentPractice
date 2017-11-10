package tk.thinkerzhangyan.dialogfragmentpractice;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


/**
 * @author zhangyan
 * @des 展示主播信息的Dialog
 * @date 2017/10/23
 */
public class AnchorFragmentDialog extends DialogFragment {
    /**
     * 主播的头像
     */
    private CircleImageView mIvAvatar;

    /**
     * 主播的名字
     */
    private TextView mTvName;

    /**
     * 主播的年龄
     */
    private TextView mTvAge;

    /**
     * 主播的位置
     */
    private TextView mTvLocation;

    /**
     * 主播的粉丝数量
     */
    private TextView mTvFansCount;

    /**
     * 主播的公告
     */
    private TextView mTvNotice;

    /**
     * 确认和取消按钮
     */
    private Button mBtSubscribe, mBtHomePage;


    /**
     * 都是内容数据
     */
    private String imgPath;
    private String name;
    private String age;
    private String location;
    private String fansCount;
    private String notice;

    private Dialog dialog;

    /**
     * Dialogde宽度和高度
     */
    private int dialogWidth;
    private int dialogHeight;


    public static final AnchorFragmentDialog newInstance(String imgPath, String name, String age, String location, String fansCount, String notice, OnClickBottomListener onClickBottomListener) {

        AnchorFragmentDialog fragment = new AnchorFragmentDialog();

        Bundle bundle = new Bundle();
        bundle.putString("imgPath", imgPath);
        bundle.putString("name", name);
        bundle.putString("age", age);
        bundle.putString("location", location);
        bundle.putString("fansCount", fansCount);
        bundle.putString("notice", notice);

        fragment.setOnClickBottomListener(onClickBottomListener);
        fragment.setArguments(bundle);

        return fragment;
    }

    /**
     * 设置订阅和主页按钮的回调
     */
    private OnClickBottomListener onClickBottomListener;

    public AnchorFragmentDialog setOnClickBottomListener(OnClickBottomListener onClickBottomListener) {
        this.onClickBottomListener = onClickBottomListener;
        return this;
    }

    public interface OnClickBottomListener {
        /**
         * 点击订阅按钮事件
         */
        void onSubscribeClick();

        /**
         * 点击主页按钮事件
         */
        void onHomePageClick();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_anchor_dialog, null, false);

        //获取Dimen中设置的宽和高，在Resume方中中使用
        dialogWidth = getResources().getDimensionPixelOffset(R.dimen.letv_dimens_anchor_dialog_width);
        dialogHeight = getResources().getDimensionPixelOffset(R.dimen.letv_dimens_anchor_dialog_height);

        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        initDialogStyle(rootView);
        getViews(rootView);
        initData();
        initEvent();

        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        //DialogFragment通过xmL是无法设置大小的，只能通过下面这种方式
        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
        refreshView();

    }

    /**
     * 设置Dialog的样式
     */
    private void initDialogStyle(View view) {
        dialog = new Dialog(getActivity(), R.style.CustomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER; // 紧贴底部
        window.setAttributes(lp);
    }

    /**
     * 获取相关控件
     */
    private void getViews(View rootView) {

        mIvAvatar = rootView.findViewById(R.id.iv_avatar);
        mTvName = rootView.findViewById(R.id.tv_name);
        mTvAge = rootView.findViewById(R.id.tv_age);
        mTvLocation = rootView.findViewById(R.id.tv_location);
        mTvFansCount = rootView.findViewById(R.id.tv_fans_count);
        mTvNotice = rootView.findViewById(R.id.tv_host_notice);
        mBtSubscribe = rootView.findViewById(R.id.bt_subscribe);
        mBtHomePage = rootView.findViewById(R.id.bt_homepage);

    }

    /**
     * 初始化数据
     */
    private void initData() {

        Bundle args = getArguments();
        if (args == null)
            return;

        imgPath = args.getString("imgPath");
        name = args.getString("name");
        age = args.getString("age");
        location = args.getString("location");
        fansCount = args.getString("fansCount");
        notice = args.getString("notice");

    }

    /**
     * 初始化界面的订阅和主页监听器
     */
    private void initEvent() {
        //设置订阅按钮被点击后，向外界提供监听
        mBtSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onClickBottomListener != null) {
                    onClickBottomListener.onSubscribeClick();
                }
            }
        });
        //设置主页按钮被点击后，向外界提供监听
        mBtHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onClickBottomListener != null) {
                    onClickBottomListener.onHomePageClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void refreshView() {

        if (!TextUtils.isEmpty(imgPath)) {
            mIvAvatar.setImageURI(Uri.parse(imgPath));
        }

        if (!TextUtils.isEmpty(name)) {
            mTvName.setText(name);
        }

        if (!TextUtils.isEmpty(age)) {
            mTvAge.setText(age);
        }

        if (!TextUtils.isEmpty(location)) {
            mTvLocation.setText(location);
        }

        if (!TextUtils.isEmpty(fansCount)) {
            mTvFansCount.setText(fansCount);
        }

        if (!TextUtils.isEmpty(notice)) {
            mTvNotice.setText(notice);
        }

    }
    
}
