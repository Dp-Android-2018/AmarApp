package dp.com.amarapp.utils;

import retrofit2.http.PUT;

public class ConfigurationFile {

    public static class UrlConstants{
        public static final String BASE_URL="http://www.dp-itc.com:8080";
        public static final String LOGIN_URL="/api/login";
        public static final String CHANGE_PASSWORD_URL="/api/change-password";
        public static final String CHECK_MAIL_URL="/api/check/email";
        public static final String CHECK_phone_URL="/api/check/phone";
        public static final String CLIENT_REGISTRATION_URL="/api/clients/register";
        public static final String CREATE_COMMENTS_URL="/api/clients/comments";
        public static final String UPDATE_DELETE_COMMENTS_URL="/api/clients/comments/{id}";
        public static final String CREATE_RATINGS_URL="/api/clients/ratings";
        public static final String UPDATE_DELETE_RATINGS_URL="/api/clients/ratings/{id}";
        public static final String COMPANY_REGISTRATION_URL="/api/companies/register";
        public static final String CREATE_PROJECTS_URL="/api/companies/projects";
        public static final String UPDATE_DELETE_PROJECTS_URL="/api/companies/projects/{id}";
        public static final String ADD_WORK_DAYS_URL="/api/companies/workdays";
        public static final String UPDATE_DELETE_WORK_DAYS_URL="/api/companies/workdays/{id}";
        public static final String COMPANIES_SEARCH_URL="api/search";
        public static final String COMPANY_DETAIL_URL="api/company/{id}";
        public static final String COMPANY_PROJECTS_URL="api/company/{id}/projects";
        public static final String COMPANY_COMMENTS_URL="api/company/{id}/comments";
        public static final String GET_COUNTRIES_URL="api/utilities/countries";
        public static final String GET_CITIES_URL="api/utilities/cities";
        public static final String GET_CATEGORIES_URL="api/utilities/categories";
        public static final String GET_SPICALIZATION_URL="api/utilities/specializations";
        public static final String GET_DURATIONS_URL="api/utilities/durations";
        public static final String GET_ADS_URL="api/utilities/ads";
        public static final String ABOUT_US_URL="api/utilities/page/about_us";
        public static final String PRIVACY_PAGE_URL="api/utilities/page/privacy";
        public static final String ADD_METADATA_URL="/api/companies/company-meta-data";
        public static final String UPDATE_METADATA_URL="/api/companies/company-meta-data";
        public static final String CREATE_ADS_URL="/api/companies/ads";
        public static final String UPDATE_DELETE_ADS="/api/companies/ads/{id}";
        public static final String ACTIVE_PHONE_URL="/api/activate/phone";
        public static final String CREATE_COMMENT_URL="/api/clients/comments";
        public static final String SEND_ACTIVATION_MAIL_URL="/api/activate/email/send";
        public static final String SEND_ACTIVATION_CODE_URL="/api/activate/phone/send";
        public static final String FORGET_PASSWORD_URL="/api/forget";
    }

    public static class Constants{
        public static final String API_KEY="nKwyEX0bIDvmAliwVA5iVHM081embegJYrF7UeFLl89iHwEkuF4MWglIEkk9";
        public static final String CONTENT_TYPE="application/json";
        public static final String CLIENT="client";
        public static final String COMPANY="company";
        public static final int YOU_ARE_COMPANY=28;

        public static final int FILL_ALL_DATA_ERROR=-601;
        public static final int INVALED_EMAIL=-602;
        public static final int INVALED_PHONE=-603;
        public static final int INVALED_PASSWORD=-604;
        public static final int PASSWORD_MATCHES_CODE=-605;
        public static final int EXISET_MAIL_CODE=-606;
        public static final int EXISET_PHONE_CODE=-607;
        public static final int NO_INTERNET_CONNECTION_CODE=-608;
        public static final int NEW_PASS_EQUAL_OLD_PASS=-609;
        public static final int NOT_REGISTER=-610;

        //---------------------------------------------------------
        public static final int MOVE_TO_FORGET_PASSWORD_ACTIVITY=1;
        public static final int MOVE_TO_HOME_ACTIVITY=2;
        public static final int MOVE_TO_MEMBERSHIP_ACTIVITY=3;
        public static final int MOVE_TO_SECOND_REGISTER_ACTIVITY=4;
        public static final int CLIENT_REGISTRATION =5;
        public static final int COMPANY_REGISTRATION =6;
        public static final int LOGOUT=7;
        public static final int SHOWDIALOG=8;
        public static final int CLOSE_MENU_DRAWER=14;
        public static final int SELECT_COUNTRY=15;
        public static final int MOVE_TO_COUNTRY_ACT=16;
        public static final int MOVE_TO_CITY_ACT=17;
        public static final int COUNTRY_ADAPTER=18;
        public static final int CATEGORY_ADAPTER=19;
        public static final int MOVE_TO_CATEGORY_ACT=20;
        public static final int MOVE_TO_SPECIALIZATION_ACT=21;
        public static final int SELECT_GATEGORY=22;
        public static final int ADDPROJECT=23;
        public static final int GETIMAGE=24;
        public static final int PICK_IMAGE_REQUEST=25;
        public static final int FINISH_ADD_PROJECT_ACTIVITY=26;

        public static final int BACK_IMAGE_VISIBILITY_CODE=27;
        public static final int BACK_IMAGE_UNVISIBILITY_CODE=28;
        //--------------------------------------------------------------

        public static final int SUCCESS_CODE=201;
        public static final int SUCCESS_CODE_second=200;
        public static final int INVALED_DATA_CODE=422;
        public static final int SERVER_ERROR=500;
        public static final int INVALED_EMAIL_PASSWORD=401;
        public static final int ALREADY_ACTIVATED=403;
        public static final int SUSBENDED=417;
    }
    public static class SharedPrefConstants{
        public static final String SHARED_PREF_NAME="AMAR";
        public static final String PREF_AMAR_CLIENT_OBJ_DATA ="CLIENT_DATA";
        public static final String PREF_REGISTER_OBJECT ="FIRST_STEP";
    }
    public static class IntentConstants{
        public static final String USER_NAME="USENAME";
        public static final String COMPANYSEARCHDATA="RESULTS_LIST";
        public static final String COMPANYITEMINFO="COMPANYITEM";
        public static final String COMPANYID="ID";
        public static final String COMPANYTOKEN="TOKEN";
        public static final String PROJECTINFO="PROJECTDATA";
        public static final String ADVERTINFO="ADVERTINFO";
        public static final int REQUEST_CODE_COUNTRY=111;
        public static final int REQUEST_CODE_CITY=222;
        public static final int REQUEST_CODE_CATEGORY=333;
        public static final int REQUEST_CODE_SPECIALIZATION=444;
        public static final String COUNTRY_DATA="COUNTRY";
        public static final String CITY_DATA="CITY";
        public static final String CATEGORY_DATA="CATEGORY";
        public static final String SPECIALIZATION_DATA="SPECIALIZATION";
        public static final String CITIES="CITIES";

    }

    public static class FragmentID{
        public static final int FRAGMENT1=1;
        public static final int FRAGMENT2=2;
        public static final int FRAGMENT3=3;
        public static final int FRAGMENT4=4;
        public static final int FRAGMENT5=5;
        public static final int FRAGMENT6=6;
        public static final int FRAGMENT7=7;
        public static final int HOME=8;
        public static final int ADDADVERT=9;
        public static final int ADVERTS=10;
        public static final int COMPANIES=11;
        public static final int SETTINGS=12;
        public static final int SEARCH=13;
    }
}
