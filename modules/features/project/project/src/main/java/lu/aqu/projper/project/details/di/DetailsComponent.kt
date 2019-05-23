package lu.aqu.projper.project.details.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import lu.aqu.core.di.FragmentScope
import lu.aqu.projper.project.details.DetailsFragment
import lu.aqu.projper.project.domain.Project
import lu.aqu.projper.project.usecase.ProjectUseCaseModule

@Component(
    modules = [ProjectUseCaseModule::class]
)
@FragmentScope
interface DetailsComponent : AndroidInjector<DetailsFragment> {

    fun projectId(): Project.Id

    @Component.Builder
    interface Builder {

        fun build(): DetailsComponent

        @BindsInstance
        fun projectId(id: Project.Id): Builder
    }
}
