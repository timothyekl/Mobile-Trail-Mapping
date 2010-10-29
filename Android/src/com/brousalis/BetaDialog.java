package com.brousalis;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

/**
 * BetaDialog is a custom extension of the Dialog class that
 * will allow us to show any popups related to the android
 * beta program.
 * @author Eric Stokes 10/28/2010
 *
 */
public class BetaDialog extends Dialog {
	public BetaDialog(Context context, int xmlToLoad) {
		super(context);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

}
