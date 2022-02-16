package com.app.mymainapp.datastore

import com.app.mymainapp.models.UserModel

object UserDataStore {

    private var userList = arrayListOf<UserModel>()

    @JvmName("getUserList1")
    fun getUserList(): ArrayList<UserModel> {
        userList.add(
            UserModel(
                "9446026465",
                "123456"
            )
        )

        userList.add(
            UserModel(
                "8086899495",
                "123456"
            )
        )
        userList.add(
            UserModel(
                "6238283379",
                "123456"
            )
        )

        userList.add(
            UserModel(
                "9497433465",
                "123456"
            )
        )
        return userList
    }
}