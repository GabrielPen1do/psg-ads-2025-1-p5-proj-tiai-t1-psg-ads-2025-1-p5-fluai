package org.example.project.domain.service

import kotlinx.io.IOException
import org.example.project.domain.exceptions.HttpException

suspend fun <T> safeApiCall(call: suspend () -> T): Result<T> {
    return try {
        Result.success(call())
    } catch (e: HttpException) {
        Result.failure(Throwable(e.error))
    } catch (e: IOException) {
        Result.failure(Throwable(message = "Erro na conexão"))
    } catch (e: Exception) {
        Result.failure(Throwable(message = "Erro desconhecido"))
    }
}
