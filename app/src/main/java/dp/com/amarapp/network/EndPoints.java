package dp.com.amarapp.network;

import com.google.gson.JsonElement;

import dp.com.amarapp.model.request.AddProjectRequest;
import dp.com.amarapp.model.request.ChangePasswordRequest;
import dp.com.amarapp.model.request.CheckMailRequest;
import dp.com.amarapp.model.request.CheckPhoneRequest;
import dp.com.amarapp.model.request.ClientRegisterRequest;
import dp.com.amarapp.model.request.CodeRequest;
import dp.com.amarapp.model.request.CommentRequest;
import dp.com.amarapp.model.request.CompanyRegisterRequest;
import dp.com.amarapp.model.request.CreateAdvertRequest;
import dp.com.amarapp.model.request.ForgetPasswordRequest;
import dp.com.amarapp.model.request.LoginRequest;
import dp.com.amarapp.model.request.UpdateMetaDataRequest;
import dp.com.amarapp.model.response.AdvertResponse;
import dp.com.amarapp.model.response.CategoriesResponse;
import dp.com.amarapp.model.response.ClientRegisterResponse;
import dp.com.amarapp.model.response.CompaniesSearchResponse;
import dp.com.amarapp.model.response.CompanyCommentsResponse;
import dp.com.amarapp.model.response.CompanyProjectResponse;
import dp.com.amarapp.model.response.CompanyRegisterResponse;
import dp.com.amarapp.model.response.CountryResponse;
import dp.com.amarapp.model.response.CreateAdvertResponse;
import dp.com.amarapp.model.response.DefaultResponse;
import dp.com.amarapp.model.response.LoginResponse;
import dp.com.amarapp.utils.ConfigurationFile;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPoints {

    //client registration
    @POST(ConfigurationFile.UrlConstants.CLIENT_REGISTRATION_URL)
    Observable<Response<ClientRegisterResponse>> clientRegister(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ClientRegisterRequest clientRegisterRequest);

    //login
    @POST(ConfigurationFile.UrlConstants.LOGIN_URL)
    Observable<Response<LoginResponse>> login(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body LoginRequest loginRequest);

    //check mail
    @POST(ConfigurationFile.UrlConstants.CHECK_MAIL_URL)
    Observable<Response<Integer>>checkMail(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body CheckMailRequest mailRequest);

    //check phone
    @POST(ConfigurationFile.UrlConstants.CHECK_phone_URL)
    Observable<Response<Integer>>checkPhone(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body CheckPhoneRequest phoneRequest);

    //company registration
    @POST(ConfigurationFile.UrlConstants.COMPANY_REGISTRATION_URL)
    Observable<Response<CompanyRegisterResponse>> companyRegister(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body CompanyRegisterRequest companyRegisterRequest);

    //change password
    @POST(ConfigurationFile.UrlConstants.CHANGE_PASSWORD_URL)
    Observable<Response<JsonElement>>changePassword(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization")String token, @Body ChangePasswordRequest passwordRequest);

    //company search
    @GET(ConfigurationFile.UrlConstants.COMPANIES_SEARCH_URL)
    Observable<Response<CompaniesSearchResponse>>companySearch(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Query("countryName") String country,@Query("cityName")String city,@Query("specialization") String specialization,@Query("category")String category,@Query("sort")String sort,@Query("page")String pageId);

    //company Projects
    @GET(ConfigurationFile.UrlConstants.COMPANY_PROJECTS_URL)
    Observable<Response<CompanyProjectResponse>>companyProjects(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token, @Path("id") int id);

    //create advert
    @POST(ConfigurationFile.UrlConstants.CREATE_ADS_URL)
    Observable<Response<CreateAdvertResponse>>createAdvert(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token , @Body CreateAdvertRequest createADRequest);

    //get adverts
    @GET(ConfigurationFile.UrlConstants.GET_ADS_URL)
    Observable<Response<AdvertResponse>>getAdverts(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    //company comments
    @GET(ConfigurationFile.UrlConstants.COMPANY_COMMENTS_URL)
    Observable<Response<CompanyCommentsResponse>>companyComment(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token , @Path("id")int id);

    //update metadata
    @PUT(ConfigurationFile.UrlConstants.UPDATE_METADATA_URL)
    Observable<Response<DefaultResponse>>updateMetadata(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token, @Body UpdateMetaDataRequest request);

    //get countries
    @GET(ConfigurationFile.UrlConstants.GET_COUNTRIES_URL)
    Observable<Response<CountryResponse>>getCountries(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    //get Categories
    @GET(ConfigurationFile.UrlConstants.GET_CATEGORIES_URL)
    Observable<Response<CategoriesResponse>>getCategories(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    //add project
    @POST(ConfigurationFile.UrlConstants.CREATE_PROJECTS_URL)
    Observable<Response<DefaultResponse>>addProject(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token,@Body AddProjectRequest request);


    //Send Activation Email
    @POST(ConfigurationFile.UrlConstants.SEND_ACTIVATION_MAIL_URL)
    Observable<Response<DefaultResponse>>sendActivationMail(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token);

    //Send Activation Code
    @POST(ConfigurationFile.UrlConstants.SEND_ACTIVATION_CODE_URL)
    Observable<Response<DefaultResponse>>sendActivationCode(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token,@Body CheckPhoneRequest phone);

    //Activation using phone
    @POST(ConfigurationFile.UrlConstants.ACTIVE_PHONE_URL)
    Observable<Response<DefaultResponse>>activePhone(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token,@Body CodeRequest code);

    //create comment
    @POST(ConfigurationFile.UrlConstants.CREATE_COMMENT_URL)
    Observable<Response<DefaultResponse>>createComment(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token,@Body CommentRequest request);

    //forget password
    @POST(ConfigurationFile.UrlConstants.FORGET_PASSWORD_URL)
    Observable<Response<DefaultResponse>>forgetPassword(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept,@Body ForgetPasswordRequest request);

}
