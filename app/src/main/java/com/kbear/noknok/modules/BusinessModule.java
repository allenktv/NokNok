package com.kbear.noknok.modules;

import com.kbear.noknok.activities.LauncherActivity;
import com.kbear.noknok.activities.MainActivity;
import com.kbear.noknok.bo.AccountBO;
import com.kbear.noknok.bo.ChatBO;
import com.kbear.noknok.service.AccountService;
import com.kbear.noknok.service.ChatService;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by allen on 3/8/15.
 */
@Module(
        injects = {
                LauncherActivity.class,
                MainActivity.class
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

}
