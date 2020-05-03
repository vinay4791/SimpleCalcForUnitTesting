package vinay.com.simplecalcforunittesting.logintestdoubles;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import vinay.com.simplecalcforunittesting.logintestdoubles.authtoken.AuthTokenCache;
import vinay.com.simplecalcforunittesting.logintestdoubles.eventbus.EventBusPoster;
import vinay.com.simplecalcforunittesting.logintestdoubles.networking.LoginHttpEndpointSync;
import vinay.com.simplecalcforunittesting.logintestdoubles.networking.NetworkErrorException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LoginUseCaseSyncTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String AUTH_TOKEN = "authToken";


    LoginHttpEndpointSyncTestDouble mLoginHttpEndpointSyncTestDouble;
    AuthTokenCacheTestDouble mAuthTokenCacheTestDouble;
    LoginUseCaseSync mLoginUseCaseSync;

    @Before
    public void setUp() throws Exception {
        mLoginHttpEndpointSyncTestDouble = new LoginHttpEndpointSyncTestDouble();
        mAuthTokenCacheTestDouble = new AuthTokenCacheTestDouble();
        mLoginUseCaseSync = new LoginUseCaseSync(mLoginHttpEndpointSyncTestDouble,
                mAuthTokenCacheTestDouble,
                new EventBusPosterTestDouble());
    }

    //username and password passed to the endpoint
    @Test
    public void loginSync_success_usernameAndPasswordPassedtoEndpoint() {
        mLoginUseCaseSync.loginSync(USERNAME, PASSWORD);
        assertThat(mLoginHttpEndpointSyncTestDouble.mUsername, is(USERNAME));
        assertThat(mLoginHttpEndpointSyncTestDouble.mPassword, is(PASSWORD));
    }

    //if login succeeds users auth token must be cached
    @Test
    public void loginSync_success_authTokenCached() {
        mLoginUseCaseSync.loginSync(USERNAME, PASSWORD);
        assertThat(mAuthTokenCacheTestDouble.mAuthToken, is(AUTH_TOKEN));
    }

    //if login fails auth token is not changed
    @Test
    public void loginSync_generalError_authTokennotCached(){
mLoginHttpEndpointSyncTestDouble.isGeneralError = true;
        mLoginUseCaseSync.loginSync(USERNAME, PASSWORD);
        assertThat(mAuthTokenCacheTestDouble.getAuthToken(), is(""));
    }

    @Test
    public void loginSync_authError_authTokennotCached(){
        mLoginHttpEndpointSyncTestDouble.isAuthError = true;
        mLoginUseCaseSync.loginSync(USERNAME, PASSWORD);
        assertThat(mAuthTokenCacheTestDouble.getAuthToken(), is(""));
    }

    @Test
    public void loginSync_serverError_authTokennotCached(){
        mLoginHttpEndpointSyncTestDouble.isServerError = true;
        mLoginUseCaseSync.loginSync(USERNAME, PASSWORD);
        assertThat(mAuthTokenCacheTestDouble.getAuthToken(), is(""));
    }




    //if login succeeds login event posted to event bus


    //if login fails no login event posted to event bus

    //if login succeeds success returns

    //if login fails failreturned

    //network - network error returned

    /*
    This is a mix between  mock and stub test double.
    Mock : It recieves a method call. Records its parameters and inspect
    these parameters in our tests.

    Stub : It returns different return values
     */
    private static class LoginHttpEndpointSyncTestDouble implements LoginHttpEndpointSync {
        public String mUsername;
        public String mPassword;
        public boolean isGeneralError;
        public boolean isAuthError;
        public boolean isServerError;

        @Override
        public EndpointResult loginSync(String username, String password) throws NetworkErrorException {
            mUsername = username;
            mPassword = password;
            if(isGeneralError){
                return new EndpointResult(EndpointResultStatus.GENERAL_ERROR,"");
            }else if (isAuthError){
                return new EndpointResult(EndpointResultStatus.AUTH_ERROR,"");
            } else if (isServerError){
                return new EndpointResult(EndpointResultStatus.SERVER_ERROR,"");
            }else{
                return new EndpointResult(EndpointResultStatus.SUCCESS, AUTH_TOKEN);
            }

        }
    }

    /*
    This is a fake test Double. This is a working implementation. Instead of caching Auth Token to persistant storage we store this in memory.
     */
    private static class AuthTokenCacheTestDouble implements AuthTokenCache {

        String mAuthToken = "";

        @Override
        public void cacheAuthToken(String authToken) {
            mAuthToken = authToken;
        }

        @Override
        public String getAuthToken() {
            return mAuthToken;
        }
    }

    private static class EventBusPosterTestDouble implements EventBusPoster {

        @Override
        public void postEvent(Object event) {

        }
    }

    @After
    public void tearDown() throws Exception {
    }
}