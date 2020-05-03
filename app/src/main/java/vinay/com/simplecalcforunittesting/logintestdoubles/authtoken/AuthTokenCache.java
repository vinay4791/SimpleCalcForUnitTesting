package vinay.com.simplecalcforunittesting.logintestdoubles.authtoken;

public interface AuthTokenCache {
    void cacheAuthToken(String authToken);
    String getAuthToken();
}
