package com.ea.ems.data.repo

import com.ea.ems.data.remote.model.MenuModel
import com.ea.ems.data.remote.model.toInfo
import com.ea.ems.domain.model.MenuInfo
import com.ea.ems.domain.repo.EmsRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

class EmsRepositoryImpl(
    private val firebaseDatabase: FirebaseDatabase
) : EmsRepository {

    override val latestEmsMenu = channelFlow<MenuInfo> {
        firebaseDatabase
            .getReference(FIREBASE_DATABASE_MENU)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.first()
                        .getValue(MenuModel::class.java)
                        ?.toInfo()
                        ?.let {
                            launch { send(it) }
                        }
                }

                override fun onCancelled(error: DatabaseError) {
                    throw error.toException()
                }
            })
        awaitClose()
    }
}

private const val FIREBASE_DATABASE_MENU = "menu"
