package com.to3g.snipasteandroid.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzf.easyfloat.EasyFloat;
import com.lzf.easyfloat.enums.ShowPattern;
import com.qmuiteam.qmui.arch.annotation.LatestVisitRecord;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.to3g.snipasteandroid.Listener.DoubleClickListener;
import com.to3g.snipasteandroid.R;
import com.to3g.snipasteandroid.base.BaseFragment;
import com.to3g.snipasteandroid.lib.ClipBoardUtil;
import com.to3g.snipasteandroid.lib.Group;
import com.to3g.snipasteandroid.lib.annotation.Widget;

import butterknife.BindView;
import butterknife.ButterKnife;

@LatestVisitRecord
@Widget(group = Group.Other, name = "Home")
public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.home_title1)
    TextView titleView1;

    @BindView(R.id.editText)
    EditText editText;

    @BindView(R.id.pasteTextButton)
    QMUIRoundButton pasteTextButton;

    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.home_layout, null);
        ButterKnife.bind(this, root);
        initTopBar();
        pasteTextButton.setOnClickListener(v -> {
            String content = ClipBoardUtil.get(getContext());
            Log.d(TAG, "剪切板内容: " + content);
            editText.setText(content);

//            LayoutInflater inflater = LayoutInflater.from(getActivity());
//            LinearLayout mFloatLayout = (LinearLayout) inflater.inflate(R.layout.text_paste, null);
//
//            TextView textView = mFloatLayout.findViewById(R.id.textView);
//            textView.setText(content);

            EasyFloat
                    .with(getActivity())
                    .setLayout(R.layout.text_paste)
                    .setShowPattern(ShowPattern.ALL_TIME)
                    .setLocation(100, 200)
                    .setTag(content)
                    .show();

            TextView textView1 = EasyFloat.getAppFloatView(content).findViewById(R.id.textView);
            EasyFloat.getAppFloatView(content).findViewById(R.id.textView).setOnClickListener(new DoubleClickListener() {
                @Override
                public void onDoubleClick(View v) {
                    EasyFloat.dismissAppFloat(content);
                }
            });
            textView1.setText(content);

        });
        return root;
    }

    private void initTopBar() {
        mTopBar.setTitle(getString(R.string.app_name));
        mTopBar.addRightImageButton(R.mipmap.icon_topbar_overflow, R.id.topbar_right_change_button)
                .setOnClickListener(v -> Toast.makeText(getContext(), "点击干嘛", Toast.LENGTH_SHORT).show());
    }

    @Override
    public Object onLastFragmentFinish() {
        return null;
    }

    @Override
    protected boolean canDragBack(Context context, int dragDirection, int moveEdge) {
        return false;
    }
}