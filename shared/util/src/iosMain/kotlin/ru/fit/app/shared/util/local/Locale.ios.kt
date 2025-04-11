package ru.fit.app.shared.util.local

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.localeIdentifier

actual val currentLocale: String = NSLocale.currentLocale.localeIdentifier