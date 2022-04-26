package com.smile.notepad.screens.note

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.view.*
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.smile.notepad.R
import com.smile.notepad.databinding.FragmentMainBinding
import com.smile.notepad.databinding.FragmentNoteBinding
import com.smile.notepad.models.AppNote
import com.smile.notepad.screens.main.MainAdapter
import com.smile.notepad.screens.main.MainFragmentViewModel
import com.smile.notepad.utilites.APP_ACTIVITY
import com.smile.notepad.utilites.showToast


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding?=null
    private val mBinding get() = _binding!!
    private lateinit var  mViewModel: NoteFragmentViewModel
    private lateinit var mCurrentNote:AppNote


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        mCurrentNote = arguments?.getSerializable("note") as AppNote //получение заметки из bundle

        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    @SuppressLint("ResourceAsColor")
    private fun initialization() {
        setHasOptionsMenu(true)
        mBinding.noteText.text = mCurrentNote.text
        mBinding.noteName.text = mCurrentNote.name
        mViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)    //Создание меню
    }


    //кнопка удаление
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.btn_delete -> {
                mViewModel.delete(mCurrentNote) {
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
            R.id.btn_edit -> {
                some(mCurrentNote)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Созд. Bundle для updateNoteFragment
    companion object {
        fun some(note: AppNote) {
            val bundle2 = Bundle()
            bundle2.putSerializable("someNote", note)
            APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_updateNoteFragment, bundle2)
        }
    }

}