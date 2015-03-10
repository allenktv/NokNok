package com.kbear.noknok.modules;

import com.kbear.noknok.bo.AccountBO;
import com.kbear.noknok.bo.ChatBO;
import com.kbear.noknok.service.AccountService;
import com.kbear.noknok.service.ChatService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by allen on 3/8/15.
 */
@Module
public class BOModules {

    @Provides
    ChatBO provideChatBO() {
        return new ChatBO();
    }

    @Provides
    AccountBO provideAccountBO() {
        return new AccountBO();
    }

}
