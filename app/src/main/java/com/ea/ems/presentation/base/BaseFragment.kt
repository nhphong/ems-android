package com.ea.ems.presentation.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.ea.ems.core.annotation.AllOpen
import com.ea.ems.core.util.FragmentLifeCycleCallbacks
import com.ea.ems.core.util.SingleLiveEvent
import com.ea.ems.core.util.hideKeyboard
import com.ea.ems.core.view.DialogUtil
import com.ea.ems.core.view.Toaster
import com.ea.ems.presentation.main.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract val viewModel: BaseViewModel

    protected val mainViewModel: MainViewModel by sharedViewModel()

    protected val toaster: Toaster by inject()

    protected val dialogUtil: DialogUtil by inject()

    var activityResultCallback: ((ActivityResult) -> Unit)? = null

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    val lifeCycleCallbacks = FragmentLifeCycleCallbacks()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityResultLauncher = registerForActivityResult(StartActivityForResult()) {
            activityResultCallback?.invoke(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        bindViewModel()
    }

    protected open fun initUI() {
        if (this is NoBottomBar) {
            mainViewModel.hideBottomBar()
        } else {
            mainViewModel.showBottomBar()
        }
    }

    protected open fun bindViewModel() {
        viewModel.run {
            isLoading.observe {
                if (it) dialogUtil.showProgressDialog(view) else dialogUtil.hideProgressDialog()
            }
            errorMessage.observe { dialogUtil.showDialog(view, it) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        lifeCycleCallbacks.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogUtil.hideProgressDialog()
    }

    protected fun <T> SingleLiveEvent<T>.observe(block: (T) -> Unit) {
        observe(viewLifecycleOwner, { block(it) })
    }
}

interface NoBottomBar
