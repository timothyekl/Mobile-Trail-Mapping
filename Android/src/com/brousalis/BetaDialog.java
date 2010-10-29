package com.brousalis;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;

/**
 * BetaDialog is a custom extension of the Dialog class that
 * will allow us to show any popups related to the android
 * beta program.
 * @author Eric Stokes 10/28/2010
 *
 */
public class BetaDialog extends Dialog {
	
	private OnCancelListener _cancelListener;
	
	public BetaDialog(Context context, int xmlToLoad) {
		super(context);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(xmlToLoad);
		this.setCancelable(true);
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		this.cancel();
	}
	
	@Override
	public boolean onSearchRequested() {
		// TODO Auto-generated method stub
		return false;
		//return super.onSearchRequested();
	}
}
