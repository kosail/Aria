package com.korealm.aria

import androidx.compose.runtime.Composable
import aria.shared.generated.resources.Res
import aria.shared.generated.resources.favicon
import org.jetbrains.compose.resources.painterResource

@Composable
fun getAriaWindowIcon() = painterResource(Res.drawable.favicon)