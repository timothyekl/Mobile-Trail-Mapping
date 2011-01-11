package com.brousalis;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetails extends Activity {

	private Bundle _extras;
	private static final String DATA_PATH = "/data/data/com.brousalis/files/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.item_details);
		_extras = this.getIntent().getExtras();
		_extras.get("title");
		_extras.get("summary");
		TextView title = (TextView) this.findViewById(R.id.detail_title);
		TextView summary = (TextView) this.findViewById(R.id.detail_summary);
		// TextView condition =
		// (TextView)this.findViewById(R.id.detail_condition);

		title.setText(_extras.get("title").toString());
		summary.setText(_extras.get("summary").toString());

		// TODO: Conditions aren't implemented for a trail scale in the XML yet.
		// Do that, then this
		// Line gets uncommented.
		// condition.setText(_extras.get("title").toString());

		String fromImage = "http://www.fernferret.com/mtm/images/forrest.png";
		String toImage = "forrest.png";
		
		ImageView image = (ImageView) findViewById(R.id.imview);
		
		File imageFile = new File("/data/data/com.brousalis/files/forrest.png");
		if(!imageFile.exists()) {
			DownloadFromUrl(fromImage, toImage, DATA_PATH);
		}
		Bitmap bMap = BitmapFactory.decodeFile("/data/data/com.brousalis/files/forrest.png");
		image.setImageBitmap(bMap);
		
	}

	/**
	 * Downloads a file from a url and places it in the data directory named the
	 * output filename. This function is intended to work with images.
	 * 
	 * @param imageURL
	 *            The Url of the file to download
	 * @param fileName
	 *            Output name
	 */
	private void DownloadFromUrl(String fileURL, String outputFileName, String dataPath) {
		try {
			URL url = new URL(fileURL);
			File file = new File(outputFileName);

			URLConnection urlConnect = url.openConnection();

			InputStream inputStream = urlConnect.getInputStream();
			BufferedInputStream bInputStream = new BufferedInputStream(inputStream);

			/* Read the buffered input to a ByteArrayBuffer */
			ByteArrayBuffer bArrayBuffer = new ByteArrayBuffer(50);
			int current = 0;
			while ((current = bInputStream.read()) != -1) {
				bArrayBuffer.append((byte) current);
			}

			/* Save the file to disk in our private directory */
			FileOutputStream output = new FileOutputStream(dataPath + file);
			output.write(bArrayBuffer.toByteArray());
			output.close();

		} catch (IOException e) {
			Log.d("ImageManager", "Error: " + e);
		}

	}
}
