package lu.aqu.projper.api.endpoint;

import java.util.List;

import io.reactivex.Observable;
import lu.aqu.projper.model.Project;
import retrofit2.http.GET;

public interface ProjectService {

    @GET("projects.json")
    Observable<List<Project>> list();

//    @GET("projects/{id}")
//    Observable<Project> get(@Path("id") long id);

//    @POST("projects")
//    Observable<Project> create(@Body Project project);

}
