package lu.aqu.projper.project

import dagger.BindsInstance
import dagger.Component
import lu.aqu.core.di.FragmentScope
import lu.aqu.projper.project.bookmarks.BookmarksFragment
import lu.aqu.projper.project.details.DetailsFragment
import lu.aqu.projper.project.overview.OverviewFragment
import retrofit2.Retrofit

@Component(modules = [ProjectModule::class])
@FragmentScope
interface ProjectComponent : lu.aqu.core.di.Component {

    fun inject(overviewFragment: OverviewFragment)

    fun inject(detailsFragment: DetailsFragment)

    fun inject(bookmarksFragment: BookmarksFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun retrofit(retrofit: Retrofit): Builder

        fun build(): ProjectComponent
    }
}
