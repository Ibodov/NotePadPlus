package com.smile.notepad.screens.start

import android.app.ActionBar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.smile.notepad.R
import com.smile.notepad.databinding.FragmentStartBinding
import com.smile.notepad.utilites.APP_ACTIVITY
import com.smile.notepad.utilites.TYPE_ROOM


class StartFragment : Fragment() {

    private var _binding : FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var actionBar: ActionBar

    private lateinit var mViewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java) //привязка к жизненному цыклу фрагмента
        mViewModel.initDatabase(TYPE_ROOM) {
            APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
        }
//        mBinding.btnRoom.setOnClickListener {
//
//        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}