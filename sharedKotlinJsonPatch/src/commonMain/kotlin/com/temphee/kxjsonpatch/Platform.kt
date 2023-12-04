package com.temphee.kxjsonpatch

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform