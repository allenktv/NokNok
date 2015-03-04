package com.kbear.noknok.service.completionhandlers;

import com.kbear.noknok.dtos.CustomError;

/**
 * Created by allen on 2/16/15.
 */
public interface IBaseCompletionHandler {
    public void onFailure(final CustomError error);
    public void onProgress(final int bytesWritten, final int totalSize);
}
