package com

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.R
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material3.Text as Text

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("NewApi")
@Composable
fun ToDoListPage(viewModel: ToDoViewModel) {
    val toDoList by viewModel.todoList.observeAsState(emptyList())
    var inputText by remember { mutableStateOf("") }
    var editingItem by remember { mutableStateOf<ToDo?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF799FD3) // Light blue background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .height(110.dp)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text(text = "Enter task") },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF799FD3),
                        unfocusedBorderColor = Color(0xFF799FD3),
                        cursorColor = Color(0xFF799FD3),
                        containerColor = Color(0xFFFCFAFA) // White background for the text field
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (inputText.isNotBlank()) {
                            if (editingItem != null) {
                                viewModel.updateToDo(
                                    editingItem!!.copy(title = inputText)
                                )
                                editingItem = null
                            } else {
                                viewModel.addToDo(inputText)
                            }
                            inputText = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White // White background for the button
                    ),
                    shape = RoundedCornerShape(13.dp)
                ) {
                    Text(
                        text = if (editingItem != null) "Update" else "Add",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp // Increased the font size of the text
                        ))
                }
            }
            if (toDoList.isNotEmpty()) {
                LazyColumn {
                    itemsIndexed(toDoList) { _, item ->
                        ToDoItem(item = item, onEdit = {
                            editingItem = item
                            inputText = item.title
                        }, onDelete = {
                            viewModel.deleteToDo(item.id)
                        })
                    }
                }
            } else {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "No ToDos",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun ToDoItem(item: ToDo, onEdit: () -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(Color(0xFFFAF8F8))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = SimpleDateFormat(
                    "HH:mm:ss  dd/MM/yyyy",
                    Locale.ENGLISH
                ).format(item.createdAt),
                fontSize = 10.sp,
                color = Color.Black // Light blue for timestamp
            )
            Text(
                text = item.title,
                fontSize = 18.sp,
                color = Color.Black // White for the item title
            )
        }
        IconButton(onClick = onEdit) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_edit_24),
                contentDescription = "Edit",
                tint = Color(0xFF799FD3) // Maintain consistent icon color
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color(0xFF799FD3) // Maintain consistent icon color
            )
        }
    }}

