package adapter;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The Scaler2 class will create Drawables and also save them in the memory for
 * future use The class will cater the FirstTime use of the application by
 * itself and keep that in the the saved preference xml
 * 
 * @author HuzaifaH
 * 
 */
@SuppressLint("NewApi")
public class ViewScaler {

	private static final String DT_PREFRENCES = "DTechPrefrences",
			KEY = "imagescopy";
	private Activity mActivity;
	public static int sScreenWidth = 0, sScreenHeight = 0;
	public static float sScreenWidth_DP = 0, sScreenHeight_DP = 0;
	private int mDesignedHeight = 1280, mDesignedWidth = 720;
	private String path;
	private boolean mFirsTime = false, mKeyStored = false;
	// private int mInSampleSize = 4;
	public static float sWidthScaleFactor = 0, sHeightScaleFactor = 0;
	public static final short SMALLER_ONLY = 1, LARGER_ONLY = 2;

	/**
	 * Constructor will initialize activity and mActivity of the Application, it
	 * will also be required to set the default size of the screen on which the
	 * screen was designed. E.g. it should be the size of the back ground image
	 * or the splash image.
	 * 
	 * @param activity
	 * @param mActivity
	 * @param designedHeight
	 * @param designedWidth
	 */
	public ViewScaler(Activity activity) {
		this.mActivity = activity;
		if (sScreenWidth == 0) {
			DisplayMetrics displaymetrics = new DisplayMetrics();
			activity.getWindowManager().getDefaultDisplay()
					.getMetrics(displaymetrics);
			sScreenWidth = displaymetrics.widthPixels;
			sScreenHeight = displaymetrics.heightPixels;
			/*
			 * check if the Designed Mode is equivalent to the Current Received
			 * Screen Values if Not then Switch the Values
			 */
			Log.e("(w,h)", "("+String.valueOf(sScreenWidth)+","+String.valueOf(sScreenHeight)+")");
			if ((mDesignedWidth > mDesignedHeight && sScreenWidth < sScreenHeight)
					|| (mDesignedWidth < mDesignedHeight && sScreenWidth > sScreenHeight)) {
				/**
				 * then System Provided Incorrect Values height should be width
				 * and width should must be the Height
				 */
				sScreenWidth = displaymetrics.heightPixels;
				sScreenHeight = displaymetrics.widthPixels;

			}
			Log.e("(w,h)", "("+String.valueOf(sScreenWidth)+","+String.valueOf(sScreenHeight)+")");

			sWidthScaleFactor = (float) sScreenWidth / (float) mDesignedWidth;
			sHeightScaleFactor = (float) sScreenHeight
					/ (float) mDesignedHeight;
			sScreenHeight_DP = sScreenHeight / displaymetrics.density;
			sScreenWidth_DP = sScreenWidth / displaymetrics.density;

		}
		SharedPreferences myPrefs = mActivity.getSharedPreferences(
				DT_PREFRENCES, 0);
		if (myPrefs.contains(KEY)) {
			path = myPrefs.getString(KEY, "");
			mFirsTime = false;
			mKeyStored = true;

		} else
			mFirsTime = true;
	}

