package com.dy.vam.ui.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blog.www.guideview.Component;
import com.dy.vam.R;

/**
 * Created by binIoter on 16/6/17.
 */
public class IeBarComponent implements Component {
  private Context context;
  public IeBarComponent(Context context) {
    this.context=context;
  }

  @Override
  public View getView(LayoutInflater inflater) {

    View ll = (RelativeLayout) inflater.inflate(R.layout.layer_tips_iebar, null);
    Typeface typeFace1 = Typeface.createFromAsset(context.getAssets(), "hanzipensc.ttf");
      TextView tvWhite = ll.findViewById(R.id.tv_tips_white);
      TextView tvWhite1 = ll.findViewById(R.id.tv_tips_white_1);
      TextView tvGreen = ll.findViewById(R.id.tv_tips_green);
      tvWhite.setTypeface(typeFace1); 
      tvWhite1.setTypeface(typeFace1);
      tvGreen.setTypeface(typeFace1);
      ll.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
//        Toast.makeText(view.getContext(), "引导层被点击了", Toast.LENGTH_SHORT).show();
      }
    });
    return ll;
  }

  @Override
  public int getAnchor() {
    return Component.ANCHOR_BOTTOM;
  }

  @Override
  public int getFitPosition() {
    return Component.FIT_CENTER;
  }

  @Override
  public int getXOffset() {
    return 0;
  }

  @Override
  public int getYOffset() {
    return 10;
  }
}
