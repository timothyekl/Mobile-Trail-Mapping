package com.brousalis;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

public class BetaDialog extends Dialog {
	public BetaDialog(Context context, int xmlToLoad) {
		super(context);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

}
