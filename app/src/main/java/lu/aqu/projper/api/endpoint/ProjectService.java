package lu.aqu.projper.api.endpoint;

import java.util.List;

import io.reactivex.Observable;
import lu.aqu.projper.model.Project;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProjectService {

    @GET("projects")
    Observable<List<Project>> list();

    @GET("projects/{id}")
    Observable<Project> get(@Path("id") long id);

    @POST("projects")
    Observable<Project> create(@Body Project project);

}
