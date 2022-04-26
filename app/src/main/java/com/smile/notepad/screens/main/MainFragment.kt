package com.smile.notepad.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.smile.notepad.MainActivity
import com.smile.notepad.R
import com.smile.notepad.databinding.FragmentMainBinding
import com.smile.notepad.models.AppNote
import com.smile.notepad.screens.note.NoteFragment
import com.smile.notepad.utilites.APP_ACTIVITY


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding ?=null
    private val mBinding get() = _binding!!
    private lateinit var  mViewModel:MainFragmentViewModel

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter
    private lateinit var mObserverList:Observer<List<AppNote>>

    private lateinit var exit: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //при нажатии кнопки назад, выход из приложения
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
          requireActivity().finish()
        }

    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mAdapter = MainAdapter()
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter
        mObserverList = Observer {
            val list = it.asReversed()
            mAdapter.setList(list)
        }
        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)

        mViewModel.allNotes.observe(this, mObserverList)
        mBinding.btnAddNote.setOnClickListener {
                APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
                mViewModel.allNotes.observe(this, mObserverList)
            }
    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId = android.R.id.)
//        return super.onOptionsItemSelected(item)
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_action_menu, menu)    //Создание меню
    }

    //кнопка выхода
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.btn_exit -> {

                    requireActivity().finish()

            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mViewModel.allNotes.removeObserver(mObserverList)
        mRecyclerView.adapter = null
    }

//    //при нажатии кнопки назад, выход из приложения
//    @Override
//    fun onBackPressed() {
//        requireActivity().finish()
//    }

    companion object{
        fun click(note: AppNote) {
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment2, bundle)
        }
        }
 }

