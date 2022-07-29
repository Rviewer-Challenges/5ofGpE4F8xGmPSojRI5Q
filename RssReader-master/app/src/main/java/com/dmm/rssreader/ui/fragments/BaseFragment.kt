package com.dmm.rssreader.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.dmm.rssreader.ui.viewModel.MainViewModel
import java.lang.IllegalArgumentException

abstract class BaseFragment<VB : ViewBinding>(
	private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

	private lateinit var _binding: VB
	protected val binding: VB get() = _binding
	protected lateinit var viewModel: MainViewModel

	protected open fun setupUI() = Unit
	protected open fun setHasOptionsMenu() = Unit

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = bindingInflater.invoke(inflater)

		if (_binding == null) {
			throw IllegalArgumentException("Binding null")
		}

		setHasOptionsMenu()

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
		setupUI()
	}
}