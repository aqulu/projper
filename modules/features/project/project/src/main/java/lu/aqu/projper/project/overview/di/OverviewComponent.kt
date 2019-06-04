package lu.aqu.projper.project.overview.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import lu.aqu.core.di.FragmentScope
import lu.aqu.core.di.Injector
import lu.aqu.projper.project.ProjectModule
import lu.aqu.projper.project.overview.OverviewFragment
import retrofit2.Retrofit

@Component(modules = [ProjectModule::class])
@FragmentScope
interface OverviewComponent : AndroidInjector<OverviewFragment>, Injector {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun retrofit(retrofit: Retrofit): Builder

        fun build(): OverviewComponent
    }
}
