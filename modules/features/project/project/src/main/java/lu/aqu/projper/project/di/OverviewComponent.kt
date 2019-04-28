package lu.aqu.projper.project.di

import dagger.Component
import dagger.android.AndroidInjector
import lu.aqu.core.di.FragmentScope
import lu.aqu.projper.project.OverviewFragment
import lu.aqu.projper.project.usecase.ProjectUseCaseModule

@Component(
    modules = [ProjectUseCaseModule::class]
)
@FragmentScope
interface OverviewComponent : AndroidInjector<OverviewFragment>
