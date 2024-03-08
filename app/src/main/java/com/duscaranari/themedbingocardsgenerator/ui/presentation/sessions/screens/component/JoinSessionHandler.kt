package com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions.screens.component

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.session.model.Session
import com.duscaranari.themedbingocardsgenerator.util.auth.UserData

@Composable
fun JoinSessionHandler(
    session: Session,
    googleUser: UserData?,
    context: Context
) {
    when (googleUser) {
        null -> {
            Toast.makeText(
                context,
                context.getString(R.string.login_needed),
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {
            if (session.locked) {

            }
            else {

            }
        }
    }
}
