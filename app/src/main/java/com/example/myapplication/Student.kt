package com.example.myapplication

data class Student(val name: String, val course: Int) {
    override fun toString(): String {
        return "Student(name='$name', course=$course)"
    }
}
