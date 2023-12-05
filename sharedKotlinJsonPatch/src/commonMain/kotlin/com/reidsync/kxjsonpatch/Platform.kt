package com.reidsync.kxjsonpatch

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform