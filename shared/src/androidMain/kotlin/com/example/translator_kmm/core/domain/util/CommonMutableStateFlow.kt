package com.example.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

actual class CommonMutableStateFlow<T> actual constructor(
  private val flow: MutableStateFlow<T>
) : MutableStateFlow<T> by flow