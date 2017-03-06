package com.github.githubapplication.user_details;

import com.github.githubapplication.entities.Repository;
import com.github.githubapplication.user_details.contracts.UserDetailsInteractor;
import com.github.githubapplication.user_details.contracts.UserDetailsView;

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
public class UserDetailsPresenterTest {
    @Mock
    UserDetailsView view;
    @Mock
    UserDetailsInteractor interactor;

    private UserDetailsPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new UserDetailsPresenterImpl(view, interactor);
    }

    @Test
    public void checkIfShowProgressOnShowUsersRepositories() {
        presenter.showUserRepositories("square");
        verify(view, times(1)).showProgress();
    }

    @Test
    public void checkIfViewIsReleasedOnDestroy() {
        presenter.viewDestroy();
        assertNull(presenter.getUserDetailsView());
    }

    @Test
    public void checkIfItemsAreReturnToView() {
        Repository repository = new Repository();
        List<Repository> items = Arrays.asList(repository, repository, repository);
        presenter.returnRepositories(items);
        verify(view, times(1)).hideProgress();
    }
}
