package vn.edu.hust.studentman

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
  val students = mutableListOf(
    StudentModel("Nguyễn Văn An", "SV001"),
    StudentModel("Trần Thị Bảo", "SV002"),
    StudentModel("Lê Hoàng Cường", "SV003"),
    StudentModel("Phạm Thị Dung", "SV004"),
    StudentModel("Đỗ Minh Đức", "SV005"),
    StudentModel("Vũ Thị Hoa", "SV006"),
    StudentModel("Hoàng Văn Hải", "SV007"),
    StudentModel("Bùi Thị Hạnh", "SV008"),
    StudentModel("Đinh Văn Hùng", "SV009"),
    StudentModel("Nguyễn Thị Linh", "SV010"),
    StudentModel("Phạm Văn Long", "SV011"),
    StudentModel("Trần Thị Mai", "SV012"),
    StudentModel("Lê Thị Ngọc", "SV013"),
    StudentModel("Vũ Văn Nam", "SV014"),
    StudentModel("Hoàng Thị Phương", "SV015"),
    StudentModel("Đỗ Văn Quân", "SV016"),
    StudentModel("Nguyễn Thị Thu", "SV017"),
    StudentModel("Trần Văn Tài", "SV018"),
    StudentModel("Phạm Thị Tuyết", "SV019"),
    StudentModel("Lê Văn Vũ", "SV020")
  )
  val studentAdapter = StudentAdapter(students)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    findViewById<RecyclerView>(R.id.recycler_view_students).run {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }

    findViewById<Button>(R.id.btn_add_new).setOnClickListener {
      showAddStudentDialog()
    }

  }
  private fun showAddStudentDialog() {
    val dialog = Dialog(this)
    dialog.setContentView(R.layout.layout_edit_dialog)
    val editName = dialog.findViewById<EditText>(R.id.edit_hoten)
    val editId = dialog.findViewById<EditText>(R.id.edit_mssv)
    val btnOk = dialog.findViewById<Button>(R.id.button_ok)
    val btnCancel = dialog.findViewById<Button>(R.id.button_cancel)

    btnOk.setOnClickListener {
      val name = editName.text.toString()
      val id = editId.text.toString()
      if (name.isNotEmpty() && id.isNotEmpty()) {
        students.add(StudentModel(name, id))
        studentAdapter.notifyItemInserted(students.size - 1)
      }
      dialog.dismiss()
    }

    btnCancel.setOnClickListener { dialog.dismiss() }
    dialog.show()
  }
}