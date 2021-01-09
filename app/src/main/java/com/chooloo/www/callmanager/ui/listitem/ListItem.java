package com.chooloo.www.callmanager.ui.listitem;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.chooloo.www.callmanager.R;
import com.chooloo.www.callmanager.databinding.ListItemBinding;

public class ListItem extends LinearLayout {

    private ListItemBinding binding;
    private final Context mContext;

    public ListItem(Context context) {
        super(context);
        mContext = context;
        setUp();
    }

    public ListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setUp();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ListItem, 0, 0);
        setBigText(a.getString(R.styleable.ListItem_bigText));
        setSmallText(a.getString(R.styleable.ListItem_smallText));
        setImageDrawable(a.getDrawable(R.styleable.ListItem_src));
        setHeaderText(a.getString(R.styleable.ListItem_header));
        showHeader(binding.listItemHeaderText.getText() != null);
        a.recycle();
    }

    public void setUp() {
        binding = ListItemBinding.inflate(LayoutInflater.from(mContext), this, true);

        // set clickable
        setClickable(false);
        binding.listItemHeaderLayout.setClickable(false);
        binding.listItemPersonLayout.setClickable(true);
        setFocusable(true);

        // set selectable background resource
        TypedValue outValue = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        setBackgroundResource(outValue.resourceId);

        // set width and height
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setBigText(@Nullable String text) {
        binding.listItemBigText.setText(text == null ? "" : text);
    }

    public void setSmallText(@Nullable String text) {
        binding.listItemSmallText.setText(text == null ? "" : text);
        binding.listItemSmallText.setVisibility(text == null ? GONE : VISIBLE);
    }

    public void setImageDrawable(@Nullable Drawable image) {
        if (image != null) {
            binding.listItemImage.setImageDrawable(image);
        }
    }

    public void setImageUri(@Nullable Uri image) {
        if (image != null) {
            binding.listItemImage.setImageURI(image);
        }
    }

    public void setHeaderText(String headerText) {
        binding.listItemHeaderText.setText(headerText);
    }

    public void showHeader(boolean isShow) {
        binding.listItemHeaderLayout.setVisibility(isShow ? VISIBLE : GONE);
    }
}