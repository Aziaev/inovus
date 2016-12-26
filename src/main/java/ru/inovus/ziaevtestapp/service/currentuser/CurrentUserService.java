package ru.inovus.ziaevtestapp.service.currentuser;

import ru.inovus.ziaevtestapp.domain.CurrentUser;

public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
