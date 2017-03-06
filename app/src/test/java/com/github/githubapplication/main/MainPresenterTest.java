package com.github.githubapplication.main;

import com.github.githubapplication.main.contracts.MainInteractor;
import com.github.githubapplication.main.contracts.MainView;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rx.subscriptions.CompositeSubscription;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by marcus on 05/03/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    @Mock
    MainView view;
    @Mock
    MainInteractor interactor;
    @Mock
    CompositeSubscription compositeSubscription;

    private MainPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenterImpl(view, interactor, compositeSubscription);
    }

    @Test
    public void checkIfShowProgressOnShowUsers() {
        presenter.showUsers();
        verify(view, times(1)).showProgress();
    }

    @Test
    public void checkIfShowProgressOnSearchUsers() {
        presenter.searchUsers(anyString());
        verify(view, times(1)).showProgress();
    }
}
