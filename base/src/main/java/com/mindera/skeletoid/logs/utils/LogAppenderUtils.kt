package com.mindera.skeletoid.logs.utils

/**
 * Abstract LOG Appender
 */
object LogAppenderUtils {


    /**
     * Convert log array to string
     *
     * @param logs List of strings
     * @return the list of strings concatenated or an empty string if null
     */
    @JvmStatic
    fun getLogString(vararg logs: String?): String {
        if (logs == null) {
            return ""
        }

        val strBuilder = StringBuilder()
        for (log in logs) {
            strBuilder.append(log)
            strBuilder.append(" ")
        }
        return strBuilder.toString()
    }

    /**
     * Get class name with ClassName pre appended.
     *
     * @param clazz Class to get the tag from
     * @return Tag string
     */
    @JvmStatic
    fun getTag(clazz: Class<*>, usePackageName: Boolean, packageName: String,
               methodName: Boolean): String {
        val stringBuilder = StringBuilder()
        if (usePackageName) {
            stringBuilder.append(packageName)
            stringBuilder.append("/")
        }

        stringBuilder.append(clazz.canonicalName)

        if (methodName) {
            stringBuilder.append(".")
            stringBuilder.append(getMethodName(clazz))
        }

        return stringBuilder.toString()
    }

    /**
     * Get class method name. This will only work when proguard is not active
     *
     * @param clazz The class being logged
     */
    @JvmStatic
    fun getMethodName(clazz: Class<*>): String {
        var index = 0
        val stackTrace = Thread.currentThread().stackTrace

        for (ste in stackTrace) {
            if (ste.className == clazz.name) {
                break
            }
            index++
        }

        val methodName: String

        if (stackTrace.size > index) {
            methodName = stackTrace[index].methodName
        } else {
            methodName = "UnknownMethod"
        }

        return methodName
    }

    /**
     * Gets the hashcode of the object sent
     *
     * @return The hashcode of the object in a printable string
     */
    @JvmStatic
    fun getObjectHash(`object`: Any?): String {
        val hashCodeString: String
        if (`object` == null) {
            hashCodeString = "[!!!NULL INSTANCE!!!] "
        } else {
            val stringBuilder = StringBuilder()
            stringBuilder.append("[")
            stringBuilder.append(`object`.javaClass.simpleName)
            stringBuilder.append("#")
            stringBuilder.append(`object`.hashCode())
            stringBuilder.append("] ")

            hashCodeString = stringBuilder.toString()
        }

        return hashCodeString
    }
}
