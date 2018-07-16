package idev.gin.nias.data.remote;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://damp-fjord-46089.herokuapp.com/";

    public static APIServiceSignUp getAPIRegis() {

        return RetrofitClient.getClient(BASE_URL).create(APIServiceSignUp.class);
    }
    public static APIServiceFaskes getAPIFaskes() {

        return RetrofitClient.getClient(BASE_URL).create(APIServiceFaskes.class);
    }
    public static APIServiceKontak getAPIKontak() {

        return RetrofitClient.getClient(BASE_URL).create(APIServiceKontak.class);
    }
    public static APIServicePelacakan getAPIPelacakan() {

        return RetrofitClient.getClient(BASE_URL).create(APIServicePelacakan.class);
    }
    public static APIServiceRiwayat getAPIRiwayat() {

        return RetrofitClient.getClient(BASE_URL).create(APIServiceRiwayat.class);
    }
    public static APIServiceSkoring getAPISkoring() {

        return RetrofitClient.getClient(BASE_URL).create(APIServiceSkoring.class);
    }
}
