package lu.aqu.projper.project

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import lu.aqu.projper.project.di.DaggerOverviewComponent
import lu.aqu.projper.project.usecase.FindProjectsUseCase
import javax.inject.Inject

class OverviewFragment : Fragment() {

    @Inject
    lateinit var findProjectsUseCase: FindProjectsUseCase // TODO temporary test

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerOverviewComponent.create().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Overview", findProjectsUseCase.execute().joinToString(", "))
    }
}
