package com.smile.notepad.screens.update

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smile.notepad.R
import com.smile.notepad.databinding.FragmentUpdateNoteBinding
import com.smile.notepad.models.AppNote
import com.smile.notepad.utilites.APP_ACTIVITY
import com.smile.notepad.utilites.showToast


class UpdateNoteFragment : Fragment() {

    private var _binding: FragmentUpdateNoteBinding?=null
    private val mBinding get() = _binding!!
    private lateinit var  mViewModel: UpdateNoteFragmentViewModel
    private lateinit var mCurrentNote:AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateNoteBinding.inflate(layoutInflater, container, false)
        mCurrentNote = arguments?.getSerializable("someNote") as AppNote //получение заметки из bundle (NoteFragment)
        return mBinding.root
    }
    override fun onStart() {
        super.onStart()
        initialization()
    }

    @SuppressLint("ResourceAsColor")
    private fun initialization() {
        setHasOptionsMenu(true)
        mBinding.inputNameNoteUpdate.setText(mCurrentNote.name)
        mBinding.inputTextNoteUpdate.setText(mCurrentNote.text)
        mViewModel = ViewModelProvider(this).get(UpdateNoteFragmentViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_update_menu, menu)    //подключение меню
    }

    //кнопка удаление - кнопка сохранение
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.btn_delete -> {
                mViewModel.delete(mCurrentNote) {
                    APP_ACTIVITY.navController.navigate(R.id.action_updateNoteFragment_to_mainFragment)
                }
            }
            R.id.btn_save -> {
                mViewModel.update(mCurrentNote) {
                    val title = mBinding.inputNameNoteUpdate.text.toString().trim()
                    val body = mBinding.inputTextNoteUpdate.text.toString().trim()

                    if (title.isNotEmpty()) {
                        val note = AppNote(mCurrentNote.id, title, body)
                        mViewModel.update(note) {
                            APP_ACTIVITY.navController.navigate(R.id.action_updateNoteFragment_to_mainFragment)
                        }

                    } else {
                        showToast("Ввидите имя записи")
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