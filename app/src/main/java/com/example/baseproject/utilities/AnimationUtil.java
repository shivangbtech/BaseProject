package com.example.baseproject.utilities;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ListView;

public class AnimationUtil {

	public void setGridAnimation(GridView gridLayout){
		AnimationSet set = new AnimationSet(true);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(1000);
		set.addAnimation(animation);
		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,Animation.RELATIVE_TO_SELF, 0.0f, 
				    Animation.RELATIVE_TO_SELF,	0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(1000);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
		gridLayout.setLayoutAnimation(controller);
	}

	public void setListAnimation(ListView listView){
		AnimationSet set = new AnimationSet(true);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(500);
		set.addAnimation(animation);
		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				    Animation.RELATIVE_TO_SELF,	-1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(500);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
		listView.setLayoutAnimation(controller);
	}
	
	
	public void setAnimation(Context context, View view, int animation){
		Animation discountColumnAnimation = AnimationUtils.loadAnimation(context, animation);
		view.setAnimation(discountColumnAnimation);

	}
	

}
