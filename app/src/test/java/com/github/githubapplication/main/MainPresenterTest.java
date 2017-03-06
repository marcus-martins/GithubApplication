package com.github.githubapplication.main;

import com.github.githubapplication.entities.User;
import com.github.githubapplication.main.contracts.MainInteractor;
import com.github.githubapplication.main.contracts.MainView;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNull;
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

    private MainPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenterImpl(view, interactor);
    }

    @Test
    public void checkIfShowProgressOnShowUsers() {
        presenter.showUsers();
        verify(view, times(1)).showProgress();
    }

    @Test
    public void checkIfShowProgressOnSearchUsers() {
        presenter.searchUsers("teste");
        verify(view, times(1)).showProgress();
    }

    @Test
    public void checkIfViewIsReleasedOnDestroy() {
        presenter.viewDestroy();
        assertNull(presenter.getMainView());
    }

    @Test
    public void checkIfItemsAreReturnToView() {
        User user = new User();
        List<User> items = Arrays.asList(user, user, user);
        presenter.returnUsers(items);
        verify(view, times(1)).hideProgress();
    }
}
