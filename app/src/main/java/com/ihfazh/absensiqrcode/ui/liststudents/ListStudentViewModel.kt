package com.ihfazh.absensiqrcode.ui.liststudents

import android.graphics.Paint
import android.graphics.Typeface.*
import android.graphics.pdf.PdfDocument
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ihfazh.absensiqrcode.domains.students.usecases.StudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.glxn.qrgen.android.QRCode
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class ListStudentViewModel @Inject constructor(private val useCase: StudentUseCase) : ViewModel() {
    fun getStudents() = LiveDataReactiveStreams.fromPublisher(
        useCase.list()
    )

    fun exportStudentsData(path: String, onSuccess: () -> Unit): Disposable {
        return useCase.listAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { students ->

                    val document = PdfDocument()
                    students.forEachIndexed { index, student ->
                        val pageInfo = PdfDocument.PageInfo.Builder(200, 200, index + 1).create()
                        val page = document.startPage(pageInfo)

                        val canvas = page.canvas
                        val paint = Paint()
                        val title = Paint()


                        canvas.drawBitmap(QRCode.from("attendanceqrcode.${student.studentId}").withSize(120, 120).bitmap(), 10.toFloat(), 10.toFloat(), paint)

                        title.typeface = create(DEFAULT, NORMAL)
                        title.textSize = 12.toFloat()
                        canvas.drawText("${student.firstName} ${student.lastName}", 10.toFloat(), 140.toFloat(), title)
                        document.finishPage(page)
                    }

                    val file = File(path)
                    document.writeTo(FileOutputStream(file))
                    document.close()

                    onSuccess()
                },
                {},
            )

    }
}