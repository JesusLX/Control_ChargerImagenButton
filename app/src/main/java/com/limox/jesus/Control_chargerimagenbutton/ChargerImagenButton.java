package com.limox.jesus.Control_chargerimagenbutton;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


/**
 * This witched is a charger of photos
 * Created by jesus on 5/02/17.
 */

public class ChargerImagenButton extends RelativeLayout {

    private Button btnCharger;
    private ImageView ivImage;
    private String mUrlImagen;
    private OnClickListener mButtonClickListener;
    private OnClickListener mImageClickListener;

    public ChargerImagenButton(Context context) {
        super(context);
    }



    public ChargerImagenButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        ((Activity)context).getLayoutInflater().inflate(R.layout.chargerimagenbutton,this,true);

        btnCharger = (Button) findViewById(R.id.button1);
        ivImage = (ImageView) findViewById(R.id.imageview1);
        init();

        if (attrs != null){
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.ChargerImagenButton);
            btnCharger.setText(typedArray.getString(R.styleable.ChargerImagenButton_text));
            mUrlImagen = typedArray.getString(R.styleable.ChargerImagenButton_url_imagen);
            typedArray.recycle();
        }
    }

    public void setButtonOnClickListener(OnClickListener listener){
        this.mButtonClickListener = listener;
    }
    public void setImageOnClickListener(OnClickListener listener){
        this.mImageClickListener = listener;
    }

    private void init(){
        btnCharger.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCharger.setVisibility(GONE);

                Picasso.with(getContext()).load(mUrlImagen).placeholder(R.drawable.progress_animation).into(ivImage, new Callback() {
                    @Override
                    public void onSuccess() {

                        ivImage.setVisibility(VISIBLE);
                    }

                    @Override
                    public void onError() {
                        btnCharger.setText(R.string.chargerror);
                        btnCharger.setVisibility(VISIBLE);
                        ivImage.setVisibility(GONE);
                    }
                });
                /*Picasso.with(getContext()).load(mUrlImagen).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        btnCharger.setVisibility(GONE);
                        ivImage.setBackground(new BitmapDrawable(getContext().getResources(),bitmap));
                        ivImage.setVisibility(VISIBLE);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        btnCharger.setText(R.string.chargerror);
                        btnCharger.setVisibility(VISIBLE);
                        ivImage.setVisibility(GONE);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        btnCharger.setText(R.string.loading);
                    }
                });*/
                if (mButtonClickListener != null)
                    mButtonClickListener.onClick(btnCharger);
            }
        });

        ivImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ivImage.setVisibility(GONE);
                btnCharger.setVisibility(VISIBLE);

                if (mImageClickListener != null)
                    mImageClickListener.onClick(ivImage);
            }
        });
    }



}
