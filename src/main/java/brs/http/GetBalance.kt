package brs.http

import brs.http.common.Parameters
import brs.services.ParameterService
import com.google.gson.JsonElement
import javax.servlet.http.HttpServletRequest

internal class GetBalance(private val parameterService: ParameterService) : APIServlet.JsonRequestHandler(arrayOf(APITag.ACCOUNTS), Parameters.ACCOUNT_PARAMETER) {
    override suspend fun processRequest(request: HttpServletRequest): JsonElement {
        return JSONData.accountBalance(parameterService.getAccount(request))
    }
}
