package com.kbear.noknok.utils.helpers;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by allen on 2/18/15.
 */
public class GalleryHelper {
    /**
     * Gets image path from given Uri.
     *
     * @param context Context.
     * @param uri     Uri for the query.
     * @return The string value for the file path, returns null if does not match any criteria.
     */
    public static String getImagePath(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        String filePath = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (DocumentsContract.isDocumentUri(context, uri)) {
                String wholeID = DocumentsContract.getDocumentId(uri);

                String id;
                try {
                    // Split at colon, use second item in the array
                    id = wholeID.split(":")[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    Log.e("GalleryHelper", "Failed to fetch image path. Invalid DocumentID.");
                    return null;
                }

                String[] column = {MediaStore.Images.Media.DATA};

                // where id is equal to
                String sel = MediaStore.Images.Media._ID + "=?";

                Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, sel, new String[]{id}, null);

                if (cursor != null) {
                    int columnIndex = cursor.getColumnIndex(column[0]);

                    if (cursor.moveToFirst()) {
                        filePath = cursor.getString(columnIndex);
                    }
                    cursor.close();
                }
            }
        } else {
            //Anything below kit kat
            String[] proj = {MediaStore.Images.Media.DATA};
            CursorLoader cursorLoader = new CursorLoader(context, uri, proj, null, null, null);
            Cursor cursor = cursorLoader.loadInBackground();
            if (cursor != null) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                filePath = cursor.getString(column_index);
                cursor.close();
            }
        }

        if (TextUtils.isEmpty(filePath)) {
            if (uri.getScheme().equalsIgnoreCase("file")) {
                filePath = uri.getPath();
            } else if (uri.getScheme().equalsIgnoreCase("content")) {
                filePath = getPathFromDataColumn(context, uri, null, null);
            }
        }

        return filePath;
    }

    /**
     * Gets the file path for file-based ContentProvides.
     *
     * @param context       Context.
     * @param uri           Uri for the query..
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getPathFromDataColumn(Context context, Uri uri, String selection,
                                               String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }
}
