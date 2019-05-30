package lu.aqu.projper.project.details.di

import dagger.Component
import dagger.android.AndroidInjector
import lu.aqu.core.di.FragmentScope
import lu.aqu.projper.project.details.DetailsFragment
import lu.aqu.projper.project.usecase.ProjectUseCaseModule

@Component(
    modules = [ProjectUseCaseModule::class]
)
@FragmentScope
interface DetailsComponent : AndroidInjector<DetailsFragment>
