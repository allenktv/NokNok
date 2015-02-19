package com.kbear.noknok.utils.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.kbear.noknok.R;
import com.kbear.noknok.common.AppConstants;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by allen on 2/18/15.
 */
public final class ExternalStorageHelper {
    private static final String TAG = "ExternalStorageHelper";
    private static final String CD_IMAGE_EXTENSION = "-NokNok.jpg";
    private static final String CD_VIDEO_EXTENSION = "-NokNok.mp4";

    /**
     * Helper Methods
     */
    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    private static File getAlbumStorageDir(String fileName) {
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/" + AppConstants.MEDIA_FILE_DIRECTORY);
        file.mkdirs();
        return new File(file, fileName);
    }

    /**
     * Images
     */

    public static void saveImage(Context context, Bitmap bitmap, boolean async) {
        if (!isExternalStorageWritable()) {
            Toast.makeText(context, context.getText(R.string.no_storage_found), Toast.LENGTH_SHORT).show();
            return;
        }
        if (async) {
            saveImageAsync(context, bitmap, new Date() + CD_IMAGE_EXTENSION);
        } else {
            saveImage(context, bitmap, new Date() + CD_IMAGE_EXTENSION);
        }
    }

    public static void saveImage(Context context, Bitmap bitmap, boolean async, String postfix) {
        if (!isExternalStorageWritable()) {
            Toast.makeText(context, context.getText(R.string.no_storage_found), Toast.LENGTH_SHORT).show();
            return;
        }
        if (async) {
            saveImageAsync(context, bitmap, new Date() + postfix + CD_IMAGE_EXTENSION);
        } else {
            saveImage(context, bitmap, new Date() + postfix + CD_IMAGE_EXTENSION);
        }
    }

    private static void saveImageAsync(final Context context, final Bitmap bitmap, final String fileName) {
        AsyncTask<Void, Boolean, Boolean> saveImageTask = new AsyncTask<Void, Boolean, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                File file = getAlbumStorageDir(fileName);
                try {
                    OutputStream stream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    stream.flush();
                    stream.close();
                    new SingleMediaScanner(context, file);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    Toast.makeText(context, context.getText(R.string.image_saved), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, context.getText(R.string.failed_save_image), Toast.LENGTH_SHORT).show();
                }
            }
        };
        saveImageTask.execute();
    }

    private static void saveImage(Context context, Bitmap bitmap, String fileName) {
        File file = getAlbumStorageDir(fileName);
        try {
            OutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
            new SingleMediaScanner(context, file);
            Toast.makeText(context, context.getText(R.string.image_saved), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, context.getText(R.string.failed_save_image), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Video
     */

    //Saves video from a given URI, doesn't need to be async since its fast.
    public static void saveVideo(Context context, Uri uri) {
        if (!isExternalStorageWritable()) {
            Toast.makeText(context, context.getText(R.string.no_storage_found), Toast.LENGTH_SHORT).show();
            return;
        }
        String sourceFilename= uri.getPath();
        File destinationFile = getAlbumStorageDir(new Date() + CD_VIDEO_EXTENSION);

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(sourceFilename));
            bos = new BufferedOutputStream(new FileOutputStream(destinationFile.getAbsolutePath(), false));
            byte[] buf = new byte[1024];
            bis.read(buf);
            do {
                bos.write(buf);
            } while(bis.read(buf) != -1);
            new SingleMediaScanner(context, destinationFile);
            Toast.makeText(context, context.getString(R.string.video_saved), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, context.getString(R.string.failed_save_video), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class SingleMediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

        private MediaScannerConnection mMs;
        private File mFile;

        public SingleMediaScanner(Context context, File f) {
            mFile = f;
            mMs = new MediaScannerConnection(context, this);
            mMs.connect();
        }

        @Override
        public void onMediaScannerConnected() {
            mMs.scanFile(mFile.getAbsolutePath(), null);
        }

        @Override
        public void onScanCompleted(String path, Uri uri) {
            mMs.disconnect();
        }
    }
}
