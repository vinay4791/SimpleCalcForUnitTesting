package vinay.com.simplecalcforunittesting.logintestdoubles;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import vinay.com.simplecalcforunittesting.logintestdoubles.authtoken.AuthTokenCache;
import vinay.com.simplecalcforunittesting.logintestdoubles.eventbus.EventBusPoster;
import vinay.com.simplecalcforunittesting.logintestdoubles.eventbus.LoggedInEvent;
import vinay.com.simplecalcforunittesting.logintestdoubles.networking.LoginHttpEndpointSync;
import vinay.com.simplecalcforunittesting.logintestdoubles.networking.NetworkErrorException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginUseCaseSyncMockitoTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String AUTH_TOKEN = "authToken";

    @Mock LoginHttpEndpointSync mLoginHttpEndpointSyncMock;
    @Mock EventBusPoster mEventBusPosterMock;
    @Mock AuthTokenCache mAuthTokenCacheMock;
    @Mock LoginUseCaseSync mLoginUseCaseSync;

    @Before
    public void setUp() throws Exception {
        /*mLoginHttpEndpointSyncMock = Mockito.mock(LoginHttpEndpointSync.class);
        mEventBusPosterMock = Mockito.mock(EventBusPoster.class);
        mAuthTokenCacheMock = Mockito.mock(AuthTokenCache.class);*/
        mLoginUseCaseSync = new LoginUseCaseSync(mLoginHttpEndpointSyncMock, mAuthTokenCacheMock, mEventBusPosterMock);
        success();
    }


    //username and password passed to the endpoint
    @Test
    public void loginSync_success_usernameAndPasswordPassedtoEndpoint() throws Exception {
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        mLoginUseCaseSync.loginSync(USERNAME, PASSWORD);
        verify(mLoginHttpEndpointSyncMock, times(1)).loginSync(ac.capture(), ac.capture());
        List<String> caprtues = ac.getAllValues();
        assertThat(caprtues.get(0), is(USERNAME));
        assertThat(caprtues.get(1), is(PASSWORD));
    }

    //if login succeeds users auth token must be cached
    @Test
    public void loginSync_success_authTokenCached() {
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        mLoginUseCaseSync.loginSync(USERNAME, PASSWORD);
        verify(mAuthTokenCacheMock).cacheAuthToken(ac.capture());
        assertThat(ac.getValue(), is(AUTH_TOKEN));
    }

    //if login fails auth token is not changed
    @Test
    public void loginSync_generalError_authTokennotCached() throws Exception {
        generalError();
        mLoginUseCaseSync.loginSync(USERNAME, PASSWORD);
        verifyNoMoreInteractions(mAuthTokenCacheMock);
    }

    private void generalError() throws Exception {
        when(mLoginHttpEndpointSyncMock.loginSync(any(String.class), any(String.class))).thenReturn(
                new LoginHttpEndpointSync.EndpointResult(LoginHttpEndpointSync.EndpointResultStatus.GENERAL_ERROR, ""));
    }

    @Test
    public void loginSync_authError_authTokennotCached() throws Exception {
        authError();
        mLoginUseCaseSync.loginSync(USERNAME, PASSWORD);
        verifyNoMoreInteractions(mAuthTokenCacheMock);
    }

    private void authError() throws Exception {
        when(mLoginHttpEndpointSyncMock.loginSync(any(String.class), any(String.class))).thenReturn(
                new LoginHttpEndpointSync.EndpointResult(LoginHttpEndpointSync.EndpointResultStatus.AUTH_ERROR, ""));
    }


    @Test
    public void loginSync_serverError_authTokennotCached() throws Exception {
        serverError();
        mLoginUseCaseSync.loginSync(USERNAME, PASSWORD);
        verifyNoMoreInteractions(mAuthTokenCacheMock);
    }

    private void serverError() throws Exception {
        when(mLoginHttpEndpointSyncMock.loginSync(any(String.class), any(String.class))).thenReturn(
                new LoginHttpEndpointSync.EndpointResult(LoginHttpEndpointSync.EndpointResultStatus.SERVER_ERROR, ""));
    }


    //if login succeeds login event posted to event bus
    @Test
    public void loginSync_serverError_loggedInEventPosted() {
        ArgumentCaptor<Object> ac = ArgumentCaptor.forClass(Object.class);
        mLoginUseCaseSync.loginSync(USERNAME, PASSWORD);
        verify(mEventBusPosterMock).postEvent(ac.capture());
        assertThat(ac.getValue(), is(instanceOf(LoggedInEvent.class)));

    }

    //if login fails no login event posted to event bus
    @Test
    public void loginSync_serverError_noInteractionWithEventBusPoster() {

    }

    //if login succeeds success returns

    @Test
    public void loginSync_success_failureReturned() {
        LoginUseCaseSync.UseCaseResult result = mLoginUseCaseSync.loginSync(USERNAME, PASSWORD);
        assertThat(result, is(LoginUseCaseSync.UseCaseResult.SUCCESS));
    }

    //if login fails failreturned
    @Test
    public void loginSync_failure_failureReturned() {

    }

    //network - network error returned
    @Test
    public void loginSync_networkError_networkErrorReturned() {

    }

    private void success() throws NetworkErrorException {
        when(mLoginHttpEndpointSyncMock.loginSync(any(String.class), any(String.class))).thenReturn(
                new LoginHttpEndpointSync.EndpointResult(LoginHttpEndpointSync.EndpointResultStatus.SUCCESS, AUTH_TOKEN));
    }

    @After
    public void tearDown() throws Exception {
    }
}