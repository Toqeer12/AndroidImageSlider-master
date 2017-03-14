package utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.koherent.pdlapps.cricketworldcup2015live.R;


public class CommonMethods {
    private static ProgressDialog pgDialog;

//    public static void showProgressDialog(Context nContext) {
//        try {
//            pgDialog = new ProgressDialog(nContext);
//            pgDialog.setIndeterminate(true);
//            pgDialog.setCancelable(false);
//            pgDialog.show();
////            pgDialog.setContentView(R.layout.dialog_loading);
//        } catch (Exception e) {
//        }
//    }

//    public static void hideProgressDialog() {
//        try {
//            if (pgDialog != null)
//                pgDialog.dismiss();
//        } catch (Exception e) {
//
//        }
//    }

//    public static Dialog showConfirmationDialog(Context nContext,
//                                                String tittle, String message, String negativeButton,
//                                                String positiveButton) {
//        try {
////			View view = CommonMethods.createView(nContext,
////					R.layout.popup_confirmation, null);
////			TextView tvTittle = (TextView) view.findViewById(R.id.tvTittle);
////			tvTittle.setText(tittle);
////			TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
////			tvMessage.setText(message);
////			Button btnPositive = (Button) view.findViewById(R.id.btnPossitive);
////			btnPossitive.setText(possitiveButton);
////			Button btnNegative = (Button) view.findViewById(R.id.btnNegative);
////			btnNegative.setText(negativeButton);
////			Dialog nDialog = new Dialog(nContext, R.style.NewDialog);
////			nDialog.setContentView(view);
////			nDialog.show();
////			return nDialog;
//        } catch (Exception e) {
//
//        }
//        return null;
//    }
    //

