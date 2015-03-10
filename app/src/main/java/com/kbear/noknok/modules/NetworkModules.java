package com.kbear.noknok.modules;

import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.service.AccountService;
import com.kbear.noknok.service.ChatService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by allen on 3/9/15.
 */
@Module
public class NetworkModules {

    @Provides
    @Singleton
    SocketManager provideSocketManager() {
        return new SocketManager();
    }

    @Provides
    ChatService provideChatService() {
        return new ChatService();
    }

    @Provides
    AccountService provideAccountService() {
        return new AccountService();
    }
}
