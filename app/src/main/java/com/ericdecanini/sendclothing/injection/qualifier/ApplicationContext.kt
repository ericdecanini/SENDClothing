package com.ericdecanini.sendclothing.injection.qualifier

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier
import kotlin.annotation.Retention

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext