package com.smile.notepad.screens.add_new_note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smile.notepad.R
import com.smile.notepad.databinding.FragmentAddNewNoteBinding
import com.smile.notepad.models.AppNote
import com.smile.notepad.utilites.APP_ACTIVITY
import com.smile.notepad.utilites.showToast

class AddNewNoteFragment : Fragment() {

    private var _binding:FragmentAddNewNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNewNoteFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewNoteBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mViewModel = ViewModelProvider(this).get(AddNewNoteFragmentViewModel::class.java)
        mBinding.btnAddNote.setOnClickListener {
            val name = mBinding.inputNameNote.text.toString()
            val text = mBinding.inputTextNote.text.toString()
            if (name.isEmpty()) {
                showToast(getString(R.string.toast_enter_name))
            } else {
                mViewModel.insert(AppNote(name = name, text = text)) {
                    APP_ACTIVITY.navController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_add_menu, menu)    //Создание меню
    }

    //кнопка сохранение
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.btn_save -> {
                val name = mBinding.inputNameNote.text.toString()
                val text = mBinding.inputTextNote.text.toString()
                if (name.isEmpty()) {
                    showToast(getString(R.string.toast_enter_name))
                } else {
                    mViewModel.insert(AppNote(name = name, text = text)) {
                        APP_ACTIVITY.navController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}