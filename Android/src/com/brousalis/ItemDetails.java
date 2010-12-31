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
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ItemDetails extends Activity {
	
	private Bundle _extras;
	private final String IMAGEPATH = "/data/data/com.brousalis/files/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.item_details);
		_extras = this.getIntent().getExtras();
		_extras.get("title");
		_extras.get("summary");
		TextView title = (TextView)this.findViewById(R.id.detail_title);
		TextView summary = (TextView)this.findViewById(R.id.detail_summary);
		//TextView condition = (TextView)this.findViewById(R.id.detail_condition);
		
		title.setText(_extras.get("title").toString());
		summary.setText(_extras.get("summary").toString());
		
		//TODO: Conditions aren't implemented for a trail scale in the XML yet.  Do that, then this
		// Line gets uncommented.
		//condition.setText(_extras.get("title").toString());
		
		// Still a few glitches in the download code, also we don't have images server side
		// function is still valid.
		//DownloadFromUrl("forrest.png", "forrest.png");
	}
	
	
	private void DownloadFromUrl(String imageURL, String fileName) {  //this is the downloader method
        try {
                URL url = new URL("http://www.fernferret.com/mtm/images/forrest.png"); //you can write here any link
                File file = new File(fileName);

                long startTime = System.currentTimeMillis();
                Log.d("ImageManager", "download begining");
                Log.d("ImageManager", "download url:" + url);
                Log.d("ImageManager", "downloaded file name:" + fileName);
                /* Open a connection to that URL. */
                URLConnection ucon = url.openConnection();

                /*
                 * Define InputStreams to read from the URLConnection.
                 */
                InputStream is = ucon.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                /*
                 * Read bytes to the Buffer until there is nothing more to read(-1).
                 */
                ByteArrayBuffer baf = new ByteArrayBuffer(50);
                int current = 0;
                while ((current = bis.read()) != -1) {
                        baf.append((byte) current);
                }

                /* Convert the Bytes read to a String. */
                FileOutputStream fos = new FileOutputStream(IMAGEPATH+file);
                fos.write(baf.toByteArray());
                fos.close();
                Log.d("ImageManager", "download ready in"
                                + ((System.currentTimeMillis() - startTime) / 1000)
                                + " sec");

        } catch (IOException e) {
                Log.d("ImageManager", "Error: " + e);
        }

	}
}