	/**
	 * makes a new drawable for the Drawable Resource
	 * 
	 * @param drawableResourceID
	 * @return
	 */
	public Drawable getDrawableFromMemory(int drawableResourceID) {
		try {

			if (!mFirsTime) {
				Bitmap b = loadImageFromStorage(path, drawableResourceID);
				if (b == null)
					return null;
				Drawable d = new BitmapDrawable(mActivity.getResources(), b);
				return d;
			} else {
				Bitmap originalBitmap = BitmapFactory.decodeResource(
						mActivity.getResources(), drawableResourceID);
				// calculate new height and width of the drawable image to make
				int h = (int) ((float) originalBitmap.getHeight() * sHeightScaleFactor);
				int w = (int) ((float) originalBitmap.getWidth() * sWidthScaleFactor);
				// create a new scaled bitmap from the original Bitmap
				Bitmap bitmap = Bitmap.createScaledBitmap(originalBitmap, w, h,
						false);
				// convert the Bitmap into a new Drawable
				Drawable d = new BitmapDrawable(mActivity.getResources(),
						bitmap);
				// save the Bitmap in the internal storage
				path = saveToInternalSorage(bitmap, drawableResourceID);
				// check if the path was stored in the preference
				if (!mKeyStored) {
					storeFilePathinThePref();
				}
				return d;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * takes the resourceId of the drawable and the resource of the imageView
	 * 
	 * @param drawableResourceID
	 * @param imageViewResourceID
	 */
	public void scaleImageFromMemory(int drawableResourceID,
			int imageViewResourceID) {
		ImageView img = (ImageView) mActivity.findViewById(imageViewResourceID);
		img.setImageDrawable(getDrawableFromMemory(drawableResourceID));
	}

	/**
	 * takes the resourceId of the Drawable and the resource of the TextView
	 * 
	 * @param drawableResourceID
	 * @param imageViewResourceID
	 */
	@SuppressWarnings("deprecation")
	public void scaleTextViewBackground(int drawableResourceID,
			int textViewResourceID) {
		TextView tv = (TextView) mActivity.findViewById(textViewResourceID);
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			tv.setBackgroundDrawable(getDrawable(drawableResourceID));
		} else {
			tv.setBackground(getDrawable(drawableResourceID));
		}
	}

	/**
	 * takes the resourceId of the drawable and the resource of the Button
	 * 
	 * @param drawableResourceID
	 * @param imageViewResourceID
	 */
	@SuppressWarnings("deprecation")
	public void scaleButtonBackground(int drawableResourceID,
			int buttonResourceID) {
		Button btn = (Button) mActivity.findViewById(buttonResourceID);
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
			btn.setBackgroundDrawable(getDrawable(drawableResourceID));
		else
			btn.setBackground(getDrawable(drawableResourceID));
	}

	/**
	 * takes the resourceId of the drawable and the resource of the Button with
	 * a single factorial for both height and width
	 * 
	 * @param drawableResourceID
	 * @param buttonResourceID
	 * @param SmallerFactorialORLarger
	 */
	@SuppressWarnings("deprecation")
	public void scaleButtonBackground(int drawableResourceID,
			int buttonResourceID, short SmallerFactorialORLarger) {
		Button btn = (Button) mActivity.findViewById(buttonResourceID);
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
			btn.setBackgroundDrawable(getDrawable(drawableResourceID,
					SmallerFactorialORLarger));
		else
			btn.setBackground(getDrawable(drawableResourceID,
					SmallerFactorialORLarger));
	}

	/**
	 * takes the resourceId of the drawable and the resource of the Button
	 * 
	 * @param drawableResourceID
	 * @param imageViewResourceID
	 */
	@SuppressWarnings("deprecation")
	public void scaleViewBackground(int drawableResourceID, int buttonResourceID) {
		View btn = (View) mActivity.findViewById(buttonResourceID);
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
			btn.setBackgroundDrawable(getDrawable(drawableResourceID));
		else
			btn.setBackground(getDrawable(drawableResourceID));
	}

	/**
	 * takes the resourceId of the drawable and the resource of the Button with
	 * a single factorial for both height and width
	 * 
	 * @param drawableResourceID
	 * @param buttonResourceID
	 * @param SmallerFactorialORLarger
	 */
	@SuppressWarnings("deprecation")
	public void scaleViewBackground(int drawableResourceID,
			int buttonResourceID, short SmallerFactorialORLarger) {
		View btn = (View) mActivity.findViewById(buttonResourceID);
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
			btn.setBackgroundDrawable(getDrawable(drawableResourceID,
					SmallerFactorialORLarger));
		else
			btn.setBackground(getDrawable(drawableResourceID,
					SmallerFactorialORLarger));
	}

	/**
	 * 
	 * @param dialog
	 * @param drawableResourceID
	 * @param buttonResourceID
	 */
	@SuppressWarnings("deprecation")
	public void scaleViewBackground(Dialog dialog, int drawableResourceID,
			int buttonResourceID) {
		View btn = (View) dialog.findViewById(buttonResourceID);
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
			btn.setBackgroundDrawable(getDrawable(drawableResourceID));
		else
			btn.setBackground(getDrawable(drawableResourceID));
	}

	/**
	 * takes the resourceId of the drawable and the resource of the Button with
	 * a single factorial for both height and width
	 * 
	 * @param drawableResourceID
	 * @param buttonResourceID
	 * @param SmallerFactorialORLarger
	 */
	@SuppressWarnings("deprecation")
	public void scaleViewBackground(Dialog dialog, int drawableResourceID,
			int buttonResourceID, short SmallerFactorialORLarger) {
		View btn = (View) dialog.findViewById(buttonResourceID);
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
			btn.setBackgroundDrawable(getDrawable(drawableResourceID,
					SmallerFactorialORLarger));
		else
			btn.setBackground(getDrawable(drawableResourceID,
					SmallerFactorialORLarger));
	}

	/**
	 * takes the resourceId of the drawable and the resource of the Button
	 * 
	 * @param drawableResourceID
	 * @param buttonView
	 */
	@SuppressWarnings("deprecation")
	public void scaleViewBackground(int drawableResourceID, View buttonView) {
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
			buttonView.setBackgroundDrawable(getDrawable(drawableResourceID));
		else
			buttonView.setBackground(getDrawable(drawableResourceID));
	}

	/**
	 * takes the resourceId of the drawable and the resource of the Button with
	 * a single factorial for both height and width
	 * 
	 * @param drawableResourceID
	 * @param buttonView
	 * @param SmallerFactorialORLarger
	 */
	@SuppressWarnings("deprecation")
	public void scaleViewBackground(int drawableResourceID, View buttonView,
			short SmallerFactorialORLarger) {
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
			buttonView.setBackgroundDrawable(getDrawable(drawableResourceID,
					SmallerFactorialORLarger));
		else
			buttonView.setBackground(getDrawable(drawableResourceID,
					SmallerFactorialORLarger));
	}

	/**
	 * 
	 * @param dialog
	 * @param drawableResourceID
	 * @param buttonView
	 */
	@SuppressWarnings("deprecation")
	public void scaleViewBackground(Dialog dialog, int drawableResourceID,
			View buttonView) {
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
			buttonView.setBackgroundDrawable(getDrawable(drawableResourceID));
		else
			buttonView.setBackground(getDrawable(drawableResourceID));
	}

	/**
	 * takes the resourceId of the drawable and the resource of the Button with
	 * a single factorial for both height and width
	 * 
	 * @param drawableResourceID
	 * @param buttonView
	 * @param SmallerFactorialORLarger
	 */
	@SuppressWarnings("deprecation")
	public void scaleViewBackground(Dialog dialog, int drawableResourceID,
			View buttonView, short SmallerFactorialORLarger) {

		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
			buttonView.setBackgroundDrawable(getDrawable(drawableResourceID,
					SmallerFactorialORLarger));
		else
			buttonView.setBackground(getDrawable(drawableResourceID,
					SmallerFactorialORLarger));
	}

	/**
	 * makes a new drawable for the Drawable Resource
	 * 
	 * @param drawableResourceID
	 * @return
	 */
	public Drawable getDrawable(int drawableResourceID) {
		try {
			BitmapDrawable bd = (BitmapDrawable) mActivity.getResources()
					.getDrawable(drawableResourceID);
			int h = (int) ((float) bd.getBitmap().getHeight() * sHeightScaleFactor);
			int w = (int) ((float) bd.getBitmap().getWidth() * sWidthScaleFactor);
			// create a new scaled bitmap from the original Bitmap
			Bitmap bitmap = Bitmap.createScaledBitmap(bd.getBitmap(), w, h,
					true);
			// convert the Bitmap into a new Drawable
			Drawable d = new BitmapDrawable(mActivity.getResources(), bitmap);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * makes a new drawable for the Drawable Resource
	 * 
	 * @param drawableResourceID
	 * @return
	 */
	/**
	 * @param drawableResourceID
	 * @param SmallerFactorialORLarger
	 * @return
	 */
	public Drawable getDrawable(int drawableResourceID,
			short SmallerFactorialORLarger) {
		try {

			// calculate new height and width of the drawable image to make
			float factorial = 0, greater = 0, smaller = 0;
			if (sHeightScaleFactor > sWidthScaleFactor) {
				greater = sHeightScaleFactor;
				smaller = sWidthScaleFactor;
			} else {
				smaller = sHeightScaleFactor;
				greater = sWidthScaleFactor;
			}

			if (SmallerFactorialORLarger == SMALLER_ONLY)
				factorial = smaller;
			else
				factorial = greater;
			BitmapDrawable bd = (BitmapDrawable) mActivity.getResources()
					.getDrawable(drawableResourceID);

			int h = (int) ((float) bd.getBitmap().getHeight() * factorial);
			int w = (int) ((float) bd.getBitmap().getWidth() * factorial);

			// create a new scaled bitmap from the original Bitmap
			Bitmap bitmap = Bitmap.createScaledBitmap(bd.getBitmap(), w, h,
					true);
			// convert the Bitmap into a new Drawable
			Drawable d = new BitmapDrawable(mActivity.getResources(), bitmap);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	/**
	 * 
	 * @param rawWidth
	 * @param rawHeight
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(int rawWidth, int rawHeight,
			int reqWidth, int reqHeight) {
		// Raw height and width of image

		int inSampleSize = 1;

		if (rawHeight > reqHeight || rawWidth > reqWidth) {

			final int halfHeight = rawHeight / 2;
			final int halfWidth = rawWidth / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	/**
	 * takes the resourceId of the drawable and the resource of the imageView
	 * 
	 * @param drawableResourceID
	 * @param imageViewResourceID
	 */
	public void scaleImage(int drawableResourceID, int imageViewResourceID) {
		ImageView img = (ImageView) mActivity.findViewById(imageViewResourceID);
		img.setImageDrawable(getDrawable(drawableResourceID));
	}

	/**
	 * takes the resourceId of the drawable and ImageView object
	 * 
	 * @param drawableResourceID
	 * @param im
	 */
	public void scaleImage(int drawableResourceID, ImageView im) {
		im.setImageDrawable(getDrawable(drawableResourceID));
	}

	/**
	 * takes the resourceId of the drawable and ImageView object
	 * 
	 * @param drawableResourceID
	 * @param im
	 */
	public void scaleImage(int drawableResourceID, ImageView im,
			short SmallerFactorialORLarger) {
		im.setImageDrawable(getDrawable(drawableResourceID,
				SmallerFactorialORLarger));
	}

	/**
	 * called from Dialog to scale Image and Assign value to the Image View
	 * 
	 * @param dialog
	 * @param drawableResourceID
	 * @param imageViewResourceID
	 */
	public void scaleImage(Dialog dialog, int drawableResourceID,
			int imageViewResourceID) {
		ImageView img = (ImageView) dialog.findViewById(imageViewResourceID);
		img.setImageDrawable(getDrawable(drawableResourceID));
	}

	/**
	 * takes the resourceId of the drawable and the resource of the imageView
	 * and if chooses to use a single Factorial
	 * 
	 * @param drawableResourceID
	 * @param imageViewResourceID
	 * @param SmallerFactorialORLarger
	 */

	public void scaleImage(int drawableResourceID, int imageViewResourceID,
			short SmallerFactorialORLarger) {
		ImageView img = (ImageView) mActivity.findViewById(imageViewResourceID);
		img.setImageDrawable(getDrawable(drawableResourceID,
				SmallerFactorialORLarger));
	}

	/**
	 * Called from Dialog only
	 * 
	 * @param dialog
	 * @param drawableResourceID
	 * @param imageViewResourceID
	 * @param SmallerFactorialORLarger
	 */
	public void scaleImage(Dialog dialog, int drawableResourceID,
			int imageViewResourceID, short SmallerFactorialORLarger) {
		ImageView img = (ImageView) dialog.findViewById(imageViewResourceID);
		img.setImageDrawable(getDrawable(drawableResourceID,
				SmallerFactorialORLarger));
	}

	/**
	 * returns the pixels of the percentage of Screen Height
	 * 
	 * @param percentage
	 * @return
	 */
	public int getPixelsFromPercentageOfHeight(float percentage) {
		return (int) ((((float) sScreenHeight) * percentage) / 100f);

	}

	/**
	 * returns the pixels of the percentage of Screen Height
	 * 
	 * @param percentage
	 * @return
	 */
	public int getPixelsFromPercentageOfWidth(float percentage) {
		return (int) ((((float) sScreenWidth) * percentage) / 100f);
	}

	/**
	 * returns the pixels of the percentage of Screen Height
	 * 
	 * @param percentage
	 * @return
	 */
	public float getDpFromPercentageOfHeight(float percentage) {
		return (sScreenHeight_DP * percentage) / 100f;

	}

	/**
	 * returns the pixels of the percentage of Screen Height
	 * 
	 * @param percentage
	 * @return
	 */
	public float getDpFromPercentageOfWidth(float percentage) {
		return (sScreenWidth_DP * percentage) / 100f;
	}

	// //////////////////////////////////////////////////////////////////

	// //////////PRIVATE METHODS

	// //////////////////////////////////////////////////////////////////

	/**
	 * will take the Bitmap Image and the Resource id of that image. It will
	 * store that image in the Internal Storage. Returns the complete path of
	 * the storage.
	 * 
	 * @param bitmapImageBitmap
	 * @param id
	 * @return
	 */
	private String saveToInternalSorage(Bitmap bitmapImage, int id) {
		ContextWrapper cw = new ContextWrapper(mActivity);
		// path to /data/data/yourapp/app_data/imageDir
		@SuppressWarnings("static-access")
		File directory = cw.getDir("imageDir", mActivity.MODE_PRIVATE);
		// Create imageDir
		File mypath = new File(directory, "image" + id);
		FileOutputStream fos = null;
		try {

			fos = new FileOutputStream(mypath);

			// Use the compress method on the BitMap object to write image to
			// the OutputStream
			bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return directory.getAbsolutePath();
	}

	/**
	 * will take path of the internal storage Directory and the id of the image.
	 * Returns the Bitmap image from the internal storage.
	 * 
	 * @param path
	 * @param id
	 * @return
	 */
	private Bitmap loadImageFromStorage(String path, int id) {

		try {
			File f = new File(path, "image" + id);
			// Decode the input stream to create the Bitmap
			Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
			return b;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Store the internal Storage Path in the Preference
	 */
	private void storeFilePathinThePref() {
		SharedPreferences myPrefs = mActivity.getSharedPreferences(
				DT_PREFRENCES, 0);
		SharedPreferences.Editor prefsEditor = myPrefs.edit();
		prefsEditor.putString(KEY, path);
		prefsEditor.commit();
		mKeyStored = true;
	}

	/**
	 * 
	 * @return
	 */
	public Activity getmActivity() {
		return mActivity;
	}

	/**
	 * 
	 * @param mActivity
	 */
	public void setmActivity(Activity mActivity) {
		this.mActivity = mActivity;
	}
	/**
	 * 
	 * @return
	 */
	public int getsScreenWidth() {
		return sScreenWidth;
	}
	/**
	 * 
	 * @param sScreenWidth
	 */
	public static void setsScreenWidth(int sScreenWidth) {
		ViewScaler.sScreenWidth = sScreenWidth;
	}
	/**
	 * 
	 * @return
	 */
	public int getsScreenHeight() {
		return sScreenHeight;
	}
	/**
	 * 
	 * @param sScreenHeight
	 */
	public static void setsScreenHeight(int sScreenHeight) {
		ViewScaler.sScreenHeight = sScreenHeight;
	}
	/**
	 * 
	 * @return
	 */
	public static float getsScreenWidth_DP() {
		return sScreenWidth_DP;
	}
	/**
	 * 
	 * @param sScreenWidth_DP
	 */
	public static void setsScreenWidth_DP(float sScreenWidth_DP) {
		ViewScaler.sScreenWidth_DP = sScreenWidth_DP;
	}
	/**
	 * 
	 * @return
	 */
	public static float getsScreenHeight_DP() {
		return sScreenHeight_DP;
	}
	/**
	 * 
	 * @param sScreenHeight_DP
	 */
	public static void setsScreenHeight_DP(float sScreenHeight_DP) {
		ViewScaler.sScreenHeight_DP = sScreenHeight_DP;
	}
	/**
	 * 
	 * @return
	 */
	public int getmDesignedHeight() {
		return mDesignedHeight;
	}
	/**
	 * 
	 * @param mDesignedHeight
	 */
	public void setmDesignedHeight(int mDesignedHeight) {
		this.mDesignedHeight = mDesignedHeight;
	}
	/**
	 * 
	 * @return
	 */
	public int getmDesignedWidth() {
		return mDesignedWidth;
	}
	/**
	 * 
	 * @param mDesignedWidth
	 */
	public void setmDesignedWidth(int mDesignedWidth) {
		this.mDesignedWidth = mDesignedWidth;
	}
	/**
	 * 
	 * @return
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 
	 * @return
	 */
	public boolean ismFirsTime() {
		return mFirsTime;
	}
	/**
	 * 
	 * @param mFirsTime
	 */
	public void setmFirsTime(boolean mFirsTime) {
		this.mFirsTime = mFirsTime;
	}
	/**
	 * 
	 * @return
	 */
	public boolean ismKeyStored() {
		return mKeyStored;
	}
	/**
	 * 
	 * @param mKeyStored
	 */
	public void setmKeyStored(boolean mKeyStored) {
		this.mKeyStored = mKeyStored;
	}
	/**
	 * 
	 * @return
	 */
	public static float getsWidthScaleFactor() {
		return sWidthScaleFactor;
	}
	/**
	 * 
	 * @param sWidthScaleFactor
	 */
	public static void setsWidthScaleFactor(float sWidthScaleFactor) {
		ViewScaler.sWidthScaleFactor = sWidthScaleFactor;
	}
	/**
	 * 
	 * @return
	 */
	public static float getsHeightScaleFactor() {
		return sHeightScaleFactor;
	}
	/**
	 * 
	 * @param sHeightScaleFactor
	 */
	public static void setsHeightScaleFactor(float sHeightScaleFactor) {
		ViewScaler.sHeightScaleFactor = sHeightScaleFactor;
	}
	/**
	 * 
	 * @return
	 */
	public static String getDtPrefrences() {
		return DT_PREFRENCES;
	}
	/**
	 * 
	 * @return
	 */
	public static String getKey() {
		return KEY;
	}


}

