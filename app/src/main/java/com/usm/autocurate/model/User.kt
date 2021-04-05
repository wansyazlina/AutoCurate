package com.usm.autocurate.model

data class User(val userName: String, var userEmail: String, var userPassword: String, ) {
//    var userName : String = username
//    var userEmail : String = email
//    var userPassword : String = password

    constructor() : this("", "","")

}