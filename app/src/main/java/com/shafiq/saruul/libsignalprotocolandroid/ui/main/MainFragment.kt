package com.shafiq.saruul.libsignalprotocolandroid.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shafiq.saruul.libsignalprotocolandroid.R
import com.shafiq.saruul.libsignalprotocolandroid.di.LibsignalProtocolAndroidApplication
import com.shafiq.saruul.libsignalprotocolandroid.di.LibsignalProtocolAndroidViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: LibsignalProtocolAndroidViewModelFactory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as LibsignalProtocolAndroidApplication).appComponent.mainComponent()
            .create().inject(fragment = this)
    }

}
