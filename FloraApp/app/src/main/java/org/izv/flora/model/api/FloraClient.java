package org.izv.flora.model.api;

import org.izv.flora.model.entity.CreateResponse;
import org.izv.flora.model.entity.DeleteResponse;
import org.izv.flora.model.entity.EditResponse;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.entity.Imagen;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface FloraClient {

    @DELETE("api/flora/{id}")
    Call<DeleteResponse> deleteFlora(@Path("id") long id);

    @GET("api/flora")
    Call<ArrayList<Flora>> getFlora();

    @POST("api/flora")
    Call<CreateResponse> createFlora(@Body Flora flora);

    @PUT("api/flora/{id}")
    Call<EditResponse> editFlora(@Path("id") long id, @Body Flora flora);

    @Multipart
    @POST("api/imagen/subir")
    Call<Long> subirImagen(@Part MultipartBody.Part file, @Part("idflora") long idFlora, @Part("descripcion") String descripcion);

    @DELETE("api/imagen/{id}")
    Call<DeleteResponse> deleteImg(@Path("id") long id);

}