package lu.aqu.projper.project.overview.di

import dagger.Component
import dagger.android.AndroidInjector
import lu.aqu.core.di.FragmentScope
import lu.aqu.core.di.Injector
import lu.aqu.projper.project.overview.OverviewFragment
import lu.aqu.projper.project.usecase.ProjectUseCaseModule

@Component(
    modules = [ProjectUseCaseModule::class]
)
@FragmentScope
interface OverviewComponent : AndroidInjector<OverviewFragment>, Injector
