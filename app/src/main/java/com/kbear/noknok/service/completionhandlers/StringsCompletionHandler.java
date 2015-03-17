package com.kbear.noknok.service.completionhandlers;

import java.util.List;

/**
 * Created by allen on 3/16/15.
 */
public abstract class StringsCompletionHandler extends AbstractBaseCompletionHandler {
    public abstract void onSuccess(final List<String> strings);
}
