package com.kbear.noknok.modules;

import com.kbear.noknok.ui.activities.LauncherActivity;
import com.kbear.noknok.ui.activities.MainActivity;
import com.kbear.noknok.bo.AccountBO;
import com.kbear.noknok.bo.ChatBO;
import com.kbear.noknok.bo.SocketBO;
import com.kbear.noknok.ui.fragments.ChatFragment;
import com.kbear.noknok.ui.fragments.LoginFragment;
import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.service.AccountService;
import com.kbear.noknok.service.ChatService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by allen on 3/8/15.
 */
@Module(
        injects = {
                ChatFragment.class,
                LoginFragment.class,
                MainActivity.class,
                LauncherActivity.class
        },
        complete = false
)
public class BusinessModule {

    @Provides
    ChatBO provideChatBO(ChatService chatService) {
        return new ChatBO(chatService);
    }

    @Provides
    AccountBO provideAccountBO(AccountService accountService) {
        return new AccountBO(accountService);
    }

    @Provides
    SocketBO provideSocketBO(SocketManager socketManager) {
        return new SocketBO(socketManager);
    }
}
