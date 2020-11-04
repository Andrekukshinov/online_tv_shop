package application.andrei.kukshinov.security.jwt;

public class SecurityConstants {
    public static final String SECRET_KEY = "TheSecretKey_MustBeReallyL0ng&ComplicatedTh1sНеКатитBut1'mTrying2DoMyBest";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String CLAIM_AUTHORITIES = "authorities";
    public static final Integer EXPIRATION_TOKEN_TIME_SECONDS = 14 * 24 * 60 * 60;
}
