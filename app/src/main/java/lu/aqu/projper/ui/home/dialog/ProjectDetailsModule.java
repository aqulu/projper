package lu.aqu.projper.ui.home.dialog;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import lu.aqu.projper.ui.home.adapter.TagsAdapter;

@Module
public abstract class ProjectDetailsModule {

    @Provides
    static TagsAdapter tagsAdapter() {
        return new TagsAdapter(new ArrayList<>(), null);
    }

}
