package com.alorma.dogarty.store

import com.alorma.dogarty.domain.model.PetDetail
import com.alorma.dogarty.domain.model.State
import com.alorma.dogarty.domain.model.UserDetail
import com.alorma.dogarty.utils.toLocalDateTime
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import java.time.LocalDateTime

class AppStore(
    private val store: FirebaseFirestore,
) {

    fun loadUser(userId: String): Flow<State<UserDetail>> = callbackFlow {
        offer(State.loading())
        val registration = store.collection("/users")
            .document(userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Timber.e(error)
                    offer(State.failed<UserDetail>(error.cause?.message.orEmpty()))
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    val userDetail = snapshot.toObject(UserDetailApi::class.java)
                    if (userDetail != null) {
                        val nick = userDetail.nick

                        val date = userDetail.created_at?.toLocalDateTime() ?: LocalDateTime.now()

                        offer(
                            State.success(
                                UserDetail(
                                    nick = nick,
                                    created = date.toLocalDate()
                                )
                            )
                        )
                    } else {
                        offer(State.failed<UserDetail>("Cannot load user"))
                    }
                } else {
                    offer(State.failed<UserDetail>("Cannot load user"))
                }
            }
        awaitClose { registration.remove() }
    }

    fun loadPets(userId: String): Flow<State<List<PetDetail>>> = callbackFlow {
        offer(State.loading<List<PetDetail>>())

        val registration = store.collection("/users")
            .document(userId)
            .collection("pets")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Timber.e(error)
                    offer(State.failed<List<PetDetail>>(error.cause?.message.orEmpty()))
                    close(error)
                    return@addSnapshotListener
                }

                if (value == null || value.isEmpty) {
                    offer(State.success(emptyList()))
                    return@addSnapshotListener
                }

                val pets = value.documents.mapNotNull { document ->
                    document.toObject(PetDetailApi::class.java)?.copy(id = document.id)
                }.filter { petApi ->
                    petApi.birthdate != null && petApi.created_at != null
                }.map { pet ->
                    PetDetail(
                        id = pet.id,
                        name = pet.name,
                        birthDate = pet.birthdate!!.toLocalDateTime().toLocalDate(),
                        created = pet.created_at!!.toLocalDateTime()
                    )
                }

                offer(State.success(pets))
            }

        awaitClose { registration.remove() }
    }
}