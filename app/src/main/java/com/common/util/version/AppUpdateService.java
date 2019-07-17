package com.common.util.version;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.blankj.utilcode.util.NetworkUtils;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * 应用内更新服务
 *
 * @author 22064
 */
public class AppUpdateService {

    private Context context;
    private DownloadManager downloader;
    private DownloadReceiver downloaderReceiver;

    private boolean isRegistered = false;

    private long downloadTaskId = -12306;

    public AppUpdateService(Context context) {
        this.context = context;
        downloaderReceiver = new DownloadReceiver();
        downloader = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public void downloadAndInstall(String url) {
        if (url == null || !isNetworkActive()) {
            return;
        }

        Query query = new Query();
        query.setFilterById(downloadTaskId);
        Cursor cur = downloader.query(query);
        // 下载任务已经存在的话

        try {
            if (cur.moveToNext()) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DownloadManager.Request task = new DownloadManager.Request(Uri.parse(url));
        String apkName = extractName(url);
        Logger.i(apkName);
        // String title = String.format("%s - v%s", apkName,
        // latestVersion.name);
        // task.setTitle(title);
        // task.setDescription(latestVersion.feature);

        task.setVisibleInDownloadsUi(true);
        task.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        task.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, apkName);
        try {
            downloadTaskId = downloader.enqueue(task);
        } catch (Exception e) {
            Toast.makeText(context, "更新版本失败，请检查下载管理器是否已经关闭..", Toast.LENGTH_SHORT).show();
        }


        if (isRegistered) {
            return;
        }
        isRegistered = true;
        context.registerReceiver(downloaderReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    private boolean isNetworkActive() {
        return NetworkUtils.isConnected();
    }

    private String extractName(String path) {
        String tempFileName = "_temp@" + path.hashCode();
        boolean fileNameExist = path.substring(path.length() - 5, path.length()).contains(".");
        if (fileNameExist) {
            tempFileName = path.substring(path.lastIndexOf(File.separator) + 1);
        }

        return tempFileName;
    }

    class DownloadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context c, Intent intent) {
            if (downloader == null) {
                return;
            }
            long completeId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
            if (completeId == downloadTaskId) {
                Query query = new Query();
                query.setFilterById(downloadTaskId);
                Cursor cur = downloader.query(query);
                if (cur.moveToFirst()) {
                    int columnIndex = cur.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    if (DownloadManager.STATUS_SUCCESSFUL == cur.getInt(columnIndex)) {
                        String uriString = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                        File apkFile = new File(Uri.parse(uriString).getPath());
                        Logger.i(uriString);
                        Intent installIntent = new Intent(Intent.ACTION_VIEW);
                        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String type = "application/vnd.android.package-archive";
                        Uri uri;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            uri = FileProvider
                                    .getUriForFile(context, context.getPackageName() + ".fileProvider", apkFile);
                            installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            Logger.i(uri.toString());
                        } else {
                            uri = Uri.fromFile(apkFile);
                        }
                        installIntent.setDataAndType(uri, type);
                        context.startActivity(installIntent);
                        callOnPause();
                    } else {
                        Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
                    }
                }
                cur.close();
            }
        }
    }

    private void callOnPause() {
        if (!isRegistered) {
            return;
        }
        isRegistered = false;
        context.unregisterReceiver(downloaderReceiver);
    }

}
