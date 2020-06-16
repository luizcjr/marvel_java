package com.example.marvel.util;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * This solution was found in this link below, because Android Studio warning to an error in annotationProcessor
 * http://sjudd.github.io/glide/doc/generatedapi.html
 */

@GlideModule
public final class MyAppGlideModule extends AppGlideModule {
}
