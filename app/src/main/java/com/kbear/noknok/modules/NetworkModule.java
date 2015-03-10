package com.kbear.noknok.modules;

import com.kbear.noknok.bo.AccountBO;
import com.kbear.noknok.bo.ChatBO;
import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.service.AccountService;
import com.kbear.noknok.service.ChatService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by allen on 3/9/15.
 */
@Module(
        injects = {
                ChatBO.class,
                AccountBO.class
        }
)
public class NetworkModule {

    @Provides
    @Singleton
    SocketManager provideSocketManager() {
        return new SocketManager();
    }

    @Provides
    ChatService provideChatService(SocketManager socketManager) {
        return new ChatService(socketManager);
    }

    @Provides
    AccountService provideAccountService(SocketManager socketManager) {
        return new AccountService(socketManager);
    }
}