    public static boolean checkForNetworkProvider(
            LocationManager nLocationManager, Context nContext) {
        try {
            if (nLocationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                    || (CommonMethods.isNetworkAvailable(nContext) || CommonMethods
                    .isWifiConnected(nContext))) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    public static void showProgressDialog(Context nContext) {
        try {
            pgDialog = new ProgressDialog(nContext);
            pgDialog.setIndeterminate(true);
            pgDialog.setCancelable(false);
            pgDialog.show();
            pgDialog.setContentView(R.layout.dialoug_loading);
        } catch (Exception e) {
        }
    }

    public static void hideProgressDialog() {
        try {
            if (pgDialog != null)
                pgDialog.dismiss();
        } catch (Exception e) {

        }
    }
//    public static void showToast(String message, int toastDurattion) {
//        try {
//            Toast.makeText(CommonObjects.getContext(), message, toastDurattion)
//                    .show();
//        } catch (Exception e) {
//        }
//    }
//
//    public static String getCurrentDate() {
//        try {
//            Calendar calender = Calendar.getInstance();
//            return getDateFormatted(
//                    String.valueOf(calender.get(Calendar.MONTH) + 1),
//                    String.valueOf(calender.get(Calendar.DAY_OF_MONTH)),
//                    String.valueOf(calender.get(Calendar.YEAR)));
//        } catch (Exception e) {
//            return "";
//        }
//    }

//    public static String getDateFormatted(String strMonth, String strDayOfMonth,
//                                          String strYear) {
//        try {
//            if (strMonth.length() == 1)
//                strMonth = "0" + strMonth;
//            if (strDayOfMonth.length() == 1)
//                strDayOfMonth = "0" + strDayOfMonth;
//            return strMonth + "/" + strDayOfMonth + "/" + strYear;
//        } catch (Exception e) {
//            return "";
//        }
//    }

//    public static String getCurrentTime() {
//        try {
//            Calendar calender = Calendar.getInstance();
//            return getTimeFormatted1(
//                    String.valueOf(calender.get(Calendar.HOUR_OF_DAY)),
//                    String.valueOf(calender.get(Calendar.MINUTE)));
//        } catch (Exception e) {
//            return "";
//        }
//
//    }

//    public static String getTimeFormatted1(String strHour, String strMinute) {
//        try {
//            if (strMinute.length() == 1)
//                strMinute = "0" + strMinute;
//            return getHourIn12Format(Integer.valueOf(strHour)) + ":" + strMinute + " " + getAMPM(strHour);
//        } catch (Exception e) {
//            return "";
//        }
//    }




  //  public static String getTimeFormatted2(String strHour, String strMinute) {
//        try {
//            if (strMinute.length() == 1)
//                strMinute = "0" + strMinute;
//            return strHour + ":" + strMinute;
//        } catch (Exception e) {
//            return "";
//        }
//    }
//
//    public static String getReFormattedDate(String date) {
//        try {
//            String[] dateTime = date.split("/");
//            return dateTime[2] + "-" + dateTime[0] + "-" + dateTime[1];
//        } catch (Exception e) {
//            return "";
//        }
//    }

//    public static void showNotification(Context nContext,
//                                        Class<?> nActivityClass, String title, String message) {
//        try {
////			Intent intent = new Intent(nContext, nActivityClass);
////			NotificationManager notificationManager = (NotificationManager) nContext
////					.getSystemService(Context.NOTIFICATION_SERVICE);
////			PendingIntent pendingIntent = PendingIntent.getActivity(nContext,
////					0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
////			NotificationCompat.Builder builder = new NotificationCompat.Builder(
////					nContext).setSmallIcon(R.drawable.ic_launcher)
////					.setContentTitle(title).setContentText(message)
////					.setContentIntent(pendingIntent).setAutoCancel(true);
////			notificationManager.notify(1, builder.build());
//        } catch (Exception e) {
//        }
//    }

//    public static void hideSoftKeyboard(View v, Context context) {
//        try {
//            InputMethodManager imm = (InputMethodManager) context
//                    .getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//        } catch (Exception e) {
//        }
//    }
//
//    /
//
//    public static void callAnActivityForResult(Context newContext,
//                                               Class<?> newActivityClass, int requestCode) {
//        try {
//            Intent newIntent = new Intent(newContext, newActivityClass);
//            ((Activity) newContext).startActivityForResult(newIntent, requestCode);
//        } catch (Exception e) {
//        }
//    }
//
//    public static void callAnActivityForResultWithParameter(Context newContext,
//                                                            Class<?> newActivityClass, int requestCode, String tag, String value) {
//        try {
//            Intent newIntent = new Intent(newContext, newActivityClass);
//            newIntent.putExtra(tag, value);
//            ((Activity) newContext).startActivityForResult(newIntent, requestCode);
//        } catch (Exception e) {
//
//        }
//    }

//    public static void callAnActivityWithParameter(Context newContext,
//                                                   Class<?> newActivityClass, String tag, String value) {
//        try {
//            Intent newIntent = new Intent(newContext, newActivityClass);
//            newIntent.putExtra(tag, value);
//            newContext.startActivity(newIntent);
//        } catch (Exception e) {
//
//        }
//    }

//    public static View createView(Context context, int layout, ViewGroup parent) {
//        try {
//            LayoutInflater newLayoutInflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            return newLayoutInflater.inflate(layout, parent, false);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
    public static String getStringPreference(Context nContext,
                                             String preferenceName, String preferenceItem, String defaultValue) {
        try {
            SharedPreferences nPreferences;
            nPreferences = nContext.getSharedPreferences(preferenceName,
                    Context.MODE_PRIVATE);
            return nPreferences.getString(preferenceItem, defaultValue);
        } catch (Exception e) {
            return "";
        }
    }

//    public static int getIntPreference(Context nContext,
//                                       String preferenceName, String preferenceItem, int deafaultValue) {
//        try {
//            SharedPreferences nPreferences;
//            nPreferences = nContext.getSharedPreferences(preferenceName,
//                    Context.MODE_PRIVATE);
//            return nPreferences.getInt(preferenceItem, deafaultValue);
//        } catch (Exception e) {
//            return deafaultValue;
//        }
//    }

//    public static Boolean getBooleanPreference(Context nContext,
//                                               String preferenceName, String preferenceItem,
//                                               Boolean defaultValue) {
//        try {
//            SharedPreferences nPreferences;
//            nPreferences = nContext.getSharedPreferences(preferenceName,
//                    Context.MODE_PRIVATE);
//            return nPreferences.getBoolean(preferenceItem, defaultValue);
//        } catch (Exception e) {
//            return defaultValue;
//        }
//    }
////
    public static void setStringPreference(Context nContext,
                                           String preferenceName, String preferenceItem,
                                           String preferenceItemValue) {
        try {
            SharedPreferences nPreferences;
            nPreferences = nContext.getSharedPreferences(preferenceName,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor nEditor = nPreferences.edit();
            nEditor.putString(preferenceItem, preferenceItemValue);
            nEditor.commit();
        } catch (Exception e) {
        }
    }

//    public static void setIntPreference(Context nContext,
//                                        String preferenceName, String preferenceItem,
//                                        int preferenceItemValue) {
//        try {
//            SharedPreferences nPreferences;
//            nPreferences = nContext.getSharedPreferences(preferenceName,
//                    Context.MODE_PRIVATE);
//            Editor nEditor = nPreferences.edit();
//            nEditor.putInt(preferenceItem, preferenceItemValue);
//            nEditor.commit();
//        } catch (Exception e) {
//        }
//    }

//    public static void setBooleanPreference(Context nContext,
//                                            String preferenceName, String preferenceItem,
//                                            Boolean preferenceItemValue) {
//        try {
//            SharedPreferences nPreferences;
//            nPreferences = nContext.getSharedPreferences(preferenceName,
//                    Context.MODE_PRIVATE);
//            Editor nEditor = nPreferences.edit();
//            nEditor.putBoolean(preferenceItem, preferenceItemValue);
//            nEditor.commit();
//        } catch (Exception e) {
//        }
//    }

//    public static String removeSpacing(String phoneNumber) {
//        try {
//            phoneNumber = phoneNumber.replace("-", "");
//            phoneNumber = phoneNumber.replace(" ", "");
//            removeNonDigits(phoneNumber);
//            if (phoneNumber.length() >= 11) {
//                phoneNumber = phoneNumber.substring(phoneNumber.length() - 11);
//            }
//            return phoneNumber;
//        } catch (Exception e) {
//            return phoneNumber;
//        }
//    }

//    public static String removeNonDigits(String text) {
//        try {
//            int length = text.length();
//            StringBuffer buffer = new StringBuffer(length);
//            for (int i = 0; i < length; i++) {
//                char ch = text.charAt(i);
//                if (Character.isDigit(ch)) {
//                    buffer.append(ch);
//                }
//            }
//            return buffer.toString();
//        } catch (Exception e) {
//            return text;
//        }
//    }
//
//    public static boolean getBoolean(int bolInt) {
//        try {
//            if (bolInt == 1)
//                return true;
//            else
//                return false;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public static boolean isNetworkAvailable(Context nContext) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) nContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager
                    .getActiveNetworkInfo();
            return activeNetworkInfo != null;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isWifiConnected(Context nContext) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) nContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (connectivityManager != null) {
                networkInfo = connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            }
            return networkInfo == null ? false : networkInfo.isConnected();
        } catch (Exception e) {
        }
        return false;
    }

//    public static boolean isInternetAvailable() {
//        try {
//            InetAddress ipAddr = InetAddress.getByName("google.com");
//            if (ipAddr.equals("")) {
//                return false;
//            } else {
//                return true;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//    }

//    public static boolean isEmailValid(String email) {
//        try {
//            Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
//            Matcher matcher = pattern.matcher(email);
//            return matcher.matches();
//        } catch (Exception e) {
//            return false;
//        }
//    }

//	public static void callTimePicker(Context nContext, String time,
//			OnTimeSetListener timePickerListener, Boolean is24Hours,
//			FragmentManager manager) {
//		try {
//			int hour, minute;
//			if (time.contains("Time")) {
//				Calendar calendar = Calendar.getInstance();
//				hour = calendar.get(Calendar.HOUR_OF_DAY);
//				minute = calendar.get(Calendar.MINUTE);
//
//			} else {
//				time=time.replace(":", " ");
//				String[] nTime = time.split(" ");
//				hour = getHourIn24FFormat(nTime[0],nTime[2]);
//				minute = Integer.parseInt(nTime[1]);
//			}
//			TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
//					timePickerListener, hour, minute, is24Hours);
//			timePickerDialog.setVibrate(false);
//			timePickerDialog.setCloseOnSingleTapMinute(false);
//			timePickerDialog.show(manager, "timepicker");
//		} catch (Exception e) {
//		}
//	}

//    private static int getHourIn24Format(String strHour, String strAM_PM) {
//        int hour = 0;
//        if (strAM_PM.equals("PM")) {
//            hour = Integer.parseInt(strHour) + 12;
//        } else {
//            if (Integer.parseInt(strHour) != 12) {
//                hour = Integer.parseInt(strHour);
//            }
//        }
//        return hour;
//    }

//    public static void callDatePicker(Context nContext, String date,
//                                      DatePickerDialog.OnDateSetListener datePickerListener, FragmentManager manager) {
//        try {
//            int day, month, year;
//            if (date.contains("")) {
//                Calendar calendar = Calendar.getInstance();
//                day = calendar.get(Calendar.DAY_OF_MONTH);
//                month = calendar.get(Calendar.MONTH);
//                year = calendar.get(Calendar.YEAR);
//            } else {
//                String[] nDate = date.split("/");
//                month = Integer.parseInt(nDate[0]) - 1;
//                day = Integer.parseInt(nDate[1]);
//                year = Integer.parseInt(nDate[2]);
//            }
//            DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
//                    datePickerListener, year, month, day, false);
//            datePickerDialog.setVibrate(false);
//            datePickerDialog.setYearRange(1985, 2028);
//            datePickerDialog.setCloseOnSingleTapDay(false);
//            datePickerDialog.show(manager, "datepicker");
//        } catch (Exception e) {
//        }
//    }
//
//    public static String getDateFormatted(String date) {
//        try {
//            date = date.replace("-", " ");
//            Date d = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
//                    .parse(date);
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(d);
//            int month = cal.get(Calendar.MONTH) + 1;
//            int day = cal.get(Calendar.DAY_OF_MONTH);
//            int year = cal.get(Calendar.YEAR);
//            return getDateFormatted("" + month, "" + day, "" + year);
//        } catch (Exception e) {
//            return "";
//        }
//    }
//
//    public static void setImage(ImageView nImageView, byte[] nImageBytes, Point size) {
//        new LoadImageTask(nImageView, nImageBytes, size).execute();
//    }

//    public static void setImage(ImageView nImageView, byte[] nImageBytes, Point size, boolean isRoundedCorners, int pixels) {
//        new LoadImageTask(nImageView, nImageBytes, size, isRoundedCorners, pixels).execute();
//    }
//
//    public static void setImageFromUrl(Context context, ImageView nImageView, String url, Point size, boolean isRounded) {
//        new LoadImageFromUrl(context, nImageView, url, size, isRounded).execute();
//    }
//
//    private static class LoadImageTask extends AsyncTask<String, Void, Boolean> {
//        private ImageView nImageView;
//        private byte[] mCameraData;
//        private Bitmap nBitmap;
//        private Point nSize;
//        private boolean isRoundedCorners;
//        private int pixels;
//
//        public LoadImageTask(ImageView nImageView, byte[] mCameraData, Point nSize, boolean isRoundedCorners, int pixels) {
//            this.nImageView = nImageView;
//            this.mCameraData = mCameraData;
//            this.nSize = nSize;
//            this.isRoundedCorners = isRoundedCorners;
//            this.pixels = pixels;
//        }
//
//        public LoadImageTask(ImageView nImageView, byte[] mCameraData, Point nSize) {
//            this.nImageView = nImageView;
//            this.mCameraData = mCameraData;
//            this.nSize = nSize;
//        }

//        @Override
//        protected Boolean doInBackground(String... params) {
//            nBitmap = loadBitmap(mCameraData);
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean result) {
//            super.onPostExecute(result);
//            if (nBitmap != null) {
//                nImageView.setImageBitmap(nBitmap);
//            }
//        }
//
//        private Bitmap loadBitmap(byte[] cameraData) {
//            Bitmap bitmap = Bitmap.createScaledBitmap(
//                    BitmapFactory.decodeByteArray(mCameraData, 0, mCameraData.length), nSize.x, nSize.y, true);
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
//            if (isRoundedCorners) {
//                bitmap = getRoundedCornerBitmap(bitmap, pixels);
//            }
//            Matrix nMatrix = new Matrix();
//            nMatrix.postRotate(90);
//            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), nMatrix, true);
//            return bitmap;
//        }
//
//        private Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
//            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
//                    .getHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(output);
//
//            final int color = 0xff424242;
//            final Paint paint = new Paint();
//            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//            final RectF rectF = new RectF(rect);
//            final float roundPx = pixels;
//
//            paint.setAntiAlias(true);
//            canvas.drawARGB(0, 0, 0, 0);
//            paint.setColor(color);
//            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//
//            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//            canvas.drawBitmap(bitmap, rect, rect, paint);
//
//            return output;
//        }
//    }
//
//    private static class LoadImageFromUrl extends AsyncTask<String, Void, Boolean> {
//        private ImageView nImageView;
//        private String url;
//        private Bitmap nBitmap;
//        private Point nSize;
//        private boolean isRounded;
//        private ImageLoader imageLoader;
///

//        @Override
//        protected Boolean doInBackground(String... params) {
//            nBitmap = loadBitmap(imageLoader.getBitmap(url));
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean result) {
//            super.onPostExecute(result);
//            if (nBitmap != null) {
//                nImageView.setImageBitmap(nBitmap);
//            }
//        }

//        private Bitmap loadBitmap(Bitmap bitmap) {
//            bitmap = Bitmap.createScaledBitmap(
//                    bitmap, nSize.x, nSize.y, true);
//            if (isRounded) {
//                bitmap = getRoundedShapeBitmap(bitmap);
//            }
//            return bitmap;
//        }
//
//        private Bitmap getRoundedShapeBitmap(Bitmap bitmap) {
//            int targetWidth = nSize.x;
//            int targetHeight = nSize.y;
//            Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
//                    targetHeight, Bitmap.Config.ARGB_8888);
//
//            Canvas canvas = new Canvas(targetBitmap);
//            Path path = new Path();
//            path.addCircle(((float) targetWidth - 1) / 2,
//                    ((float) targetHeight - 1) / 2,
//                    (Math.min(((float) targetWidth),
//                            ((float) targetHeight)) / 2),
//                    Path.Direction.CCW);
//            canvas.clipPath(path);
//            Bitmap sourceBitmap = bitmap;
//            canvas.drawBitmap(sourceBitmap,
//                    new Rect(0, 0, sourceBitmap.getWidth(),
//                            sourceBitmap.getHeight()),
//                    new Rect(0, 0, targetWidth, targetHeight), null);
//            return targetBitmap;
//        }
//    }
//
//    public static int dpToPx(int dp, Context context) {
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        int px = Math.round(dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
//        return px;
//    }

//    public static int pxToDp(int px, Context context) {
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        int dp = Math.round(px / (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
//        return dp;
//    }

//    public static ArrayList<ArrayList<String>> getCategoriesList() {
//        ArrayList<ArrayList<String>> categoriesList = new ArrayList<ArrayList<String>>();
//        categoriesList.add(new ArrayList<String>() {{
//            add("ic_sell");
//            add("Regulated Mods");
//        }});
//        categoriesList.add(new ArrayList<String>() {{
//            add("ic_sell");
//            add("Mechanical Mods");
//        }});
//        categoriesList.add(new ArrayList<String>() {{
//            add("ic_sell");
//            add("RDA Atomizers");
//        }});
//        categoriesList.add(new ArrayList<String>() {{
//            add("ic_sell");
//            add("RBA Atomizers");
//        }});
//        categoriesList.add(new ArrayList<String>() {{
//            add("ic_sell");
//            add("Genesis Style Atomizers");
//        }});
//        categoriesList.add(new ArrayList<String>() {{
//            add("ic_sell");
//            add("E-Juice");
//        }});
//        categoriesList.add(new ArrayList<String>() {{
//            add("ic_sell");
//            add("Accessories");
//        }});
//        categoriesList.add(new ArrayList<String>() {{
//            add("ic_sell");
//            add("Other");
//        }});
//        return categoriesList;
//    }

//    public static ArrayList<ArrayList<String>> getColorsList() {
//        ArrayList<ArrayList<String>> colorsList = new ArrayList<ArrayList<String>>();
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_black");
//            add("Black");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_blue");
//            add("Blue");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_brass");
//            add("Brass");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_bronze");
//            add("Bronze");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_copper");
//            add("Copper");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_gold");
//            add("Gold");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_green");
//            add("Green");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_grey");
//            add("Grey");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_pink");
//            add("Pink");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_purple");
//            add("Purple");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_red");
//            add("Red");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_silver");
//            add("Silver");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_stainless_steel");
//            add("Stainless Steel");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_teal");
//            add("Teal");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_white");
//            add("White");
//        }});
//        colorsList.add(new ArrayList<String>() {{
//            add("circle_color_yellow");
//            add("Yellow");
//        }});
//        return colorsList;
//    }
//
//    public static ArrayList<ArrayList<String>> getAuthentications() {
//        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
//        list.add(new ArrayList<String>() {{
//            add("ic_authentic");
//            add("Authentic");
//        }});
//        list.add(new ArrayList<String>() {{
//            add("ic_clone");
//            add("Clone");
//        }});
//        return list;
//    }
//
//    public static ArrayList<Double> getPackage() {
//        ArrayList<Double> list = new ArrayList<Double>();
//        list.add(3.29);
//        list.add(5.20);
//        list.add(11.30);
//        return list;
//    }
//
//    public static int getIndex(Spinner spinner, String myString) {
//        int index = 0;
//
//        for (int i = 0; i < spinner.getCount(); i++) {
//            if (spinner.getItemAtPosition(i).equals(myString)) {
//                index = i;
//            }
//        }
//        return index;
//    }
//
//    public static int getDeviceWidth(Context nConext) {
//        DisplayMetrics metrics = new DisplayMetrics();
//        ((Activity) nConext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        return metrics.widthPixels;
//    }
//
//    public static int getDeviceHeight(Context nConext) {
//        DisplayMetrics metrics = new DisplayMetrics();
//        ((Activity) nConext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        return metrics.heightPixels;
//    }
//
//    public static void createShipment() {
////        new CreateShipment().execute();
//    }

//    private static class CreateShipment extends AsyncTask<String, Void, Boolean> {
//
//
//        public CreateShipment() {
//
//        }
//
//        @Override
//        protected Boolean doInBackground(String... params) {
//            EasyPost.apiKey = "cueqNZUb3ldeWTNX7MU3Mel8UXtaAMUi";
//
//            Map<String, Object> fromAddressMap = new HashMap<String, Object>();
//            fromAddressMap.put("name", "Simpler Postage Inc");
//            fromAddressMap.put("street1", "388 Townsend St");
//            fromAddressMap.put("street2", "Apt 20");
//            fromAddressMap.put("city", "San Francisco");
//            fromAddressMap.put("state", "CA");
//            fromAddressMap.put("zip", "94107");
//            fromAddressMap.put("phone", "415-456-7890");
//
//            Map<String, Object> toAddressMap = new HashMap<String, Object>();
//            toAddressMap.put("name", "Sawyer Bateman");
//            toAddressMap.put("street1", "1A Larkspur Cres");
//            toAddressMap.put("street2", "");
//            toAddressMap.put("city", "St. Albert");
//            toAddressMap.put("state", "AB");
//            toAddressMap.put("zip", "T8N2M4");
//            toAddressMap.put("phone", "780-483-2746");
//            toAddressMap.put("country", "CA");
//
//            Map<String, Object> parcelMap = new HashMap<String, Object>();
//            parcelMap.put("weight", 22.9);
//            parcelMap.put("height", 12.1);
//            parcelMap.put("width", 8);
//            parcelMap.put("length", 19.8);
//
//            Map<String, Object> customsItem1Map = new HashMap<String, Object>();
//            customsItem1Map.put("description", "EasyPost T-shirts");
//            customsItem1Map.put("quantity", 2);
//            customsItem1Map.put("value", 23.56);
//            customsItem1Map.put("weight", 18.8);
//            customsItem1Map.put("origin_country", "us");
//            customsItem1Map.put("hs_tariff_number", "123456");
//
//            Map<String, Object> customsItem2Map = new HashMap<String, Object>();
//            customsItem2Map.put("description", "EasyPost Stickers");
//            customsItem2Map.put("quantity", 11);
//            customsItem2Map.put("value", 8.98);
//            customsItem2Map.put("weight", 3.2);
//            customsItem2Map.put("origin_country", "us");
//            customsItem2Map.put("hs_tariff_number", "654321");
//
//            try {
//                Parcel parcel = Parcel.create(parcelMap);
//                Address fromAddress = Address.create(fromAddressMap);
//                Address toAddress = Address.create(toAddressMap);
//
//                // Address verified = to_address.verify();
//
//                // customs
//                Map<String, Object> customsInfoMap = new HashMap<String, Object>();
//                customsInfoMap.put("integrated_form_type", "form_2976");
//                customsInfoMap.put("customs_certify", true);
//                customsInfoMap.put("customs_signer", "Dr. Pepper");
//                customsInfoMap.put("contents_type", "gift");
//                customsInfoMap.put("eel_pfc", "NOEEI 30.37(a)");
//                customsInfoMap.put("non_delivery_option", "return");
//                customsInfoMap.put("restriction_type", "none");
//                CustomsItem customsItem1 = CustomsItem.create(customsItem1Map);
//                CustomsItem customsItem2 = CustomsItem.create(customsItem2Map);
//                List<CustomsItem> customsItemsList = new ArrayList<CustomsItem>();
//                customsItemsList.add(customsItem1);
//                customsItemsList.add(customsItem2);
//                customsInfoMap.put("customs_items", customsItemsList);
//                CustomsInfo customsInfo = CustomsInfo.create(customsInfoMap);
//
//                // create shipment
//                Map<String, Object> shipmentMap = new HashMap<String, Object>();
//                shipmentMap.put("to_address", toAddress);
//                shipmentMap.put("from_address", fromAddress);
//                shipmentMap.put("parcel", parcel);
//                shipmentMap.put("customs_info", customsInfo);
//
//                Shipment shipment = Shipment.create(shipmentMap);
//
//                // buy postage
//                List<String> buyCarriers = new ArrayList<String>();
//                buyCarriers.add("USPS");
//                List<String> buyServices = new ArrayList<String>();
//                buyServices.add("PriorityMailInternational");
//
//                shipment = shipment.buy(shipment.lowestRate(buyCarriers, buyServices));
//
//                String s=  shipment.prettyPrint();
//                String s2=s;
//            } catch (EasyPostException e) {
//                e.printStackTrace();
//            }
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean result) {
//            super.onPostExecute(result);
//
//        }
//
//
//
//    }
}
