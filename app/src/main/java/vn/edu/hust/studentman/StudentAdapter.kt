package vn.edu.hust.studentman

import android.app.AlertDialog
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class StudentAdapter(val students:  MutableList<StudentModel>): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
  class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
    val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item,
       parent, false)
    return StudentViewHolder(itemView)
  }

  override fun getItemCount(): Int = students.size

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]

    holder.textStudentName.text = student.studentName
    holder.textStudentId.text = student.studentId

    holder.imageEdit.setOnClickListener{
      showEditDialog(holder.itemView,position)
    }
    holder.imageRemove.setOnClickListener{
      showRemoveConfirmation(holder.itemView, position)
    }
  }

  fun showEditDialog(view: View,position: Int){
    val context =view.context
    val dialog = Dialog(context)
    dialog.setContentView(R.layout.layout_edit_dialog)

    val editName = dialog.findViewById<EditText>(R.id.edit_hoten)
    val editId = dialog.findViewById<EditText>(R.id.edit_mssv)
    val btnOk = dialog.findViewById<Button>(R.id.button_ok)
    val btnCancel = dialog.findViewById<Button>(R.id.button_cancel)

    // load du lieu cu
    editName.setText(students[position].studentName)
    editId.setText(students[position].studentId)

    btnOk.setOnClickListener {
      students[position].studentName = editName.text.toString()
      students[position].studentId = editId.text.toString()
      notifyItemChanged(position)
      dialog.dismiss()
    }
    btnCancel.setOnClickListener { dialog.dismiss() }
    dialog.show()
  }

  fun showRemoveConfirmation(view: View,position: Int){
    val context = view.context
    AlertDialog.Builder(context).apply {
      setTitle("Xóa sinh viên")
      setMessage("Bạn có chắc muốn xóa sinh viên này không?")
      setPositiveButton("Xóa") { _, _ ->
        val deletedStudent = students.removeAt(position)
        notifyItemRemoved(position)

        // Hiển thị Snackbar với tùy chọn Undo
        Snackbar.make(view, "Đã xóa sinh viên", Snackbar.LENGTH_LONG)
          .setAction("Undo") {
            students.add(position, deletedStudent)
            notifyItemInserted(position)
          }.show()
      }
      setNegativeButton("Hủy", null)
    }.show()
  }

}