package lu.aqu.projper.project.usecase

import lu.aqu.projper.project.domain.Project

interface FindProjectsUseCase {

    fun execute(): List<Project>
}