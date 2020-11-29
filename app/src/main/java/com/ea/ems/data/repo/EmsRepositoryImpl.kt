package com.ea.ems.data.repo

import com.ea.ems.data.remote.model.MenuModel
import com.ea.ems.data.remote.model.toInfo
import com.ea.ems.domain.repo.EmsRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

class EmsRepositoryImpl(
    private val firestore: FirebaseFirestore
) : EmsRepository {

    override val latestEmsMenu = channelFlow {
        firestore
            .collection(FIRESTORE_MENU)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    throw error
                }

                snapshot?.first()?.toObject(MenuModel::class.java)?.let {
                    launch { send(it.toInfo()) }
                }
            }
        awaitClose()
    }
}

private const val FIRESTORE_MENU = "menus"
